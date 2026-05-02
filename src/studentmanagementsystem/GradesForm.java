

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package studentmanagementsystem;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
// ADD THESE IMPORTS:

/**
 *
 * @author nico
 */
public class GradesForm extends javax.swing.JFrame {
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GradesForm.class.getName());

      // ── Column index constants (14 columns, no Quiz Average) ──
    private static final int COL_STUDENT_ID     = 0;
    private static final int COL_FULL_NAME      = 1;
    private static final int COL_ATTENDANCE     = 2;  // 0-100, manual input
    private static final int COL_PARTICIPATION  = 3;  // 0-100, manual input
    private static final int COL_QUIZ1          = 4;  // 0-10
    private static final int COL_QUIZ2          = 5;  // 0-10
    private static final int COL_QUIZ3          = 6;  // 0-10
    private static final int COL_QUIZ4          = 7;  // 0-10
    private static final int COL_EXAM           = 8;  // 0-50
    private static final int COL_PERIOD_GRADE   = 9;  // auto computed
    private static final int COL_PERIOD_RATING  = 10; // auto computed
    private static final int COL_PERIOD_REMARKS = 11; // auto computed
    private static final int COL_FINAL_AVE      = 12; // auto computed
    private static final int COL_FINAL_REMARKS  = 13; // auto computed

    private DefaultTableModel tableModel;

    /**
     * Creates new form GradesForm
     */
    public GradesForm() {
        initComponents();
        setSize(1050, 640);
        setLocationRelativeTo(null);
        setTitle("Grades Management");
        setupTable();
        loadSubjects();
        loadData();
    }

     // ── Setup 14-column table ──
    private void setupTable() {
        String[] columns = {
            "Student ID", "Full Name", "Attendance", "Participation",
            "Quiz 1", "Quiz 2", "Quiz 3", "Quiz 4", "Exam Score",
            "Period Grade", "Period Rating", "Period Remarks",
            "Final Ave Rating", "Final Remarks"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only attendance to exam are editable (columns 2-8)
                return column >= COL_ATTENDANCE && column <= COL_EXAM;
            }
        };

        tblGrades.setModel(tableModel);
        tblGrades.setRowHeight(24);
        tblGrades.getTableHeader().setReorderingAllowed(false);
    }

    // ── Load subjects from DB ──
    private void loadSubjects() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM subjects";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            cmbSubject.removeAllItems();
            while (rs.next()) {
                cmbSubject.addItem(rs.getString("subject_name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading subjects: " + e.getMessage());
        }
    }

    // ── Load data based on selected period ──
    private void loadData() {
        if (tableModel == null) return;
        tableModel.setRowCount(0);

        String period = (String) cmbPeriod.getSelectedItem();
        if (period == null) return;

        // Use backticks for "final" (reserved word)
        String tableName = period.equalsIgnoreCase("Midterm") ? "midterm" : "`final`";

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM " + tableName;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("student_id"),
                    rs.getString("full_name"),
                    rs.getDouble("attendance"),       // default 0
                    rs.getDouble("participation"),    // default 0
                    rs.getDouble("quiz1"),
                    rs.getDouble("quiz2"),
                    rs.getDouble("quiz3"),
                    rs.getDouble("quiz4"),
                    rs.getDouble("exam_score"),
                    rs.getDouble("period_grade"),
                    rs.getDouble("period_rating"),
                    rs.getString("period_remarks"),
                    rs.getDouble("final_ave_rating"),
                    rs.getString("final_remarks")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading data: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── COMPUTE BUTTON ──
    private void computeAll() {
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            try {
                // Get inputs
                double attendance    = toDouble(tableModel.getValueAt(row, COL_ATTENDANCE));
                double participation = toDouble(tableModel.getValueAt(row, COL_PARTICIPATION));
                double q1  = toDouble(tableModel.getValueAt(row, COL_QUIZ1));
                double q2  = toDouble(tableModel.getValueAt(row, COL_QUIZ2));
                double q3  = toDouble(tableModel.getValueAt(row, COL_QUIZ3));
                double q4  = toDouble(tableModel.getValueAt(row, COL_QUIZ4));
                double exam = toDouble(tableModel.getValueAt(row, COL_EXAM));

                // Step 1: Quiz Average (max 10)
                double quizAve = (q1 + q2 + q3 + q4) / 4;

                // Step 2: Compute Period Grade
                // attendance  : 0-100, weight 10% → max contribution = 10
                // participation: 0-100, weight 20% → max contribution = 20
                // quizAve     : 0-10,  weight 30% → normalize: (quizAve/10)*30 → max = 30
                // exam        : 0-50,  weight 40% → normalize: (exam/50)*40   → max = 40
                // Total max = 10+20+30+40 = 100
                double periodGrade = (attendance * 0.10)
                                   + (participation * 0.20)
                                   + ((quizAve / 10.0) * 30.0)
                                   + ((exam / 50.0) * 40.0);

                // Step 3: Convert to Rating and Remarks
                double rating  = convertToRating(periodGrade);
                String remarks = getRemarks(rating);

                // Update table
                tableModel.setValueAt(round2(periodGrade), row, COL_PERIOD_GRADE);
                tableModel.setValueAt(rating,              row, COL_PERIOD_RATING);
                tableModel.setValueAt(remarks,             row, COL_PERIOD_REMARKS);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Error on row " + (row + 1) + ": " + e.getMessage());
            }
        }

        // Step 4: Compute Final Average if BOTH periods saved
        computeFinalAverage();

        JOptionPane.showMessageDialog(this,
            "Computation complete!\nClick SAVE to store results.",
            "Done", JOptionPane.INFORMATION_MESSAGE);
    }

    // ── Compute Final Average using ratings ──
    private void computeFinalAverage() {
        try (Connection conn = getConnection()) {
            String period = (String) cmbPeriod.getSelectedItem();
            // Get the OTHER period's data
            String otherTable = period.equalsIgnoreCase("Midterm") ? "`final`" : "midterm";

            for (int row = 0; row < tableModel.getRowCount(); row++) {
                String studentId = String.valueOf(tableModel.getValueAt(row, COL_STUDENT_ID));
                if (studentId == null || studentId.isEmpty()) continue;

                double currentRating = toDouble(tableModel.getValueAt(row, COL_PERIOD_RATING));

                // Get other period's rating from DB
                String sql = "SELECT period_rating FROM " + otherTable + " WHERE student_id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, studentId);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    double otherRating = rs.getDouble("period_rating");

                    // Only compute if other period is already computed (rating > 0)
                    if (otherRating > 0 && currentRating > 0) {
                        // Final Ave Rating = (MidtermRating + FinalRating) / 2
                        double finalAveRating = round2((currentRating + otherRating) / 2.0);
                        String finalRemarks = finalAveRating <= 3.00 ? "Passed" : "Failed";

                        tableModel.setValueAt(finalAveRating, row, COL_FINAL_AVE);
                        tableModel.setValueAt(finalRemarks,   row, COL_FINAL_REMARKS);
                    }
                }
            }
        } catch (Exception e) {
            // Normal if other period not saved yet - stay silent
        }
    }

    // ── SAVE BUTTON ──
    private void saveData() {
        String period = (String) cmbPeriod.getSelectedItem();
        String tableName = period.equalsIgnoreCase("Midterm") ? "midterm" : "`final`";

        try (Connection conn = getConnection()) {
            String sql = "UPDATE " + tableName +
                " SET attendance=?, participation=?," +
                " quiz1=?, quiz2=?, quiz3=?, quiz4=?, exam_score=?," +
                " period_grade=?, period_rating=?, period_remarks=?," +
                " final_ave_rating=?, final_remarks=?" +
                " WHERE student_id=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            for (int row = 0; row < tableModel.getRowCount(); row++) {
                pst.setDouble(1,  toDouble(tableModel.getValueAt(row, COL_ATTENDANCE)));
                pst.setDouble(2,  toDouble(tableModel.getValueAt(row, COL_PARTICIPATION)));
                pst.setDouble(3,  toDouble(tableModel.getValueAt(row, COL_QUIZ1)));
                pst.setDouble(4,  toDouble(tableModel.getValueAt(row, COL_QUIZ2)));
                pst.setDouble(5,  toDouble(tableModel.getValueAt(row, COL_QUIZ3)));
                pst.setDouble(6,  toDouble(tableModel.getValueAt(row, COL_QUIZ4)));
                pst.setDouble(7,  toDouble(tableModel.getValueAt(row, COL_EXAM)));
                pst.setDouble(8,  toDouble(tableModel.getValueAt(row, COL_PERIOD_GRADE)));
                pst.setDouble(9,  toDouble(tableModel.getValueAt(row, COL_PERIOD_RATING)));
                pst.setString(10, objToStr(tableModel.getValueAt(row, COL_PERIOD_REMARKS)));
                pst.setDouble(11, toDouble(tableModel.getValueAt(row, COL_FINAL_AVE)));
                pst.setString(12, objToStr(tableModel.getValueAt(row, COL_FINAL_REMARKS)));
                pst.setString(13, objToStr(tableModel.getValueAt(row, COL_STUDENT_ID)));
                pst.addBatch();
            }

            pst.executeBatch();

            // Also update other table's Final Ave Rating
            updateOtherTableFinalAve(conn, period);

            JOptionPane.showMessageDialog(this,
                "Saved to " + period + " table!",
                "Saved", JOptionPane.INFORMATION_MESSAGE);

            loadData(); // refresh table

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error saving: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── Update Final Ave in the other period table ──
    private void updateOtherTableFinalAve(Connection conn, String period) {
        try {
            String currentTable = period.equalsIgnoreCase("Midterm") ? "midterm" : "`final`";
            String otherTable   = period.equalsIgnoreCase("Midterm") ? "`final`" : "midterm";

            // Get all students from current table
            String sql = "SELECT student_id, period_rating FROM " + currentTable;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String studentId    = rs.getString("student_id");
                double currentRating = rs.getDouble("period_rating");

                // Get other period rating
                String sqlOther = "SELECT period_rating FROM " + otherTable + " WHERE student_id=?";
                PreparedStatement pstOther = conn.prepareStatement(sqlOther);
                pstOther.setString(1, studentId);
                ResultSet rsOther = pstOther.executeQuery();

                if (rsOther.next()) {
                    double otherRating = rsOther.getDouble("period_rating");

                    if (otherRating > 0 && currentRating > 0) {
                        double finalAve    = round2((currentRating + otherRating) / 2.0);
                        String finalRemark = finalAve <= 3.00 ? "Passed" : "Failed";

                        // Update other table
                        String sqlUpdate = "UPDATE " + otherTable +
                            " SET final_ave_rating=?, final_remarks=? WHERE student_id=?";
                        PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdate);
                        pstUpdate.setDouble(1, finalAve);
                        pstUpdate.setString(2, finalRemark);
                        pstUpdate.setString(3, studentId);
                        pstUpdate.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            // silent
        }
    }

    // ── Rating Conversion (uses grade score 0-100) ──
    private double convertToRating(double grade) {
        if (grade >= 97) return 1.00;
        if (grade >= 94) return 1.25;
        if (grade >= 91) return 1.50;
        if (grade >= 88) return 1.75;
        if (grade >= 85) return 2.00;
        if (grade >= 82) return 2.25;
        if (grade >= 79) return 2.50;
        if (grade >= 76) return 2.75;
        if (grade >= 74) return 3.00;
        return 5.00;
    }

    private String getRemarks(double rating) {
        return rating <= 3.00 ? "Passed" : "Failed";
    }

    private double toDouble(Object val) {
        if (val == null) return 0;
        try { return Double.parseDouble(val.toString()); }
        catch (NumberFormatException e) { return 0; }
    }

    private String objToStr(Object val) {
        if (val == null) return "";
        return val.toString();
    }

    private double round2(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/student_management_system",
            "root", ""
        );
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        panelFilter = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbPeriod = new javax.swing.JComboBox<>();
        cmbSubject = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGrades = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnCompute = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblTitle.setText("Grades Management");

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addGap(395, 395, 395))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitle)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Subject:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Period:");

        cmbPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Midterm", "Final" }));
        cmbPeriod.addItemListener(this::cmbPeriodItemStateChanged);

        cmbSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSubject.addActionListener(this::cmbSubjectActionPerformed);

        javax.swing.GroupLayout panelFilterLayout = new javax.swing.GroupLayout(panelFilter);
        panelFilter.setLayout(panelFilterLayout);
        panelFilterLayout.setHorizontalGroup(
            panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFilterLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 417, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        panelFilterLayout.setVerticalGroup(
            panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFilterLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(panelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cmbPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        tblGrades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Full Name", "Attendance", "Participation", "Quiz 1", "Quiz 2", "Quiz 3", "Quiz 4", "Exam Score", "Period Grade", "Period Rating", "Period Remarks", "Final Average Rating", "Final Remarks"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblGrades);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        btnCompute.setText("Compute");
        btnCompute.addActionListener(this::btnComputeActionPerformed);

        btnSave.setText("Save");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompute)
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addGap(29, 29, 29))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompute)
                    .addComponent(btnBack)
                    .addComponent(btnSave))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
     // <editor-fold defaultstate="collapsed" desc="Generated Code">
    
    
    private void cmbPeriodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodItemStateChanged
        // TODO add your handling code here:
 if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            loadData();
        }
    }//GEN-LAST:event_cmbPeriodItemStateChanged

    private void btnComputeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComputeActionPerformed
        // TODO add your handling code here:
        computeAll();
    }//GEN-LAST:event_btnComputeActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        saveData();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new ProfessorDashboard().setVisible(true);
this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void cmbSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSubjectActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new GradesForm().setVisible(true));
     
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCompute;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbPeriod;
    private javax.swing.JComboBox<String> cmbSubject;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelFilter;
    private javax.swing.JPanel panelTop;
    private javax.swing.JTable tblGrades;
    // End of variables declaration//GEN-END:variables
}
