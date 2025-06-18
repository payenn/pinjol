

package latihan.controller;
import java.sql.ResultSet;
import java.sql.Statement;
import latihan.model.Admin;



public class AdminController {
    Statement stm;
    ResultSet res;
    String sql;
    
    public AdminController(){
        Koneksi db = new Koneksi();
        db.config();
        stm = db.stm;
    }
    
    //QUERY SELECT
    public boolean cekLogin(String un, String pw){
        Admin adm = new Admin();
        adm.setNama(un);
        adm.setPassword(pw);
        boolean status = false;
        
        try {
            //menuliskan perintah SQL
            sql = "SELECT * FROM tbadmin WHERE nama='"+adm.getNama()+"' "
                    + "AND password='"+adm.getPassword()+"'";
            
            //mengeksekusi/menjalankan perintah SQL
            //executequery HANYA BERLAKU untuk perintah SELECT
            this.res = stm.executeQuery(sql);
            
            //jika res memperoleh hasil
            if(res.next()) status = true;
            else status = false;
            
        } catch (Exception e) {
            System.out.println("Query Gagal");
        }
        return status;
    }
}