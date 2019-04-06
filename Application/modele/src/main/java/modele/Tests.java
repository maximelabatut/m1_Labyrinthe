package modele;

import modele.exceptions.ExceptionDeplacementImpossible;
import modele.exceptions.ExceptionLoginDejaPris;
import modele.exceptions.ExceptionPartiePrivee;

public class Tests {
    public static void main(String[] args) throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
        GestionLabyrinthe facade = new GestionLabyrinthe();
        facade.connexion("Michel","");
        facade.connexion("Thierry","");

        facade.creerPartie("Michel",2, true);

        facade.rejoindrePartie("Michel","Thierry");

        for(int i=0; i<2 ;i++){
            Joueur monJoueur = facade.getPartie("Michel").getMesJoueurs()[i];
            facade.getPartie("Michel").getMesJoueurs()[i].setX(1);
            facade.getPartie("Michel").getMesJoueurs()[i].setY(1);
        }

        System.out.println(facade.getPartie("Michel").monPlateau);

        facade.deplacerJoueur("Michel",3,5);

        System.out.println(facade.getPartie("Michel").monPlateau);

    }
}