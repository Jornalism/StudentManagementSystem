

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


package studentmanagementsystem;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nico
 */
public class ProfessorDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProfessorDashboard.class.getName());
    
    // Flag to track if there are unsaved changes in Manage Grades table
private boolean gradesDirty = false;

    // ── Column index constants for grades table (14 columns) ──
private static final int COL_STUDENT_ID     = 0;
private static final int COL_FULL_NAME      = 1;
private static final int COL_ATTENDANCE     = 2;
private static final int COL_PARTICIPATION  = 3;
private static final int COL_QUIZ1          = 4;
private static final int COL_QUIZ2          = 5;
private static final int COL_QUIZ3          = 6;
private static final int COL_QUIZ4          = 7;
private static final int COL_EXAM           = 8;
private static final int COL_PERIOD_GRADE   = 9;
private static final int COL_PERIOD_RATING  = 10;
private static final int COL_PERIOD_REMARKS = 11;
private static final int COL_FINAL_AVE      = 12;
private static final int COL_FINAL_REMARKS  = 13;

    /**
     * Creates new form ProfessorDashboard
     */
    public ProfessorDashboard() {
        initComponents();
    setSize(1065, 665);       
    setResizable(false); 
    setLocationRelativeTo(null);
   loadCoursesAndSectionsForReports();
    setTitle("Student Management System - Professor");
    
     // === Initialize Record Attendance tab ===
    loadSubjectsForAttendance();
    setCurrentDate();
    cleanupOrphanedAttendance();
    loadCoursesAndSectionsForAttendance(); 
    loadCoursesAndSectionsForGrades();
cmbReportCourse.addActionListener(e -> refreshReport());
cmbReportSection.addActionListener(e -> refreshReport());
    // === Add action listeners for attendance buttons ===
    btnAllPresent.addActionListener(e -> markAllPresent());
    btnAllAbsent.addActionListener(e -> markAllAbsent());
    btnSave.addActionListener(e -> saveAttendance());
    btnReset.addActionListener(e -> resetAttendanceStatus());
    btnView.addActionListener(e -> viewAttendanceHistory());

    // Reload students when subject changes
    cmbSubjectAttendance.addActionListener(e -> loadStudentsForAttendance());

  
    
    // === Initialize Manage Grades tab ===
loadSubjectsForGrades();
cmbCourseGrades.addActionListener(e -> loadGradesData());   // course filter
cmbSectionGrades.addActionListener(e -> loadGradesData()); // section filter
cmbPeriod.addItemListener(e -> {
    if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        loadGradesData();
    }
});
cmbSubject8.addActionListener(e -> loadGradesData());
btnCompute.addActionListener(e -> computeAllGrades());
btnSave1.addActionListener(e -> saveGradesData());
btnReset5.addActionListener(e -> resetGradesData());


// Track when user edits editable columns in Manage Grades
tblGrades.getModel().addTableModelListener(e -> {
    if (e.getColumn() >= COL_ATTENDANCE && e.getColumn() <= COL_EXAM) {
        gradesDirty = true;
    }
});

// === Initialize View Reports tab ===
cmbReportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
    "Student List", "Attendance Report", "Grade Report Midterm", "Grade Report Final", "Grade Report Final Average"
}));

btnGenerate.addActionListener(e -> {
    String selected = cmbReportType.getSelectedItem().toString();
    switch (selected) {
        case "Student List":
            loadStudentListReport();
            break;
        case "Attendance Report":
            loadAttendanceReport();
            break;
        case "Grade Report Midterm":
            loadGradeReportMidterm();
            break;
        case "Grade Report Final":
            loadGradeReportFinal();
            break;
        case "Grade Report Final Average":
            loadGradeReportFinalAverage();
            break;
    }
});
btnPrint.addActionListener(e -> printReport());
// Load default report (Student List) when the tab is first shown
cmbReportType.setSelectedIndex(0);
loadStudentListReport();

cmbCourseMyStudent.addActionListener(e -> loadFilteredStudents());
loadFilteredStudents();
// Manage Grades filter listeners
cmbSubject8.addActionListener(e -> loadGradesData());
cmbPeriod.addActionListener(e -> loadGradesData());
btnSearch21.addActionListener(e -> loadGradesData());
cmbCourseAttendance.addActionListener(e -> loadStudentsForAttendance());
cmbSectionAttendance.addActionListener(e -> loadStudentsForAttendance());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        panelCenter = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblTitle1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabMystudent = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblTitle6 = new javax.swing.JLabel();
        cmbCourseMyStudent = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        lblTitle5 = new javax.swing.JLabel();
        txtMySearch = new javax.swing.JTextField();
        btnMysearch = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMystudent = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        lblTotalstudents1 = new javax.swing.JLabel();
        tabRecordAttendance = new javax.swing.JPanel();
        btnLogout1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lblTitle11 = new javax.swing.JLabel();
        btnAllPresent = new javax.swing.JButton();
        lblTitle16 = new javax.swing.JLabel();
        btnAllAbsent = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRecord = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        cmbSubjectAttendance = new javax.swing.JComboBox<>();
        lblTitle4 = new javax.swing.JLabel();
        cmbCourseAttendance = new javax.swing.JComboBox<>();
        lblTitle14 = new javax.swing.JLabel();
        lblTitle15 = new javax.swing.JLabel();
        cmbSectionAttendance = new javax.swing.JComboBox<>();
        lblDate = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblPresent = new javax.swing.JLabel();
        lblAbsent = new javax.swing.JLabel();
        lblLate = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        tabManagegrades = new javax.swing.JPanel();
        btnLogout2 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblGrades = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        cmbSubject8 = new javax.swing.JComboBox<>();
        lblTitle25 = new javax.swing.JLabel();
        lblTitle26 = new javax.swing.JLabel();
        lblTitle29 = new javax.swing.JLabel();
        cmbSectionGrades = new javax.swing.JComboBox<>();
        lblTitle28 = new javax.swing.JLabel();
        cmbPeriod = new javax.swing.JComboBox<>();
        cmbCourseGrades = new javax.swing.JComboBox<>();
        btnCompute = new javax.swing.JButton();
        btnSave1 = new javax.swing.JButton();
        btnReset5 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        lblTitle27 = new javax.swing.JLabel();
        txtSearch5 = new javax.swing.JTextField();
        btnSearch21 = new javax.swing.JButton();
        tabViewreport = new javax.swing.JPanel();
        btnLogout3 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        cmbReportType = new javax.swing.JComboBox<>();
        lblReportType = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        lblReportType1 = new javax.swing.JLabel();
        cmbReportCourse = new javax.swing.JComboBox<>();
        lblReportType2 = new javax.swing.JLabel();
        cmbReportSection = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMystudent3 = new javax.swing.JTable();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        panelCenter.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        jPanel4.setBackground(new java.awt.Color(102, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        lblTitle1.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        lblTitle1.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle1.setText("Student Management System                                 Welcome, Professor!");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabMystudent.setBackground(new java.awt.Color(102, 153, 255));
        tabMystudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        btnLogout.setBackground(new java.awt.Color(255, 0, 51));
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        jPanel3.setBackground(new java.awt.Color(102, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        lblTitle6.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle6.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle6.setText("Course:");

        cmbCourseMyStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "ACT", "BSCS", "BSIT" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCourseMyStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle6)
                    .addComponent(cmbCourseMyStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(102, 153, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        lblTitle5.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle5.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lblTitle5.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle5.setText("    Search Student:");

        txtMySearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMySearchKeyReleased(evt);
            }
        });

        btnMysearch.setText("Search");
        btnMysearch.addActionListener(this::btnMysearchActionPerformed);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMysearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(395, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMysearch))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(102, 153, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        tblMystudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Full Name", "Course", "Section", "Email"
            }
        ));
        jScrollPane1.setViewportView(tblMystudent);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(102, 153, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        lblTotalstudents1.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalstudents1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTotalstudents1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalstudents1.setText("Total Students: ");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTotalstudents1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalstudents1)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabMystudentLayout = new javax.swing.GroupLayout(tabMystudent);
        tabMystudent.setLayout(tabMystudentLayout);
        tabMystudentLayout.setHorizontalGroup(
            tabMystudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMystudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabMystudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(tabMystudentLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabMystudentLayout.setVerticalGroup(
            tabMystudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabMystudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogout)
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("My Student", tabMystudent);

        tabRecordAttendance.setBackground(new java.awt.Color(102, 153, 255));
        tabRecordAttendance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        btnLogout1.setBackground(new java.awt.Color(255, 51, 0));
        btnLogout1.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout1.setText("Logout");
        btnLogout1.addActionListener(this::btnLogout1ActionPerformed);

        jPanel9.setBackground(new java.awt.Color(102, 153, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        lblTitle11.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle11.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle11.setText("Mark All Absent:");

        btnAllPresent.setBackground(new java.awt.Color(0, 255, 153));
        btnAllPresent.setText("Present");

        lblTitle16.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle16.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle16.setText("Mark All Present:");

        btnAllAbsent.setBackground(new java.awt.Color(255, 51, 0));
        btnAllAbsent.setForeground(new java.awt.Color(255, 255, 255));
        btnAllAbsent.setText("Absent");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTitle16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAllPresent, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lblTitle11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAllAbsent, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAllPresent)
                    .addComponent(btnAllAbsent)
                    .addComponent(lblTitle16, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(102, 153, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        tblRecord.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Student ID", "Full Name", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblRecord);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel17.setBackground(new java.awt.Color(102, 153, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        lblTitle4.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle4.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle4.setText("  Subject:");

        cmbCourseAttendance.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "ACT", "BSCS", "BSIT" }));

        lblTitle14.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle14.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle14.setText("Course:");

        lblTitle15.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle15.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle15.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle15.setText("Section:");

        cmbSectionAttendance.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        lblDate.setBackground(new java.awt.Color(255, 255, 255));
        lblDate.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("Date:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblTitle4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSubjectAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitle14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCourseAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSectionAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 93, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSubjectAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle4)
                    .addComponent(cmbCourseAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle14)
                    .addComponent(lblTitle15)
                    .addComponent(cmbSectionAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDate)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        lblPresent.setBackground(new java.awt.Color(255, 255, 255));
        lblPresent.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblPresent.setForeground(new java.awt.Color(255, 255, 255));
        lblPresent.setText("Present: ");

        lblAbsent.setBackground(new java.awt.Color(255, 255, 255));
        lblAbsent.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblAbsent.setForeground(new java.awt.Color(255, 255, 255));
        lblAbsent.setText("Absent: ");

        lblLate.setBackground(new java.awt.Color(255, 255, 255));
        lblLate.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLate.setForeground(new java.awt.Color(255, 255, 255));
        lblLate.setText("Late: ");

        lblTotal.setBackground(new java.awt.Color(255, 255, 255));
        lblTotal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("Total: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblPresent)
                .addGap(156, 156, 156)
                .addComponent(lblAbsent)
                .addGap(188, 188, 188)
                .addComponent(lblLate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotal)
                .addGap(180, 180, 180))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPresent)
                    .addComponent(lblAbsent)
                    .addComponent(lblLate)
                    .addComponent(lblTotal))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(102, 153, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        btnSave.setBackground(new java.awt.Color(0, 255, 153));
        btnSave.setText("Save Attendance");

        btnReset.setBackground(new java.awt.Color(255, 153, 51));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");

        btnView.setBackground(new java.awt.Color(153, 204, 255));
        btnView.setText("View History");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnReset)
                    .addComponent(btnView))
                .addContainerGap())
        );

        javax.swing.GroupLayout tabRecordAttendanceLayout = new javax.swing.GroupLayout(tabRecordAttendance);
        tabRecordAttendance.setLayout(tabRecordAttendanceLayout);
        tabRecordAttendanceLayout.setHorizontalGroup(
            tabRecordAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRecordAttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabRecordAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabRecordAttendanceLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnLogout1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(935, Short.MAX_VALUE))
                    .addGroup(tabRecordAttendanceLayout.createSequentialGroup()
                        .addGroup(tabRecordAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        tabRecordAttendanceLayout.setVerticalGroup(
            tabRecordAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabRecordAttendanceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogout1)
                .addGap(152, 152, 152))
        );

        jTabbedPane1.addTab("Record Attendance", tabRecordAttendance);

        tabManagegrades.setBackground(new java.awt.Color(102, 153, 255));
        tabManagegrades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        btnLogout2.setBackground(new java.awt.Color(255, 51, 0));
        btnLogout2.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout2.setText("Logout");
        btnLogout2.addActionListener(this::btnLogout2ActionPerformed);

        jPanel12.setBackground(new java.awt.Color(102, 153, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

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
                false, false, true, true, true, true, true, true, true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblGrades);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(102, 153, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        lblTitle25.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle25.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle25.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle25.setText("  Subject:");

        lblTitle26.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle26.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle26.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle26.setText("Course:");

        lblTitle29.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle29.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle29.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle29.setText("Section:");

        cmbSectionGrades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        lblTitle28.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle28.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle28.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle28.setText("Period:");

        cmbPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Midterm", "Final" }));
        cmbPeriod.addItemListener(this::cmbPeriodItemStateChanged);

        cmbCourseGrades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblTitle25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSubject8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblTitle26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCourseGrades, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblTitle29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSectionGrades, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitle28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSubject8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle25)
                    .addComponent(lblTitle26)
                    .addComponent(lblTitle29)
                    .addComponent(cmbSectionGrades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle28)
                    .addComponent(cmbPeriod)
                    .addComponent(cmbCourseGrades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnCompute.setBackground(new java.awt.Color(255, 255, 0));
        btnCompute.setText("Compute");
        btnCompute.addActionListener(this::btnComputeActionPerformed);

        btnSave1.setBackground(new java.awt.Color(0, 255, 153));
        btnSave1.setText("Save");
        btnSave1.addActionListener(this::btnSave1ActionPerformed);

        btnReset5.setBackground(new java.awt.Color(255, 153, 51));
        btnReset5.setText("Reset");

        jPanel22.setBackground(new java.awt.Color(102, 153, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        lblTitle27.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle27.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTitle27.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle27.setText("Search:");

        btnSearch21.setText("Search");
        btnSearch21.addActionListener(this::btnSearch21ActionPerformed);
        btnSearch21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnSearch21KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblTitle27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle27)
                    .addComponent(txtSearch5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabManagegradesLayout = new javax.swing.GroupLayout(tabManagegrades);
        tabManagegrades.setLayout(tabManagegradesLayout);
        tabManagegradesLayout.setHorizontalGroup(
            tabManagegradesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabManagegradesLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnLogout2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompute)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset5)
                .addGap(16, 16, 16))
            .addGroup(tabManagegradesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabManagegradesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(tabManagegradesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabManagegradesLayout.setVerticalGroup(
            tabManagegradesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabManagegradesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabManagegradesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout2)
                    .addComponent(btnCompute)
                    .addComponent(btnSave1)
                    .addComponent(btnReset5))
                .addGap(50, 50, 50))
        );

        jTabbedPane1.addTab("Manage Grades", tabManagegrades);

        tabViewreport.setBackground(new java.awt.Color(102, 153, 255));
        tabViewreport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        btnLogout3.setBackground(new java.awt.Color(255, 51, 51));
        btnLogout3.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout3.setText("Logout");
        btnLogout3.addActionListener(this::btnLogout3ActionPerformed);

        jPanel13.setBackground(new java.awt.Color(102, 153, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        lblReportType.setBackground(new java.awt.Color(255, 255, 255));
        lblReportType.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblReportType.setForeground(new java.awt.Color(255, 255, 255));
        lblReportType.setText("Report Type:");

        btnGenerate.setBackground(new java.awt.Color(102, 255, 51));
        btnGenerate.setText("Generate");

        btnPrint.setBackground(new java.awt.Color(204, 204, 204));
        btnPrint.setText("Print");

        lblReportType1.setBackground(new java.awt.Color(255, 255, 255));
        lblReportType1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblReportType1.setForeground(new java.awt.Color(255, 255, 255));
        lblReportType1.setText("Section:");

        cmbReportCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        lblReportType2.setBackground(new java.awt.Color(255, 255, 255));
        lblReportType2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblReportType2.setForeground(new java.awt.Color(255, 255, 255));
        lblReportType2.setText("Course:");

        cmbReportSection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblReportType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbReportType, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblReportType2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbReportCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblReportType1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbReportSection, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbReportType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReportType)
                    .addComponent(btnGenerate)
                    .addComponent(btnPrint)
                    .addComponent(lblReportType1)
                    .addComponent(cmbReportCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReportType2)
                    .addComponent(cmbReportSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(102, 153, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        tblMystudent3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Full Name", "Course", "Section", "Email"
            }
        ));
        jScrollPane4.setViewportView(tblMystudent3);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabViewreportLayout = new javax.swing.GroupLayout(tabViewreport);
        tabViewreport.setLayout(tabViewreportLayout);
        tabViewreportLayout.setHorizontalGroup(
            tabViewreportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewreportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabViewreportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(tabViewreportLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnLogout3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabViewreportLayout.setVerticalGroup(
            tabViewreportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabViewreportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout3)
                .addGap(10, 10, 10))
        );

        jTabbedPane1.addTab("View Reports", tabViewreport);

        javax.swing.GroupLayout panelCenterLayout = new javax.swing.GroupLayout(panelCenter);
        panelCenter.setLayout(panelCenterLayout);
        panelCenterLayout.setHorizontalGroup(
            panelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelCenterLayout.setVerticalGroup(
            panelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelCenter, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void loadStudentsBySubject() {
    // TEMPORARY: student_subjects table is missing, so show all students
    loadFilteredStudents();
    
    /* COMMENTED – will use when student_subjects table exists
    if (cmbSubject.getSelectedIndex() == -1) return;
    String selected = cmbSubject.getSelectedItem().toString();
    String subjectCode = selected.split(" - ")[0];
    String sql = "SELECT s.student_id, s.full_name, s.course, s.section, s.email " +
                 "FROM students s " +
                 "JOIN student_subjects ss ON s.student_id = ss.student_id " +
                 "WHERE ss.subject_code = ?";
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, subjectCode);
        java.sql.ResultSet rs = pst.executeQuery();
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new String[]{"Student ID", "Full Name", "Course", "Section", "Email"}, 0);
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("student_id"),
                rs.getString("full_name"),
                rs.getString("course"),
                rs.getString("section"),
                rs.getString("email")
            });
        }
        tblMystudent.setModel(model);
        tblMystudent.setDefaultEditor(Object.class, null);
        updateTotalStudents();   // ✅ updates count
        rs.close();
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage());
    }
    */
}
    

 
 private void loadFilteredStudents() {
    String selectedCourse = (String) cmbCourseMyStudent.getSelectedItem();
    
    String sql = "SELECT * FROM students WHERE 1=1";
    
    // Course filter
    if (selectedCourse != null && !selectedCourse.equals("All") && !selectedCourse.equals(" ")) {
        sql += " AND course = '" + selectedCourse + "'";
    }
    
    
    // Sorting: ACT → BSCS → BSIT, then by section
    sql += " ORDER BY FIELD(course, 'ACT', 'BSCS', 'BSIT'), section ASC";
    
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.PreparedStatement pst = conn.prepareStatement(sql);
         java.sql.ResultSet rs = pst.executeQuery()) {
        
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Student ID", "Full Name", "Course", "Section", "Email"}, 0);
        
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("student_id"),
                rs.getString("full_name"),
                rs.getString("course"),
                rs.getString("section"),
                rs.getString("email")
            });
        }
        tblMystudent.setModel(model);
        tblMystudent.setDefaultEditor(Object.class, null);
        updateTotalStudents();
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage());
    }
}

 
private void searchStudents(String keyword) {
    try {
        java.sql.Connection conn = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM students WHERE " +
                     "student_id LIKE ? OR " +
                     "full_name LIKE ? OR " +
                     "course LIKE ? OR " +
                     "section LIKE ? OR " +
                     "email LIKE ?";

        java.sql.PreparedStatement pst = conn.prepareStatement(sql);

        String searchValue = "%" + keyword + "%";

        pst.setString(1, searchValue);
        pst.setString(2, searchValue);
        pst.setString(3, searchValue);
        pst.setString(4, searchValue);
        pst.setString(5, searchValue);

        java.sql.ResultSet rs = pst.executeQuery();

        javax.swing.table.DefaultTableModel model =
            new javax.swing.table.DefaultTableModel(
                new String[]{"Student ID", "Full Name", "Course", "Section", "Email"}, 0
            );

        while(rs.next()){
            model.addRow(new Object[]{
                rs.getString("student_id"),
                rs.getString("full_name"),
                rs.getString("course"),
                rs.getString("section"),
                rs.getString("email")
            });
        }

        tblMystudent.setModel(model);
        tblMystudent.setDefaultEditor(Object.class, null);
        updateTotalStudents();

        conn.close();

    } catch(Exception e){
        javax.swing.JOptionPane.showMessageDialog(this,
            "Search Error: " + e.getMessage());
    }
}

private void updateTotalStudents() {
    int rowCount = tblMystudent.getRowCount();
    lblTotalstudents1.setText("Total Students: " + rowCount);
}
    
    
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?",
            "Logout",
            javax.swing.JOptionPane.YES_NO_OPTION);
        if(confirm == javax.swing.JOptionPane.YES_OPTION){
            new LoginForm().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnLogout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout1ActionPerformed
        // TODO add your handling code here:
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
        "Are you sure you want to logout?",
        "Logout",
        javax.swing.JOptionPane.YES_NO_OPTION);

    if(confirm == javax.swing.JOptionPane.YES_OPTION){
        new LoginForm().setVisible(true);
        this.dispose();
    }
    }//GEN-LAST:event_btnLogout1ActionPerformed

    private void btnLogout2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout2ActionPerformed
        // TODO add your handling code here:
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
        "Are you sure you want to logout?",
        "Logout",
        javax.swing.JOptionPane.YES_NO_OPTION);

    if(confirm == javax.swing.JOptionPane.YES_OPTION){
        new LoginForm().setVisible(true);
        this.dispose();
    }
    }//GEN-LAST:event_btnLogout2ActionPerformed

    private void btnLogout3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout3ActionPerformed
        // TODO add your handling code here:
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
        "Are you sure you want to logout?",
        "Logout",
        javax.swing.JOptionPane.YES_NO_OPTION);

    if(confirm == javax.swing.JOptionPane.YES_OPTION){
        new LoginForm().setVisible(true);
        this.dispose();
    }
    }//GEN-LAST:event_btnLogout3ActionPerformed

    private void btnMysearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMysearchActionPerformed
        // TODO add your handling code here:
          String keyword = txtMySearch.getText();

    if(keyword.isEmpty()){
        loadFilteredStudents(); // if empty search, reload all
    } else {
        searchStudents(keyword);
    }
    }//GEN-LAST:event_btnMysearchActionPerformed

    private void txtMySearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMySearchKeyReleased
        // TODO add your handling code here:
        if(txtMySearch.getText().isEmpty()){
        loadFilteredStudents();
    }
    }//GEN-LAST:event_txtMySearchKeyReleased

    private void cmbPeriodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPeriodItemStateChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cmbPeriodItemStateChanged

    private void btnComputeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComputeActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btnComputeActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnSearch21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch21ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnSearch21ActionPerformed

    private void btnSearch21KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSearch21KeyReleased
        // TODO add your handling code here:
         loadGradesData();
    }//GEN-LAST:event_btnSearch21KeyReleased

    
    // ==================== RECORD ATTENDANCE METHODS ====================

private void loadSubjectsForAttendance() {
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.PreparedStatement pst = conn.prepareStatement("SELECT subject_code, subject_name FROM subjects");
         java.sql.ResultSet rs = pst.executeQuery()) {
        cmbSubjectAttendance.removeAllItems();
        while (rs.next()) {
            String code = rs.getString("subject_code");
            String name = rs.getString("subject_name");
            cmbSubjectAttendance.addItem(code + " - " + name);
        }
        if (cmbSubjectAttendance.getItemCount() > 0) {
            cmbSubjectAttendance.setSelectedIndex(0);
            loadStudentsForAttendance();
        }
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading subjects: " + e.getMessage());
    }
}


private void cleanupOrphanedAttendance() {
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.Statement stmt = conn.createStatement()) {
        stmt.executeUpdate("DELETE FROM attendance WHERE student_id NOT IN (SELECT student_id FROM students)");
        System.out.println("Orphaned attendance records removed.");
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error cleaning attendance: " + e.getMessage());
    }
}

private void loadStudentsForAttendance() {
    if (cmbSubjectAttendance.getSelectedIndex() == -1) {
        loadAllStudentsForAttendanceWithoutSubject();
        return;
    }

    String selected = cmbSubjectAttendance.getSelectedItem().toString();
    String subjectCode = selected.split(" - ")[0];
    String subjectName = selected.split(" - ")[1];

    String courseFilter = (String) cmbCourseAttendance.getSelectedItem();
    String sectionFilter = (String) cmbSectionAttendance.getSelectedItem();

    String baseSql = "SELECT student_id, full_name, course, section FROM students WHERE 1=1";
    List<String> conditions = new ArrayList<>();
    List<String> params = new ArrayList<>();

    if (courseFilter != null && !courseFilter.equals("All")
            && !courseFilter.equals(" ") && !courseFilter.equals("No course data")) {
        conditions.add("course = ?");
        params.add(courseFilter);
    }
    if (sectionFilter != null && !sectionFilter.equals("All")
            && !sectionFilter.equals(" ") && !sectionFilter.equals("No section data")) {
        conditions.add("section = ?");
        params.add(sectionFilter);
    }

    StringBuilder fullSql = new StringBuilder(baseSql);
    if (!conditions.isEmpty()) {
        fullSql.append(" AND ").append(String.join(" AND ", conditions));
    }
    fullSql.append(" ORDER BY course, section, student_id");

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(fullSql.toString())) {

        for (int i = 0; i < params.size(); i++) {
            pst.setString(i + 1, params.get(i));
        }

        try (ResultSet rs = pst.executeQuery()) {
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"Student ID", "Full Name", "Subject Name", "Course", "Section", "Status"}, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("student_id"),
                    rs.getString("full_name"),
                    subjectName,
                    rs.getString("course"),
                    rs.getString("section"),
                    ""
                });
            }
            tblRecord.setModel(model);

            tblRecord.getModel().addTableModelListener(e -> {
                if (e.getColumn() == 5) updateAttendanceSummary();
            });

            JComboBox<String> statusCombo =
                new JComboBox<>(new String[]{"", "Present", "Absent", "Late"});
            tblRecord.getColumnModel().getColumn(5).setCellEditor(
                new DefaultCellEditor(statusCombo));

            for (int i = 0; i < 5; i++) {
                tblRecord.getColumnModel().getColumn(i).setCellEditor(null);
            }

            updateAttendanceSummary();
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage());
    }
}

private void loadAllStudentsForAttendanceWithoutSubject() {
    String courseFilter = (String) cmbCourseAttendance.getSelectedItem();
    String sectionFilter = (String) cmbSectionAttendance.getSelectedItem();

    String baseSql = "SELECT student_id, full_name, course, section FROM students WHERE 1=1";
    List<String> conditions = new ArrayList<>();
    List<String> params = new ArrayList<>();

    if (courseFilter != null && !courseFilter.equals("All")
            && !courseFilter.equals(" ") && !courseFilter.equals("No course data")) {
        conditions.add("course = ?");
        params.add(courseFilter);
    }
    if (sectionFilter != null && !sectionFilter.equals("All")
            && !sectionFilter.equals(" ") && !sectionFilter.equals("No section data")) {
        conditions.add("section = ?");
        params.add(sectionFilter);
    }

    StringBuilder fullSql = new StringBuilder(baseSql);
    if (!conditions.isEmpty()) {
        fullSql.append(" AND ").append(String.join(" AND ", conditions));
    }
    fullSql.append(" ORDER BY course, section, student_id");

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(fullSql.toString())) {

        for (int i = 0; i < params.size(); i++) {
            pst.setString(i + 1, params.get(i));
        }

        try (ResultSet rs = pst.executeQuery()) {
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"Student ID", "Full Name", "Subject Name", "Course", "Section", "Status"}, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("student_id"),
                    rs.getString("full_name"),
                    "N/A",
                    rs.getString("course"),
                    rs.getString("section"),
                    ""
                });
            }
            tblRecord.setModel(model);

            tblRecord.getModel().addTableModelListener(e -> {
                if (e.getColumn() == 5) updateAttendanceSummary();
            });

            JComboBox<String> statusCombo =
                new JComboBox<>(new String[]{"", "Present", "Absent", "Late"});
            tblRecord.getColumnModel().getColumn(5).setCellEditor(
                new DefaultCellEditor(statusCombo));

            for (int i = 0; i < 5; i++) {
                tblRecord.getColumnModel().getColumn(i).setCellEditor(null);
            }

            updateAttendanceSummary();
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage());
    }
}

private void updateAttendanceSummary() {
    int total = tblRecord.getRowCount();
    int present = 0, absent = 0, late = 0;
    for (int i = 0; i < total; i++) {
        String status = tblRecord.getValueAt(i, 5).toString();  // column 5 = Status
        if (status.equals("Present")) present++;
        else if (status.equals("Absent")) absent++;
        else if (status.equals("Late")) late++;
    }
    lblPresent.setText("Present: " + present);
    lblAbsent.setText("Absent: " + absent);
    lblLate.setText("Late: " + late);
    lblTotal.setText("Total: " + total);
}

private void markAllPresent() {
    for (int i = 0; i < tblRecord.getRowCount(); i++) {
        tblRecord.setValueAt("Present", i, 5);
    }
    updateAttendanceSummary();
}

private void markAllAbsent() {
    for (int i = 0; i < tblRecord.getRowCount(); i++) {
        tblRecord.setValueAt("Absent", i, 5);
    }
    updateAttendanceSummary();
}

private void resetAttendanceStatus() {
    for (int i = 0; i < tblRecord.getRowCount(); i++) {
        tblRecord.setValueAt("", i, 5);
    }
    updateAttendanceSummary();
}

private void loadCoursesAndSectionsForAttendance() {
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.Statement stmt = conn.createStatement()) {
        
        // --- Load courses ---
        cmbCourseAttendance.removeAllItems();
        cmbCourseAttendance.addItem("All");   // first option
        java.sql.ResultSet rsCourses = stmt.executeQuery("SELECT DISTINCT course FROM students WHERE course IS NOT NULL AND course != ''");
        while (rsCourses.next()) {
            cmbCourseAttendance.addItem(rsCourses.getString("course"));
        }
        if (cmbCourseAttendance.getItemCount() == 1) {
            cmbCourseAttendance.addItem("No course data");
        }
        cmbCourseAttendance.setSelectedIndex(0);
        
        // --- Load sections ---
        cmbSectionAttendance.removeAllItems();          // ← corrected name
        cmbSectionAttendance.addItem("All");           // ← corrected name
        java.sql.ResultSet rsSections = stmt.executeQuery("SELECT DISTINCT section FROM students WHERE section IS NOT NULL AND section != ''");
        while (rsSections.next()) {
            cmbSectionAttendance.addItem(rsSections.getString("section"));
        }
        if (cmbSectionAttendance.getItemCount() == 1) {
            cmbSectionAttendance.addItem("No section data");
        }
        cmbSectionAttendance.setSelectedIndex(0);
        
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading courses/sections: " + e.getMessage());
    }
}

private void saveAttendance() {
    if (cmbSubjectAttendance.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this, "Please select a subject.");
        return;
    }
    if (txtDate.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Date is required.");
        return;
    }

    String course = cmbCourseAttendance.getSelectedItem().toString().trim();
    String section = cmbSectionAttendance.getSelectedItem().toString().trim();

    if (course.isEmpty() || course.equals("No course data")
            || section.isEmpty() || section.equals("No section data")) {
        JOptionPane.showMessageDialog(this, "Please select a valid Course and Section.");
        return;
    }

    // 🚫 Reject "All"
    if (course.equals("All") || section.equals("All")) {
        JOptionPane.showMessageDialog(this, "Please select a specific Course and Section (not 'All').");
        return;
    }

    String selected = cmbSubjectAttendance.getSelectedItem().toString();
    String subjectCode = selected.split(" - ")[0];
    String date = txtDate.getText().trim();

    try (Connection conn = DatabaseConnection.getConnection()) {
        // Updated SQL: now updates course and section as well on duplicate
        String sql = "INSERT INTO attendance (student_id, subject_code, course, section, date, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?) "
                   + "ON DUPLICATE KEY UPDATE status = ?, course = VALUES(course), section = VALUES(section)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            int saved = 0;
            for (int i = 0; i < tblRecord.getRowCount(); i++) {
                String studentId = tblRecord.getValueAt(i, 0).toString();
                String status = tblRecord.getValueAt(i, 5).toString();
                if (status == null || status.trim().isEmpty()) continue;

                pst.setString(1, studentId);
                pst.setString(2, subjectCode);
                pst.setString(3, course);
                pst.setString(4, section);
                pst.setString(5, date);
                pst.setString(6, status);
                pst.setString(7, status);   // for UPDATE status
                // course and section are handled by VALUES() in SQL, no extra params needed
                pst.addBatch();
                saved++;
            }

            if (saved > 0) {
                pst.executeBatch();
                JOptionPane.showMessageDialog(this, "Attendance saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No status selected to save.");
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error saving attendance: " + e.getMessage());
    }
}

private void viewAttendanceHistory() {
    javax.swing.JDialog historyDialog = new javax.swing.JDialog(this, "Attendance History", true);
    historyDialog.setSize(850, 400);   // slightly wider to fit full name
    historyDialog.setLocationRelativeTo(this);
    
    javax.swing.JTable historyTable = new javax.swing.JTable();
    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(historyTable);
    historyDialog.add(scrollPane);
    
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.Statement stmt = conn.createStatement();
         java.sql.ResultSet rs = stmt.executeQuery(
             "SELECT a.student_id, st.full_name, s.subject_name, a.course, a.section, a.date, a.status " +
             "FROM attendance a " +
             "JOIN subjects s ON a.subject_code = s.subject_code " +
             "JOIN students st ON a.student_id = st.student_id " +
             "ORDER BY a.date DESC")) {
        
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new String[]{"Student ID", "Full Name", "Subject Name", "Course", "Section", "Date", "Status"}, 0
        );
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("student_id"),
                rs.getString("full_name"),
                rs.getString("subject_name"),
                rs.getString("course"),
                rs.getString("section"),
                rs.getString("date"),
                rs.getString("status")
            });
        }
        historyTable.setModel(model);
        historyTable.setDefaultEditor(Object.class, null);
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(historyDialog, "Error loading history: " + e.getMessage());
    }
    
    historyDialog.setVisible(true);
}

private void setCurrentDate() {
    java.time.LocalDate today = java.time.LocalDate.now();
    txtDate.setText(today.toString());
}
    
// ==================== MANAGE GRADES METHODS ====================

private void loadSubjectsForGrades() {
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.PreparedStatement pst = conn.prepareStatement("SELECT subject_code, subject_name FROM subjects");
         java.sql.ResultSet rs = pst.executeQuery()) {
        cmbSubject8.removeAllItems();
        while (rs.next()) {
            String code = rs.getString("subject_code");
            String name = rs.getString("subject_name");
            cmbSubject8.addItem(code + " - " + name);
        }
        // Always load grades data (students) even if no subjects exist
        loadGradesData();
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading subjects: " + e.getMessage());
        // Still try to load students (without subject filter)
        loadGradesData();
    }
}

private void setRatingCellRenderer() {
    // Format numbers to always show two decimal places (e.g., 1.00, 2.25)
    javax.swing.table.DefaultTableCellRenderer renderer = new javax.swing.table.DefaultTableCellRenderer() {
        @Override
        protected void setValue(Object value) {
            if (value instanceof Number) {
                setText(String.format("%.2f", ((Number) value).doubleValue()));
            } else {
                super.setValue(value);
            }
        }
    };
    renderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    
    // Apply to Period Rating column (index 10)
    if (tblGrades.getColumnCount() > COL_PERIOD_RATING) {
        tblGrades.getColumnModel().getColumn(COL_PERIOD_RATING).setCellRenderer(renderer);
    }
    // Apply to Final Average Rating column (index 12)
    if (tblGrades.getColumnCount() > COL_FINAL_AVE) {
        tblGrades.getColumnModel().getColumn(COL_FINAL_AVE).setCellRenderer(renderer);
    }
}

private void loadGradesData() {
    String period = (String) cmbPeriod.getSelectedItem();
    if (period == null) return;
    
    String subjectCode = "";
    if (cmbSubject8.getSelectedIndex() != -1 && cmbSubject8.getItemCount() > 0) {
        String selected = cmbSubject8.getSelectedItem().toString();
        subjectCode = selected.split(" - ")[0];
    }
    
    // Get filters (use your actual combo box names)
    String courseFilter = (String) cmbCourseGrades.getSelectedItem();   // course filter
    String sectionFilter = (String) cmbSectionGrades.getSelectedItem(); // section filter
    String searchKeyword = txtSearch5.getText().trim();
    
    // Build student query with filters
    StringBuilder studentSql = new StringBuilder("SELECT student_id, full_name, course, section FROM students WHERE 1=1");
    if (courseFilter != null && !courseFilter.equals("All")) {
        studentSql.append(" AND course = '").append(courseFilter).append("'");
    }
    if (sectionFilter != null && !sectionFilter.equals("All")) {
        studentSql.append(" AND section = '").append(sectionFilter).append("'");
    }
    if (!searchKeyword.isEmpty()) {
        studentSql.append(" AND (student_id LIKE '%").append(searchKeyword).append("%' OR full_name LIKE '%").append(searchKeyword).append("%')");
    }
    studentSql.append(" ORDER BY FIELD(course, 'ACT', 'BSCS', 'BSIT'), section, student_id");
    
    DefaultTableModel model = (DefaultTableModel) tblGrades.getModel();
    model.setRowCount(0);
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(studentSql.toString());
         ResultSet rs = pst.executeQuery()) {
        
        String tableName = period.equalsIgnoreCase("Midterm") ? "midterm" : "`final`";
        
        while (rs.next()) {
            String studentId = rs.getString("student_id");
            String fullName = rs.getString("full_name");
            // We ignore course and section here – they are only for filtering, not displayed
            
            double attendance = 0, participation = 0, quiz1 = 0, quiz2 = 0, quiz3 = 0, quiz4 = 0, exam = 0;
            double periodGrade = 0, periodRating = 0, finalAve = 0;
            String periodRemarks = "", finalRemarks = "";
            
            if (!subjectCode.isEmpty()) {
                String fetchSql = "SELECT attendance, participation, quiz1, quiz2, quiz3, quiz4, exam_score, " +
                                  "period_grade, period_rating, period_remarks, final_ave_rating, final_remarks " +
                                  "FROM " + tableName + " WHERE student_id=? AND subject_code=?";
                try (PreparedStatement pstFetch = conn.prepareStatement(fetchSql)) {
                    pstFetch.setString(1, studentId);
                    pstFetch.setString(2, subjectCode);
                    ResultSet rsFetch = pstFetch.executeQuery();
                    if (rsFetch.next()) {
                        attendance = rsFetch.getDouble("attendance");
                        participation = rsFetch.getDouble("participation");
                        quiz1 = rsFetch.getDouble("quiz1");
                        quiz2 = rsFetch.getDouble("quiz2");
                        quiz3 = rsFetch.getDouble("quiz3");
                        quiz4 = rsFetch.getDouble("quiz4");
                        exam = rsFetch.getDouble("exam_score");
                        periodGrade = rsFetch.getDouble("period_grade");
                        periodRating = rsFetch.getDouble("period_rating");
                        periodRemarks = rsFetch.getString("period_remarks");
                        finalAve = rsFetch.getDouble("final_ave_rating");
                        finalRemarks = rsFetch.getString("final_remarks");
                    }
                    rsFetch.close();
                }
            }
            model.addRow(new Object[]{
                studentId, fullName,
                attendance, participation,
                quiz1, quiz2, quiz3, quiz4, exam,
                periodGrade, periodRating, periodRemarks,
                finalAve, finalRemarks
            });
        }
        tblGrades.setModel(model);
        
        // Make non-editable columns read-only (same as before)
        if (tblGrades.getColumnCount() > COL_FINAL_REMARKS) {
            tblGrades.getColumnModel().getColumn(COL_PERIOD_GRADE).setCellEditor(null);
            tblGrades.getColumnModel().getColumn(COL_PERIOD_RATING).setCellEditor(null);
            tblGrades.getColumnModel().getColumn(COL_PERIOD_REMARKS).setCellEditor(null);
            tblGrades.getColumnModel().getColumn(COL_FINAL_AVE).setCellEditor(null);
            tblGrades.getColumnModel().getColumn(COL_FINAL_REMARKS).setCellEditor(null);
        }
        setRatingCellRenderer();
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading grades: " + e.getMessage());
        e.printStackTrace();
    }
}

private String getSelectedSubjectCode() {
    if (cmbSubject8.getSelectedIndex() == -1 || cmbSubject8.getItemCount() == 0) {
        return "";
    }
    String selected = cmbSubject8.getSelectedItem().toString();
    return selected.split(" - ")[0];
}

private void loadCoursesAndSectionsForGrades() {
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.Statement stmt = conn.createStatement()) {
        
        // Load courses into cmbSection23 (rename to cmbCourseFilter if you want)
        cmbCourseGrades.removeAllItems();
        cmbCourseGrades.addItem("All");
        java.sql.ResultSet rsCourses = stmt.executeQuery("SELECT DISTINCT course FROM students WHERE course IS NOT NULL AND course != ''");
        while (rsCourses.next()) {
            cmbCourseGrades.addItem(rsCourses.getString("course"));
        }
        
        // Load sections into cmbCourserecord56d (rename to cmbSectionFilter if you want)
        cmbSectionGrades.removeAllItems();
        cmbSectionGrades.addItem("All");
        java.sql.ResultSet rsSections = stmt.executeQuery("SELECT DISTINCT section FROM students WHERE section IS NOT NULL AND section != ''");
        while (rsSections.next()) {
            cmbSectionGrades.addItem(rsSections.getString("section"));
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading course/section filters: " + e.getMessage());
    }
}

private void computeAllGrades() {
    gradesDirty = true;
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblGrades.getModel();
    for (int row = 0; row < model.getRowCount(); row++) {
        try {
            double attendance    = toDouble(model.getValueAt(row, COL_ATTENDANCE));
            double participation = toDouble(model.getValueAt(row, COL_PARTICIPATION));
            double q1  = toDouble(model.getValueAt(row, COL_QUIZ1));
            double q2  = toDouble(model.getValueAt(row, COL_QUIZ2));
            double q3  = toDouble(model.getValueAt(row, COL_QUIZ3));
            double q4  = toDouble(model.getValueAt(row, COL_QUIZ4));
            double exam = toDouble(model.getValueAt(row, COL_EXAM));

            // Quiz average (max 10)
            double quizAve = (q1 + q2 + q3 + q4) / 4.0;

            // Period Grade (max 100)
            double periodGrade = (attendance * 0.10)
                               + (participation * 0.20)
                               + ((quizAve / 10.0) * 30.0)
                               + ((exam / 50.0) * 40.0);

            double rating = convertToRating(periodGrade);
            String remarks = rating <= 3.00 ? "Passed" : "Failed";

            model.setValueAt(round2(periodGrade), row, COL_PERIOD_GRADE);
            model.setValueAt(rating, row, COL_PERIOD_RATING);
            model.setValueAt(remarks, row, COL_PERIOD_REMARKS);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error on row " + (row+1) + ": " + e.getMessage());
        }
    }
    // After computing current period, try to update final average (if other period exists)
    updateFinalAverage();
    javax.swing.JOptionPane.showMessageDialog(this, "Computation complete!\nClick Save to store results.");
}

private void updateFinalAverage() {
    String currentPeriod = (String) cmbPeriod.getSelectedItem();
    String otherTable = currentPeriod.equalsIgnoreCase("Midterm") ? "`final`" : "midterm";

    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblGrades.getModel();
    try (java.sql.Connection conn = DatabaseConnection.getConnection()) {
        for (int row = 0; row < model.getRowCount(); row++) {
            String studentId = model.getValueAt(row, COL_STUDENT_ID).toString();
            double currentGrade = toDouble(model.getValueAt(row, COL_PERIOD_GRADE));

            // Get other period's grade (0-100) from database
            String sql = "SELECT period_grade FROM " + otherTable + " WHERE student_id=? AND subject_code=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, studentId);
            pst.setString(2, getSelectedSubjectCode());
            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double otherGrade = rs.getDouble("period_grade");
                if (currentGrade > 0 && otherGrade > 0) {
                    double finalGrade = round2((currentGrade + otherGrade) / 2.0);
                    double finalRating = convertToRating(finalGrade);
                    String finalRemarks = finalRating <= 3.00 ? "Passed" : "Failed";
                    model.setValueAt(round2(finalRating), row, COL_FINAL_AVE);
                    model.setValueAt(finalRemarks, row, COL_FINAL_REMARKS);
                }
            }
            rs.close();
            pst.close();
        }
    } catch (Exception e) {
        // Other period not yet saved – ignore
    }
}

private void saveGradesData() {
    String period = (String) cmbPeriod.getSelectedItem();
    String tableName = period.equalsIgnoreCase("Midterm") ? "midterm" : "`final`";
    String subjectCode = getSelectedSubjectCode();
    String section = ""; // or remove from SQL
    
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblGrades.getModel();

    try (java.sql.Connection conn = DatabaseConnection.getConnection()) {
        // Use INSERT ... ON DUPLICATE KEY UPDATE
        String sql = "INSERT INTO " + tableName + " (student_id, full_name, subject_code, course, section, " +
                     "attendance, participation, quiz1, quiz2, quiz3, quiz4, exam_score, " +
                     "period_grade, period_rating, period_remarks, final_ave_rating, final_remarks) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE " +
                     "attendance=VALUES(attendance), participation=VALUES(participation), " +
                     "quiz1=VALUES(quiz1), quiz2=VALUES(quiz2), quiz3=VALUES(quiz3), quiz4=VALUES(quiz4), " +
                     "exam_score=VALUES(exam_score), period_grade=VALUES(period_grade), " +
                     "period_rating=VALUES(period_rating), period_remarks=VALUES(period_remarks), " +
                     "final_ave_rating=VALUES(final_ave_rating), final_remarks=VALUES(final_remarks)";

        java.sql.PreparedStatement pst = conn.prepareStatement(sql);

        for (int row = 0; row < model.getRowCount(); row++) {
            String studentId = model.getValueAt(row, COL_STUDENT_ID).toString();
            
            // Get actual course from students table
            String actualCourse = "";
            String courseSql = "SELECT course FROM students WHERE student_id = ?";
            try (PreparedStatement pstCourse = conn.prepareStatement(courseSql)) {
                pstCourse.setString(1, studentId);
                ResultSet rsCourse = pstCourse.executeQuery();
                if (rsCourse.next()) {
                    actualCourse = rsCourse.getString("course");
                }
            }
            
            pst.setString(1,  studentId);
            pst.setString(2,  model.getValueAt(row, COL_FULL_NAME).toString());
            pst.setString(3,  subjectCode);
            pst.setString(4,  actualCourse);   // store actual course
            pst.setString(5,  section);
            pst.setDouble(6,  toDouble(model.getValueAt(row, COL_ATTENDANCE)));
            pst.setDouble(7,  toDouble(model.getValueAt(row, COL_PARTICIPATION)));
            pst.setDouble(8,  toDouble(model.getValueAt(row, COL_QUIZ1)));
            pst.setDouble(9,  toDouble(model.getValueAt(row, COL_QUIZ2)));
            pst.setDouble(10, toDouble(model.getValueAt(row, COL_QUIZ3)));
            pst.setDouble(11, toDouble(model.getValueAt(row, COL_QUIZ4)));
            pst.setDouble(12, toDouble(model.getValueAt(row, COL_EXAM)));
            pst.setDouble(13, toDouble(model.getValueAt(row, COL_PERIOD_GRADE)));
            pst.setDouble(14, toDouble(model.getValueAt(row, COL_PERIOD_RATING)));
            pst.setString(15, model.getValueAt(row, COL_PERIOD_REMARKS).toString());
            pst.setDouble(16, toDouble(model.getValueAt(row, COL_FINAL_AVE)));
            pst.setString(17, model.getValueAt(row, COL_FINAL_REMARKS).toString());
            pst.addBatch();
        }

        pst.executeBatch();
        javax.swing.JOptionPane.showMessageDialog(this, "Grades saved successfully!");

        // After saving, refresh data to show updated final averages from the other period
        loadGradesData();

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error saving grades: " + e.getMessage());
    }
}

// Helper methods (same as old GradesForm)
private double toDouble(Object val) {
    if (val == null) return 0;
    try { return Double.parseDouble(val.toString()); }
    catch (NumberFormatException e) { return 0; }
}

private double round2(double val) {
    return Math.round(val * 100.0) / 100.0;
}

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

private void resetGradesData() {
    if (!gradesDirty) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "No unsaved changes to reset.\nEdit a score or click Compute first.",
            "Reset", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
        "Reset will clear ALL entered grades (Attendance, Participation, Quizzes, Exam)\n" +
        "and also clear computed grades.\n" +
        "Any unsaved changes will be lost.\nAre you sure?",
        "Confirm Reset",
        javax.swing.JOptionPane.YES_NO_OPTION);
    if (confirm != javax.swing.JOptionPane.YES_OPTION) return;
    
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblGrades.getModel();
    for (int row = 0; row < model.getRowCount(); row++) {
        // Clear editable columns (Attendance to Exam)
        for (int col = COL_ATTENDANCE; col <= COL_EXAM; col++) {
            model.setValueAt(0.0, row, col);
        }
        // Clear computed columns
        model.setValueAt(0.0, row, COL_PERIOD_GRADE);
        model.setValueAt(0.0, row, COL_PERIOD_RATING);
        model.setValueAt("", row, COL_PERIOD_REMARKS);
        model.setValueAt(0.0, row, COL_FINAL_AVE);
        model.setValueAt("", row, COL_FINAL_REMARKS);
    }
    // After reset, the table now has unsaved changes
    gradesDirty = true;
    javax.swing.JOptionPane.showMessageDialog(this, "Grades have been reset. Click Save to store.");
}

// ==================== VIEW REPORTS METHODS ====================

private void loadStudentListReport() {
    String courseFilter = (String) cmbReportCourse.getSelectedItem();
    String sectionFilter = (String) cmbReportSection.getSelectedItem();
    
    StringBuilder sql = new StringBuilder("SELECT student_id, full_name, course, section, email FROM students WHERE 1=1");
    if (courseFilter != null && !courseFilter.equals("All")) {
        sql.append(" AND course = '").append(courseFilter).append("'");
    }
    if (sectionFilter != null && !sectionFilter.equals("All")) {
        sql.append(" AND section = '").append(sectionFilter).append("'");
    }
    sql.append(" ORDER BY course, section, student_id");
    
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.PreparedStatement pst = conn.prepareStatement(sql.toString());
         java.sql.ResultSet rs = pst.executeQuery()) {
        
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new String[]{"Student ID", "Full Name", "Course", "Section", "Email"}, 0
        );
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("student_id"),
                rs.getString("full_name"),
                rs.getString("course"),
                rs.getString("section"),
                rs.getString("email")
            });
        }
        tblMystudent3.setModel(model);
        tblMystudent3.setDefaultEditor(Object.class, null);
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading student list: " + e.getMessage());
    }
}

private void loadAttendanceReport() {
    String courseFilter = (String) cmbReportCourse.getSelectedItem();
    String sectionFilter = (String) cmbReportSection.getSelectedItem();
    
    StringBuilder sql = new StringBuilder(
        "SELECT s.student_id, s.full_name, " +
        "SUM(CASE WHEN a.status = 'Present' THEN 1 ELSE 0 END) AS present_count, " +
        "SUM(CASE WHEN a.status = 'Absent' THEN 1 ELSE 0 END) AS absent_count, " +
        "SUM(CASE WHEN a.status = 'Late' THEN 1 ELSE 0 END) AS late_count, " +
        "COUNT(*) AS total_days " +
        "FROM attendance a " +
        "JOIN students s ON a.student_id = s.student_id WHERE 1=1"
    );
    if (courseFilter != null && !courseFilter.equals("All")) {
        sql.append(" AND a.course = '").append(courseFilter).append("'");
    }
    if (sectionFilter != null && !sectionFilter.equals("All")) {
        sql.append(" AND a.section = '").append(sectionFilter).append("'");
    }
    sql.append(" GROUP BY s.student_id, s.full_name");
    
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.Statement stmt = conn.createStatement();
         java.sql.ResultSet rs = stmt.executeQuery(sql.toString())) {
        
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new String[]{"Student ID", "Full Name", "Present", "Absent", "Late", "Rate %"}, 0
        );
        while (rs.next()) {
            int present = rs.getInt("present_count");
            int total = rs.getInt("total_days");
            double rate = total == 0 ? 0 : (present * 100.0 / total);
            model.addRow(new Object[]{
                rs.getString("student_id"),
                rs.getString("full_name"),
                present,
                rs.getInt("absent_count"),
                rs.getInt("late_count"),
                String.format("%.2f", rate) + "%"
            });
        }
        tblMystudent3.setModel(model);
        tblMystudent3.setDefaultEditor(Object.class, null);
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading attendance report: " + e.getMessage());
    }
}

private void loadGradeReportMidterm() {
    String courseFilter = (String) cmbReportCourse.getSelectedItem();
    String sectionFilter = (String) cmbReportSection.getSelectedItem();
    
    StringBuilder sql = new StringBuilder(
        "SELECT m.student_id, m.full_name, m.subject_code, 'Midterm' AS period, " +
        "m.period_rating, m.period_remarks " +
        "FROM midterm m " +
        "JOIN students s ON m.student_id = s.student_id " +   // ← only existing students
        "WHERE 1=1"
    );
    if (courseFilter != null && !courseFilter.equals("All")) {
        sql.append(" AND m.course = '").append(courseFilter).append("'");
    }
    if (sectionFilter != null && !sectionFilter.equals("All")) {
        sql.append(" AND m.section = '").append(sectionFilter).append("'");
    }
    sql.append(" ORDER BY m.student_id, m.subject_code");
    
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.Statement stmt = conn.createStatement();
         java.sql.ResultSet rs = stmt.executeQuery(sql.toString())) {
        
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new String[]{"Student ID", "Full Name", "Subject", "Period", "Period Rating", "Period Remarks"}, 0
        );
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("student_id"),
                rs.getString("full_name"),
                rs.getString("subject_code"),
                rs.getString("period"),
                String.format("%.2f", rs.getDouble("period_rating")),
                rs.getString("period_remarks")
            });
        }
        tblMystudent3.setModel(model);
        tblMystudent3.setDefaultEditor(Object.class, null);
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading midterm report: " + e.getMessage());
    }
}

private void loadGradeReportFinal() {
    String courseFilter = (String) cmbReportCourse.getSelectedItem();
    String sectionFilter = (String) cmbReportSection.getSelectedItem();
    
    StringBuilder sql = new StringBuilder(
        "SELECT f.student_id, f.full_name, f.subject_code, 'Final' AS period, " +
        "f.period_rating, f.period_remarks " +
        "FROM `final` f " +
        "JOIN students s ON f.student_id = s.student_id " +  // ← only existing students
        "WHERE 1=1"
    );
    if (courseFilter != null && !courseFilter.equals("All")) {
        sql.append(" AND f.course = '").append(courseFilter).append("'");
    }
    if (sectionFilter != null && !sectionFilter.equals("All")) {
        sql.append(" AND f.section = '").append(sectionFilter).append("'");
    }
    sql.append(" ORDER BY f.student_id, f.subject_code");
    
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.Statement stmt = conn.createStatement();
         java.sql.ResultSet rs = stmt.executeQuery(sql.toString())) {
        
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new String[]{"Student ID", "Full Name", "Subject", "Period", "Period Rating", "Period Remarks"}, 0
        );
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("student_id"),
                rs.getString("full_name"),
                rs.getString("subject_code"),
                rs.getString("period"),
                String.format("%.2f", rs.getDouble("period_rating")),
                rs.getString("period_remarks")
            });
        }
        tblMystudent3.setModel(model);
        tblMystudent3.setDefaultEditor(Object.class, null);
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading final report: " + e.getMessage());
    }
}

private void loadGradeReportFinalAverage() {
    String courseFilter = (String) cmbReportCourse.getSelectedItem();
    String sectionFilter = (String) cmbReportSection.getSelectedItem();
    
    // Build the sub‑query with JOIN on students so only existing students are included
    StringBuilder midSql = new StringBuilder(
        "SELECT m.student_id, m.full_name, m.subject_code, m.period_grade, m.course, m.section " +
        "FROM midterm m JOIN students s ON m.student_id = s.student_id WHERE 1=1"
    );
    if (courseFilter != null && !courseFilter.equals("All")) {
        midSql.append(" AND m.course = '").append(courseFilter).append("'");
    }
    if (sectionFilter != null && !sectionFilter.equals("All")) {
        midSql.append(" AND m.section = '").append(sectionFilter).append("'");
    }
    
    StringBuilder finalSql = new StringBuilder(
        "SELECT f.student_id, f.full_name, f.subject_code, f.period_grade, f.course, f.section " +
        "FROM `final` f JOIN students s ON f.student_id = s.student_id WHERE 1=1"
    );
    if (courseFilter != null && !courseFilter.equals("All")) {
        finalSql.append(" AND f.course = '").append(courseFilter).append("'");
    }
    if (sectionFilter != null && !sectionFilter.equals("All")) {
        finalSql.append(" AND f.section = '").append(sectionFilter).append("'");
    }
    
    StringBuilder sql = new StringBuilder(
        "SELECT combined.student_id, combined.full_name, combined.subject_code, " +
        "AVG(combined.period_grade) AS avg_grade " +
        "FROM ( " +
        midSql.toString() +
        " UNION ALL " +
        finalSql.toString() +
        ") AS combined " +
        "GROUP BY combined.student_id, combined.full_name, combined.subject_code " +
        "ORDER BY combined.student_id, combined.subject_code"
    );
    
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.Statement stmt = conn.createStatement();
         java.sql.ResultSet rs = stmt.executeQuery(sql.toString())) {
        
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new String[]{"Student ID", "Full Name", "Subject", "Final Average Rating", "Final Remarks"}, 0
        );
        while (rs.next()) {
            double avgGrade = rs.getDouble("avg_grade");
            double rating = convertToRating(avgGrade);
            String remarks = rating <= 3.00 ? "Passed" : "Failed";
            model.addRow(new Object[]{
                rs.getString("student_id"),
                rs.getString("full_name"),
                rs.getString("subject_code"),
                String.format("%.2f", rating),
                remarks
            });
        }
        tblMystudent3.setModel(model);
        tblMystudent3.setDefaultEditor(Object.class, null);
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading final average report: " + e.getMessage());
    }
}

private void printReport() {
    try {
        boolean complete = tblMystudent3.print(JTable.PrintMode.FIT_WIDTH);
        if (complete) {
            javax.swing.JOptionPane.showMessageDialog(this, "Printing completed.");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Printing cancelled.", "Print", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (java.awt.print.PrinterException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Printing failed: " + e.getMessage());
    }
}

private void refreshReport() {
    String selected = cmbReportType.getSelectedItem().toString();
    switch (selected) {
        case "Student List":
            loadStudentListReport();
            break;
        case "Attendance Report":
            loadAttendanceReport();
            break;
        case "Grade Report Midterm":
            loadGradeReportMidterm();
            break;
        case "Grade Report Final":
            loadGradeReportFinal();
            break;
        case "Grade Report Final Average":
            loadGradeReportFinalAverage();
            break;
    }
}

private void loadCoursesAndSectionsForReports() {
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         java.sql.Statement stmt = conn.createStatement()) {
        
        // Load courses
        cmbReportCourse.removeAllItems();
        cmbReportCourse.addItem("All");
        java.sql.ResultSet rsCourses = stmt.executeQuery("SELECT DISTINCT course FROM students WHERE course IS NOT NULL AND course != ''");
        while (rsCourses.next()) {
            cmbReportCourse.addItem(rsCourses.getString("course"));
        }
        
        // Load sections
        cmbReportSection.removeAllItems();
        cmbReportSection.addItem("All");
        java.sql.ResultSet rsSections = stmt.executeQuery("SELECT DISTINCT section FROM students WHERE section IS NOT NULL AND section != ''");
        while (rsSections.next()) {
            cmbReportSection.addItem(rsSections.getString("section"));
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading report filters: " + e.getMessage());
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ProfessorDashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAllAbsent;
    private javax.swing.JButton btnAllPresent;
    private javax.swing.JButton btnCompute;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnLogout1;
    private javax.swing.JButton btnLogout2;
    private javax.swing.JButton btnLogout3;
    private javax.swing.JButton btnMysearch;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnReset5;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSearch21;
    private javax.swing.JButton btnView;
    private javax.swing.JComboBox<String> cmbCourseAttendance;
    private javax.swing.JComboBox<String> cmbCourseGrades;
    private javax.swing.JComboBox<String> cmbCourseMyStudent;
    private javax.swing.JComboBox<String> cmbPeriod;
    private javax.swing.JComboBox<String> cmbReportCourse;
    private javax.swing.JComboBox<String> cmbReportSection;
    private javax.swing.JComboBox<String> cmbReportType;
    private javax.swing.JComboBox<String> cmbSectionAttendance;
    private javax.swing.JComboBox<String> cmbSectionGrades;
    private javax.swing.JComboBox<String> cmbSubject8;
    private javax.swing.JComboBox<String> cmbSubjectAttendance;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAbsent;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblLate;
    private javax.swing.JLabel lblPresent;
    private javax.swing.JLabel lblReportType;
    private javax.swing.JLabel lblReportType1;
    private javax.swing.JLabel lblReportType2;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lblTitle11;
    private javax.swing.JLabel lblTitle14;
    private javax.swing.JLabel lblTitle15;
    private javax.swing.JLabel lblTitle16;
    private javax.swing.JLabel lblTitle25;
    private javax.swing.JLabel lblTitle26;
    private javax.swing.JLabel lblTitle27;
    private javax.swing.JLabel lblTitle28;
    private javax.swing.JLabel lblTitle29;
    private javax.swing.JLabel lblTitle4;
    private javax.swing.JLabel lblTitle5;
    private javax.swing.JLabel lblTitle6;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalstudents1;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel tabManagegrades;
    private javax.swing.JPanel tabMystudent;
    private javax.swing.JPanel tabRecordAttendance;
    private javax.swing.JPanel tabViewreport;
    private javax.swing.JTable tblGrades;
    private javax.swing.JTable tblMystudent;
    private javax.swing.JTable tblMystudent3;
    private javax.swing.JTable tblRecord;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtMySearch;
    private javax.swing.JTextField txtSearch5;
    // End of variables declaration//GEN-END:variables
}
