package modele.exceptions;

public class ExceptionPartiePrivee extends Exception {
    public void ExceptionPartiePrivee(){
        System.out.println("La partie choisi est privé, vous ne pouvez pas le rejoindre");
    }
}
