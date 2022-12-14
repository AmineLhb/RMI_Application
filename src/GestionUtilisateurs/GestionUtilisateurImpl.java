package GestionUtilisateurs;


import GestionUtilisateurs.exceptions.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class GestionUtilisateurImpl extends UnicastRemoteObject implements IGestionUtilisateur {

    private static final long serialVersionUID = 2674880711467464646L;

    List<Compte> comptes = UserDAO.readAllAccounts();

    public GestionUtilisateurImpl() throws RemoteException {
        for(Compte c: comptes){
            System.out.println(c);
        }
    }

    @Override
    public void creerCompte(Compte c) throws RemoteException, CompteAlreadyExist {
        Compte compte = null;
        for (Compte comp : comptes) {
            if (comp.getId().equals(c.getId())) {
                compte = comp;
            }
        }
        if (compte == null) {
            comptes.add(c);
            UserDAO.CREATE_USER(c.getId(),c.getName(),c.getPassword());
        } else {
            throw new CompteAlreadyExist();
        }
    }

    @Override
    public void modifierCompte(int id, String name, String password) throws RemoteException, CompteAlreadyExist {
        UserDAO.UPDATE_USER(String.valueOf(id),name,password);
    }


    @Override
    public String consulter(String id) throws RemoteException, CompteNoExistException {
        String s = "";
        for (int i = 0; i < comptes.size(); i++) {
            if (comptes.get(i).getId().equals(id)) {
                Compte c = comptes.get(i);
                s = c.getName();
            }
        }
        if (s == "") {
            throw new CompteNoExistException();
        }
        return s;
    }


    @Override
    public void retirer(String id) throws RemoteException, CompteNoExistException {
        List<Compte> et = comptes.stream().filter(c -> c.getId().equals(id)).toList();
        if (comptes.size() > 0) {
            comptes.remove(et.get(0));
            UserDAO.REMOVE_USER(Integer.parseInt(et.get(0).getId()));
        } else {
            throw new CompteNoExistException();
        }
    }

    @Override
    public int count() throws RemoteException {
        return comptes.size();
    }
}
