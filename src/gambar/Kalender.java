/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gambar;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class Kalender extends javax.swing.JFrame {

    /** Creates new form Kalender */


         private void noOtomatis(){

             
     
        

        }
    public Kalender() {
        initComponents();
    }
    
     private void otomatis()
    {
         try {

            String sql = "SELECT MAX(right(nofaktur,6)) AS nofaktur From peminjaman  " ;
            java.sql.Connection conn = (Connection) Perpus.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
             DateFormat vblnth = new SimpleDateFormat("ddMMyy");

                    String blnth = vblnth.format(Calendar.getInstance().getTime());
                      int mmyy = Integer.parseInt(blnth);
  while (res.next()) {
                if (res.first() == false) {
                    txtKP.setText("UPB/"+blnth+"/"+"000001");
                } else {
                    res.last();
                    int auto_id = res.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 6 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    txtKP.setText("UPB/"+blnth+ "/"+ no);
                }
            }
  
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        } 
     }
     
     private void noterakhir()
    {
         try {

            String sql = "SELECT *from peminjaman order by nofaktur desc limit 1 " ;
            java.sql.Connection conn = (Connection) Perpus.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
             DateFormat vblnth = new SimpleDateFormat("ddMMyy");

                    String blnth = vblnth.format(Calendar.getInstance().getTime());
                      
 
                if (!res.next()) {
                    txtKP.setText("UPB/"+blnth+ "/"+ "000000");
                } else {
                    String no = res.getString("nofaktur");
                    String kosong ="";
                    String b = no.substring(5,10);
                    String c = no.substring(12,17);
                    txtKP.setText("UPB/"+b+"/"+c);
                }
            
  otomatis();
  
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        } 
     }
 public void nomorotomatis(){
        try {
        Date now = new Date();
        SimpleDateFormat date,bulan;
        date= new SimpleDateFormat("yyyyMM");
        String tgl = date.format(now);
        bulan= new SimpleDateFormat("yyyy-MM");
        String bln =bulan.format(now);
        String c=bln+"-%";
   
        String sql = "SELECT MAX(right(nofaktur,6)) AS nofaktur  "
                +"FROM peminjaman Where tanggal_pinjam like '"+tgl+"'";
        java.sql.Connection conn = (Connection) Perpus.Koneksi.configDB();
        java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet res = stm.executeQuery(sql);
           
  while (res.next()) {
                if (res.first() == false) {
                    txtKP.setText("UPB/"+tgl+"/"+"000001");
                } else {
                    res.last();
                    int auto_id = res.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 6 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    txtKP.setText("UPB/"+tgl+ "/"+ no);
                }
            }
  
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        } 
     }
  private void notaerakhir() {
        try {

            String sql = "SELECT *from peminjaman order by nofaktur desc limit 1 ";
            java.sql.Connection conn = (Connection) Perpus.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            DateFormat vblnth = new SimpleDateFormat("ddMMyy");

            String blnth = vblnth.format(Calendar.getInstance().getTime());

            if (!res.next()) {
                txtKP.setText("UPB/" + blnth + "/" + "000000");
            } else {
                String no = res.getString("nofaktur");
                String kosong = "";
                String b = no.substring(5, 10);
                String c = no.substring(12, 17);
                txtKP.setText("UPB/" + b + "/" + c);
            }

           
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtKP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tanggal");

        jLabel2.setText("Kode Pinjam");

        tab.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tab);

        jLabel3.setText("Nama Buku");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tDate, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(145, 145, 145)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKP, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Kalender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kalender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kalender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kalender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kalender().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JDateChooser tDate;
    private javax.swing.JTable tab;
    private javax.swing.JTextField txtKP;
    // End of variables declaration//GEN-END:variables

}
