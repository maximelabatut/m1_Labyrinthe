package modele.exceptions;

public class ExceptionDeplacementImpossible extends Exception{
    public ExceptionDeplacementImpossible(){
        System.out.println("La case que vous souhaitez pousser ne fait pas partie des cases Amovibles de cette partie, merci de choisir une autre case.");
    }
}
