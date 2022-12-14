package GestionUtilisateurs.exceptions;

public class CompteNoExistException extends Exception {
    public CompteNoExistException(){
        super("Compte n'exist pas");
    }
}
