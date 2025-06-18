/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihan.controller;
import latihan.model.Nasabah;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
public class NasabahController {
    Statement stat;
    ResultSet res;
    String sql;
    DefaultTableModel dtm = new DefaultTableModel();
    
    public NasabahController(){
        Koneksi db = new Koneksi();
        db.config();
        stat = db.stm;
    }
    
    public DefaultTableModel createTable(){
        //membuat tabel
        dtm.addColumn("ID Nasabah");
        dtm.addColumn("Nama Nasabah");
        dtm.addColumn("Nomor HP");
        dtm.addColumn("Jumlah Pinjam");
        
        return this.dtm;
    }
    
    public void TampilkanNasabah(){
        try {
            //clear dtm/tabel sementaranya
            dtm.getDataVector().removeAllElements();
            dtm.fireTableDataChanged();
            
            //sql query
            this.sql = "SELECT * FROM tbnasabah";
            
            //eksekusi
            res=stat.executeQuery(sql);
            
            //hasil query ditampilkan di dtm
            while(res.next()){
                Object[] obj = new Object[4];
                
                //get Stringnya harus sesuai dengan kolomm di db
                obj[0] = res.getString("id");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("hp");
                obj[3] = res.getString("jumlah");
                dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Query Gagal");
        }
    }
    
    //insert
    public boolean tambahNasabah(String a, String b, double c){
        try {
            Nasabah ns = new Nasabah();
            ns.setNama(a);
            ns.setHp(b);
            ns.setJumlah(c);
            //query
            this.sql = "INSERT INTO tbnasabah(nama, hp, jumlah) VALUES" + "('" + ns.getNama() + "','" + ns.getHp() + "',"+ns.getJumlah() + ")";

            //menjalankan query
            //untuk inser, update, delete, menggunakan eceute update
            this.stat.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    
    //update
    public boolean ubahNasabah(int a, String b, String c, double d){
        Nasabah ns = new Nasabah();
        ns.setId(a);
        ns.setNama(b);
        ns.setHp(c);
        ns.setJumlah(d);
        
        try {
            //query
            this.sql="UPDATE tbnasabah SET nama= '"+ns.getNama()+"', "
                    + "hp='"+ns.getHp()+"',"
                    + "jumlah="+ns.getJumlah()+""
                    + "WHERE id="+ns.getId()+"";
            this.stat.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    //detele
    public boolean hapusNasabah(int a){
        Nasabah ns = new Nasabah();
        ns.setId(a);
        
        try {
            this.sql="DELETE FROM tbnasabah WHERE id="+ns.getId()+"";
            this.stat.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
