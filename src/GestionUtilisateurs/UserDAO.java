package GestionUtilisateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {

    public static boolean CREATE_USER(String id, String name, String password) {
        boolean status = false;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into users(id,name,password) values(?,?,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, password);
            status = ps.execute();
            con.commit();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int UPDATE_USER(String id, String name, String password) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("update users set name = ?, password = ? where id = ?;");
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, id);
            status = ps.executeUpdate();
            con.commit();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static void REMOVE_USER(int id) {
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from users where id=?");
            ps.setString(1, String.valueOf(id));
            ps.executeUpdate();
            con.commit();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static List<String> readAllNames() {
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
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Arrays.asList(q);
    }

    public static Compte getComptebyID(int id) {
       Compte c = null;
        try {
            Connection con = DB.getConnection();
            String query = "select * from users where id=?";
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String idd = rs.getString("id");
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    c = new Compte(idd, name, password);
                }
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }

    public static String getCompteNamebyID(int id) {
        String name = null;
        try {
            Connection con = DB.getConnection();
            String query = "select name from users where id=" + id;
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    name = rs.getString("name");
                }
                con.close();
            } catch (Exception e) {
                System.out.println("f" + e);
            }
        } catch (Exception e) {
            System.out.println("g" + e);
        }
        return name;
    }

    public static List<Compte> readAllAccounts() {
        ArrayList<Compte> comptes = new ArrayList<>();
        try {
            Connection con = DB.getConnection();
            String query = "select * from users";
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    comptes.add(new Compte(id, name, password));
                }
                con.close();
            } catch (Exception e) {
                System.out.println("f" + e);
            }
        } catch (Exception e) {
            System.out.println("g" + e);
        }
        System.out.println(comptes);
        return comptes;
    }

    public static String[] AddToStringArray(String[] oldArray, String newString) {
        String[] newArray = Arrays.copyOf(oldArray, oldArray.length + 1);
        newArray[oldArray.length] = newString;
        return newArray;
    }

}
