package ChatRMI.client;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection getConnection(){
        Connection con = null;
        java.sql.Statement s;
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC","root","");
            s = con.createStatement();
        }catch(Exception e){System.out.println(e);}
        return con;
    }

}