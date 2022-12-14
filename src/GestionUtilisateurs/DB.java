package GestionUtilisateurs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection getConnection() {
        Connection con = null;
        java.sql.Statement s;
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC","root","");
            con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}