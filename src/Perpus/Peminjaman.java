/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Perpus;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Acer
 */
public class Peminjaman extends javax.swing.JFrame {

    /**
     * Creates new form databaseperpustakaan
     */
    
    public void Waktu(){
        Date tgl=new Date();
        txtPinjam.setDate(tgl);
     }
    
    private void otomatis() {
        try {
            DateFormat vblnth = new SimpleDateFormat("ddMMYYYY");
            String blnth = vblnth.format(Calendar.getInstance().getTime());

            DateFormat hari = new SimpleDateFormat("dd-MM-YYYY");
            String a = hari.format(Calendar.getInstance().getTime());

            String sql = "SELECT MAX(right(Kode_Pinjam,6)) AS Kode_Pinjam  "
                    + "FROM peminjaman Where tanggal_pinjam like '" + a + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                if (res.first() == false) {
                    txtKP.setText("UPB/" + blnth + "/" + "000001");
                } else {
                    res.last();
                    int auto_id = res.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 6 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    txtKP.setText("UPB/" + blnth + "/" + no);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }
    }

    private void noterakhir() {
        try {

            String sql = "SELECT *from peminjaman order by Kode_Pinjam desc limit 1 ";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            DateFormat vblnth = new SimpleDateFormat("ddMMYYYY");

            String blnth = vblnth.format(Calendar.getInstance().getTime());

            if (!res.next()) {
                txtKP.setText("UPB/" + blnth + "/" + "000000");
            } else {
                String no = res.getString("Kode_Pinjam");
                String kosong = "";
                String b = no.substring(0, 10);
                String c = no.substring(11, 17);
                txtKP.setText(b + "/" + c);
            }
            otomatis();

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }
    }

    
    public String date;

    private void Kosongkan() {
        txtNIM.setText(null);
        txtNama.setText(null);
        txtNIM.setEditable(true);
        txtKode.setText(null);
        txtBuku.setText(null);
        txtPengarang.setText(null);
        txtPenerbit.setText(null);
        txtTerbit.setText(null);

    }

    private void Kosongkan_Form() {
        txtNIM.setEditable(true);
        txtKode.setText(null);
        txtBuku.setText(null);
        txtPengarang.setText(null);
        txtPenerbit.setText(null);
        txtTerbit.setText(null);

    }

    private void Tampilkan_Form() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Kode Buku");
        model.addColumn("Nama Buku");
        model.addColumn("Nama Pengarang");
        model.addColumn("Penerbit");
        model.addColumn("Tahun Terbit");

        try {

            String sql = "SELECT * FROM datamahasiswa ";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2), res.getString(3),
                    res.getString(4), res.getString(5)});
            }
            TabBuku.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }

    }

    private void Tampilkan_Form1() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("NIM");
        model.addColumn("Nama ");
        model.addColumn("Kode Buku");
        model.addColumn("Nama Buku");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Kode Pinjam");

        try {

            String sql = "SELECT * FROM peminjaman where nim like '%"
                    + txtCari.getText() + "%'"
                    + "or nama like '%" + txtCari.getText()
                    + "%'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2), res.getString(3),
                    res.getString(4), res.getString(5),
                    res.getString(6)});
            }
            TabPeminjam.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }

    }

    public Peminjaman() {
        initComponents();
        //Menampilkan icon gambar

        Image icon = Toolkit.getDefaultToolkit().getImage("src/gambar/sasa.png");
        setIconImage(icon);
        otomatis();
        noterakhir();
        Tampilkan_Form1();
        Tampilkan_Form();
        Kosongkan_Form();
        Waktu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabBuku = new javax.swing.JTable();
        btnExit = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabPeminjam = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtPrint = new javax.swing.JButton();
        Menu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtKP = new javax.swing.JTextField();
        txtNIM = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtBuku = new javax.swing.JTextField();
        txtKode = new javax.swing.JTextField();
        txtPinjam = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtPengarang = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPenerbit = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTerbit = new javax.swing.JTextField();
        txtNIM1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setBackground(new java.awt.Color(51, 51, 255));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("DATA PEMINJAMAN BUKU PERPUSTAKAAN UPB");

        jLabel17.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\koneksi\\src\\gambar\\books.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 1310, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("By Dua Masa Channel");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(983, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 1280, 40));

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\koneksi\\src\\gambar\\sv.jpg")); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 100, -1));

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\koneksi\\src\\gambar\\Cancel-2-16x16.png")); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 320, 100, 30));

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\koneksi\\src\\gambar\\dt.jpg")); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 320, 100, -1));

        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\koneksi\\src\\gambar\\ed.jpg")); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 320, 100, -1));

        TabBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabBukuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TabBukuMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(TabBuku);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 860, 150));

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\koneksi\\src\\gambar\\Tutup.png")); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 320, 100, 30));

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\koneksi\\src\\gambar\\cr.jpg")); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 590, -1, 30));

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });
        getContentPane().add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 590, 176, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tabel Data Buku");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 110, -1));

        TabPeminjam.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
        TabPeminjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        TabPeminjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPeminjamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabPeminjam);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, 850, 220));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tabel Data Peminjam");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, 140, -1));

        txtPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPrint.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\koneksi\\src\\gambar\\tm.jpg")); // NOI18N
        txtPrint.setText("Print");
        txtPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrintActionPerformed(evt);
            }
        });
        getContentPane().add(txtPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 640, 100, -1));

        Menu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Menu.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\koneksi\\src\\gambar\\Back.png")); // NOI18N
        Menu.setText("Back To Menu");
        Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuActionPerformed(evt);
            }
        });
        getContentPane().add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 660, 160, -1));

        jPanel3.setBackground(new java.awt.Color(0, 153, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Peminjam", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 255, 255)));

        txtKP.setEditable(false);
        txtKP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKPActionPerformed(evt);
            }
        });

        txtNIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNIMActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Kode Pinjam");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NIM");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNIM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKP))
                .addGap(36, 36, 36))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNIM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNama))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 360, 170));

        jPanel4.setBackground(new java.awt.Color(0, 153, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DataBuku", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kode Buku");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nama Buku");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tanggal Pinjam");

        txtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeActionPerformed(evt);
            }
        });

        txtPinjam.setMaxSelectableDate(new java.util.Date(253370743281000L));
        txtPinjam.setMinSelectableDate(new java.util.Date(-62135791119000L));
        txtPinjam.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtPinjamPropertyChange(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\koneksi\\src\\gambar\\+.png")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nama Pengarang");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nama Penerbit");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tahun Terbit");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPengarang))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                            .addGap(32, 32, 32)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(txtKode)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(txtPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPenerbit))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTerbit)))
                    .addComponent(jButton1))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 360, 370));
        getContentPane().add(txtNIM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 590, 160, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Masukkan NIM");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 590, 100, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Desktop\\pp.jpg")); // NOI18N
        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1280, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        int selectedOption = JOptionPane.showConfirmDialog(null, "Apakah Anda Ingin Menutup Program ?", "Tutup Aplikasi", JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:

            JOptionPane.showMessageDialog(null, "Proses Simpan Berhasil");
            Tampilkan_Form();
            Tampilkan_Form1();
            Kosongkan();
            Waktu();
            otomatis();
            noterakhir();
       
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "UPDATE peminjaman SET NIM ='" + txtNIM.getText()
                    + "',nama='" + txtNama.getText()
                    + "',Kode_Buku='" + txtKode.getText()
                    + "',Buku='" + txtBuku.getText()
                    + "',Tanggal_Pinjam='" + date
                    + "',Kode_Pinjam='" + txtKP.getText()
                    + "' WHERE NIM = '" + txtNIM.getText() + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "EDIT DATA BERHASIL...");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        Tampilkan_Form();
        Tampilkan_Form1();
        Kosongkan_Form();
        Waktu();
        otomatis();

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "DELETE FROM peminjaman WHERE Kode_Buku ='"
                    + txtKode.getText() + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "HAPUS DATA BERHASIL...");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        Tampilkan_Form1();
        Tampilkan_Form();
        Waktu();
        Kosongkan_Form();

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        Waktu();
        Kosongkan();
        otomatis();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:

        try {
            String sql = "SELECT * FROM peminjaman where nim like '%"
                    + txtCari.getText() + "%'"
                    + "or nama like '%" + txtCari.getText()
                    + "%'";

            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();

        } catch (HeadlessException | SQLException e) {

        }

        Tampilkan_Form1();
        Kosongkan_Form();


    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCariActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:


    }//GEN-LAST:event_txtCariKeyReleased

    private void txtNIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNIMActionPerformed
        // TODO add your handling code here:

        try {

            String sql = "SELECT * FROM mahasiswa where id_anggota='"
                    + txtNIM.getText() + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                txtNama.setText(res.getString("Nama"));

            }

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }
    }//GEN-LAST:event_txtNIMActionPerformed

    private void TabBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabBukuMouseClicked
        // TODO add your handling code here:
        int baris = TabBuku.rowAtPoint(evt.getPoint());
        String kode = TabBuku.getValueAt(baris, 0).toString();
        txtKode.setText(kode);

        String judul = TabBuku.getValueAt(baris, 1).toString();
        txtBuku.setText(judul);

        String jud = TabBuku.getValueAt(baris, 2).toString();
        txtPengarang.setText(jud);

        String judu = TabBuku.getValueAt(baris, 3).toString();
        txtPenerbit.setText(judu);

        String udul = TabBuku.getValueAt(baris, 4).toString();
        txtTerbit.setText(udul);


    }//GEN-LAST:event_TabBukuMouseClicked

    private void TabPeminjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPeminjamMouseClicked
        // TODO add your handling code here:
        int baris = TabPeminjam.rowAtPoint(evt.getPoint());
        String nim = TabPeminjam.getValueAt(baris, 0).toString();
        txtNIM.setText(nim);

        String nama = TabPeminjam.getValueAt(baris, 1).toString();
        txtNama.setText(nama);

        String kode_buku = TabPeminjam.getValueAt(baris, 2).toString();
        txtKode.setText(kode_buku);

        String buku = TabPeminjam.getValueAt(baris, 3).toString();
        txtBuku.setText(buku);

        String KP = TabPeminjam.getValueAt(baris, 5).toString();
        txtKP.setText(KP);
    }//GEN-LAST:event_TabPeminjamMouseClicked

    private void txtPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrintActionPerformed
        // TODO add your handling code here:
        Connection con = null;
        try {
            String jdbcDriver = "com.mysql.jdbc.Driver";
            Class.forName(jdbcDriver);

            String url = "jdbc:mysql://localhost:3306/latihannetbeans1";
            String user = "root";
            String pass = "";

            con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();

            try {

                String report = ("C:\\Users\\Acer\\Documents\\NetBeansProjects"
                        + "\\koneksi\\src\\koneksi\\DataSiswa.jrxml");
               
                //Mengambil parameter dari ireport
              
                JasperReport JRpt = JasperCompileManager.compileReport(report);
                File file = new File(report);
                JasperPrint JPrint = JasperFillManager.fillReport(JRpt, null, con);
                JasperViewer.viewReport(JPrint, false);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtPrintActionPerformed

    private void txtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeActionPerformed
        // TODO add your handling code here:
        try {

            String sql = "SELECT * FROM datamahasiswa where kode ='" + txtKode.getText() + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                txtBuku.setText(res.getString("Buku"));

            }

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }
    }//GEN-LAST:event_txtKodeActionPerformed

    private void TabBukuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabBukuMouseEntered

    }//GEN-LAST:event_TabBukuMouseEntered

    private void MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuActionPerformed
        // TODO add your handling code here:
        this.hide();
        MenuUtama frm = new MenuUtama();
        frm.setVisible(true);
    }//GEN-LAST:event_MenuActionPerformed

    private void txtKPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKPActionPerformed
        // TODO add your handling code here         
    }//GEN-LAST:event_txtKPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String sql = " INSERT INTO peminjaman VALUES "
                    + "('" + txtNIM.getText()
                    + "','" + txtNama.getText()
                    + "','" + txtKode.getText()
                    + "','" + txtBuku.getText()
                    + "','" + date
                    + "','" + txtKP.getText() + "')";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Proses Simpan Berhasil");
            Tampilkan_Form();
            Tampilkan_Form1();
            Waktu();
            Kosongkan_Form();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPinjamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtPinjamPropertyChange
        // TODO add your handling code here:
        try {
            if (txtPinjam.getDate() != null) {
                String pattern = "dd-MM-YYYY";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                date = String.valueOf(format.format(txtPinjam.getDate()));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtPinjamPropertyChange

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Peminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Menu;
    private javax.swing.JTable TabBuku;
    private javax.swing.JTable TabPeminjam;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBuku;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKP;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNIM;
    private javax.swing.JTextField txtNIM1;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private com.toedter.calendar.JDateChooser txtPinjam;
    private javax.swing.JButton txtPrint;
    private javax.swing.JTextField txtTerbit;
    // End of variables declaration//GEN-END:variables

    private void Koneksi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
