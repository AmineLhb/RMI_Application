package GestionUtilisateurs;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientRMI {

    final static int PORT = 2244;
    final static String HOST = "rmi://localhost:" + PORT + "/Parking";


    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
        IGestionUtilisateur b;

        {
            try {

                b = (IGestionUtilisateur) Naming.lookup(HOST);
                MyForm f = new MyForm(b);

            } catch (NotBoundException e) {
                System.out.println("1");
            } catch (MalformedURLException e) {
                System.out.println("2");
            } catch (RemoteException e) {
                System.out.println("3");
                JOptionPane.showMessageDialog(null,
                        "Le serveur que vous essayer de se connecter indisponible ou \n n'est pas encore demarre",
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }


    }


}
