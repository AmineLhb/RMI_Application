package GestionUtilisateurs;



import GestionUtilisateurs.exceptions.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGestionUtilisateur extends Remote {
	void creerCompte(Compte c) throws RemoteException, CompteAlreadyExist;
	void modifierCompte(int id, String name, String password) throws RemoteException, CompteAlreadyExist;

	String consulter(String id) throws RemoteException, CompteNoExistException;
	void retirer(String id) throws RemoteException, CompteNoExistException;
	int count() throws RemoteException;

}
