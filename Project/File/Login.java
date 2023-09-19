package Project.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Login extends JFrame {
    private String Name;
    private String Password;

    public Login() {// นี่คือ constructor ของคลาส Login ที่ใช้สำหรับการสร้างอ็อบเจ็กต์ของคลาส Login
        initComponents();// เรียก initComponents() เพื่อกำหนดค่าและสร้างองค์ประกอบของ GUI.
    }

    private void initComponents() {// สร้างและกำหนดค่าองค์ประกอบ GUI เช่น ปุ่ม, ป้ายชื่อ, ช่องข้อมูล 

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPasswordField2 = new javax.swing.JPasswordField();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("User Name:");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setText("Password: ");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPasswordField2.setText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jLabel1)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextField1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 154,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(73, 73, 73)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPasswordField2,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 153,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton1))))
                                .addContainerGap(63, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(78, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

    }// </editor-fold>

    public void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// ตรวจสอบชื่อผู้ใช้และดำเนินการตามเงื่อนไขหรือไม่ ถ้าข้อมูลไม่ตรงจะเด่งหน้าต่างว่าข้อมูลตรงไหนผิด
        Name = jTextField1.getText();
        if (Name == null || Name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "User Name is empty");
            return; // Return early to avoid further processing.
        }

        switch (Name) {
            case "Thitirat":
                char[] passwordChars = jPasswordField2.getPassword();
                Password = new String(passwordChars);

                if ("123456".equals(Password)) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new POSGUI(Name).setVisible(true);
                        }
                    });
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Password Wrong");
                    jPasswordField2.setText("");
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "User Name Wrong");
                jTextField1.setText("");
                break;
        }
    }
    

    private javax.swing.JButton jButton1;// ปุ่ม "Login" ใน GUI.
    private javax.swing.JLabel jLabel1;// ป้ายชื่อ "User Name:" ใน GUI.
    private javax.swing.JLabel jLabel2;// ป้ายชื่อ "Password:" ใน GUI.
    private javax.swing.JPanel jPanel1;// พาแนลที่ใช้ใน GUI สำหรับรวมองค์ประกอบอื่น ๆ
    private javax.swing.JPasswordField jPasswordField1;// ช่องรับข้อมูลรหัสผ่าน (ซึ่งไม่ได้ใช้ในโค้ด). 
    private javax.swing.JPasswordField jPasswordField2;// ช่องรับข้อมูลรหัสผ่านใน GUI.
    private javax.swing.JTextField jTextField1;// ช่องรับข้อมูลชื่อผู้ใช้ใน GUI.
}
