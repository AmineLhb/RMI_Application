package GestionUtilisateurs.exceptions;

public class CompteAlreadyExist extends Exception {
    public CompteAlreadyExist(){
        super("Ce compte deja exist !! penser d'effecuter des operations !");
    }
}
