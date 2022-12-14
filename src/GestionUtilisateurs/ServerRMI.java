package GestionUtilisateurs;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerRMI {
	
	final static int  PORT = 2244;
		final static String  HOST = "rmi://localhost:"+PORT+"/Parking";

	public static void main(String[] args) {
		
		try {
			System.out.println("[INFO...] Server : construct object");
			GestionUtilisateurImpl parkingImpl = new GestionUtilisateurImpl();
			
			//register objet on a PORT
			LocateRegistry.createRegistry(PORT);
			
			System.out.println("[INFO...] Server : publish on RMI");
			Naming.rebind(HOST, parkingImpl);
			System.out.println("[INFO...] Server : wait for clients invocations");

			JOptionPane.showMessageDialog(null,"Server Started...","Info!!",JOptionPane.INFORMATION_MESSAGE);
			
		} catch (RemoteException | MalformedURLException e) {
			System.out.println("[DEBUG...]Error on the Server....");
		}
	}

}
