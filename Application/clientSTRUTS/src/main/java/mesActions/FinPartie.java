package mesActions;

import modele.Carte;
import modele.Joueur;
import modele.Partie;

public class FinPartie extends Environnement {
    Partie laPartie;
    private String pseudoHost;
    private Joueur[] listeDesJoueurs;
    private int nbTresor;
    public String resultatJoueur;
    public String pseudoGagnant;

    public Partie getLaPartie() {
        return laPartie;
    }

    public void setLaPartie(Partie laPartie) {
        this.laPartie = laPartie;
    }

    public String getPseudoHost() {
        return pseudoHost;
    }

    public void setPseudoHost(String pseudoHost) {
        this.pseudoHost = pseudoHost;
    }

    public Joueur[] getListeDesJoueurs() {
        return listeDesJoueurs;
    }

    public void setListeDesJoueurs(Joueur[] listeDesJoueurs) {
        this.listeDesJoueurs = listeDesJoueurs;
    }

    public int getNbTresor() {
        return nbTresor;
    }

    public void setNbTresor(int nbTresor) {
        this.nbTresor = nbTresor;
    }

    public String getResultatJoueur() {
        return resultatJoueur;
    }

    public void setResultatJoueur(String resultatJoueur) {
        this.resultatJoueur = resultatJoueur;
    }

    public String getPseudoGagnant() {
        return pseudoGagnant;
    }

    public void setPseudoGagnant(String pseudoGagnant) {
        this.pseudoGagnant = pseudoGagnant;
    }

    @Override
    public String execute() throws Exception {

        //on récupère le pseudo de l'hôte
        pseudoHost=laPartie.getJoueurHost().getPseudo();

        //on met dans listeDesjoueurs la liste des joueurs de la partie
        listeDesJoueurs=laPartie.getMesJoueurs();

        pseudoGagnant=facadeRMI.getGagnant(pseudoHost).getPseudo();

        return SUCCESS;
    }

}

