

package latihan.controller;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;


public class Koneksi {

    public static Connection con;
    public static Statement stm;

    public void config() {
        try {
            //deklarasi string connection: url, user, pass database
            String url = "jdbc:mysql://localhost/dbpinjol";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //connection merangkai string connection
            con = DriverManager.getConnection(url,user,pass);
            
            //statement --> membuat SQL Statement
            stm = (Statement) con.createStatement();
            System.out.println("Koneksi Berhasil...");
        } catch (Exception e) {
            System.out.println("Koneksi Gagal...");
        }
    }
}