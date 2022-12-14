package GestionUtilisateurs.exceptions;

public class SoldNoSuffisantException extends Exception {
    public SoldNoSuffisantException(){
        super("le sold n'est pas suffissant pour effeecuter l'operation");
    }
}
