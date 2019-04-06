package modele.exceptions;

public class ExceptionLoginDejaPris extends Exception {
    public ExceptionLoginDejaPris(){
        System.out.println("Le pseudo a déjà été saisie par un autre joueur !");
    }
}
