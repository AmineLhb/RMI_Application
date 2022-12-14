package ChatRMI.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class UserDAO {
    public static int save(String id, String name, String password) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into users(id,name,password) values(?,?,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, password);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static String[] readAllNames() {
        int status = 0;
        String[] q = new String[0];
        try {
            Connection con = DB.getConnection();
            String query = "select name from users";
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                q = new String[0];
                while (rs.next()) {
                    String name = rs.getString("name");
                    q = AddToStringArray(q, name);
                }
                //setClientPanel(q);
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }


        } catch (Exception e) {
            System.out.println(e);
        }
        return q;
    }

    public static String[] AddToStringArray(String[] oldArray, String newString) {
        String[] newArray = Arrays.copyOf(oldArray, oldArray.length + 1);
        newArray[oldArray.length] = newString;
        return newArray;
    }

}
