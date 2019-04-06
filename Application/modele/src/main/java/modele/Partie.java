package modele;

import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

public class Partie implements Serializable {
    private static int id;
    private int nbJoueursMax;
    private boolean prive;
    private Joueur[] mesJoueurs;
    public Plateau monPlateau;
    private Joueur joueurHost;
    private Joueur[] joueursObservateurs = new Joueur[0];

    private int dejaPousserX, dejaPousserY;

    public Partie (Joueur[] mesJoueurs){
        this.id = id++;
        this.mesJoueurs=mesJoueurs;
    }

    // Création de la partie

    public void initialiserPartie(){
        if(this.getNbJoueurs()==this.getNbJoueursMax()) {
            this.monPlateau = new Plateau();

            // Initialisation du plateau
            monPlateau.initialiserPlateau();

            // On enregistre le joueur qui "héberge" la partie
            //this.setJoueurHost(mesJoueurs[0]);

            // On récupère les joueurs connectés
            Carte[] toutesLesCartes = getToutesCartes();

            // Instanciation des cases départ
            Case[] casesDeparts = new Case[4];
            casesDeparts[0] = new Case(1, 1, new CaseForme(false, true, true, false), Tresor.tresor0);
            casesDeparts[1] = new Case(1, 7, new CaseForme(false, false, true, true), Tresor.tresor0);
            casesDeparts[2] = new Case(7, 1, new CaseForme(true, true, false, false), Tresor.tresor0);
            casesDeparts[3] = new Case(7, 7, new CaseForme(true, false, false, true), Tresor.tresor0);

            // On randomize les cases départ
            Collections.shuffle(Arrays.asList(casesDeparts));

            int nbCartesParJoueur = getToutesCartes().length / getNbJoueurs();
            int nbJoueurs = getNbJoueurs();
            for (int i = 0; i < getNbJoueurs(); i++) {
                // On répartie les cartes sur les différents joueurs
                Carte[] cartesDeCeJoueur = new Carte[nbCartesParJoueur];
                for (int j = 0; j < nbCartesParJoueur; j++) {
                    if (i == nbJoueurs - 1) {
                        cartesDeCeJoueur[j] = toutesLesCartes[j];
                        mesJoueurs[i].setMesCartes(cartesDeCeJoueur);
                    } else {
                        // On met la carte courante dans notre tableau temporaire
                        cartesDeCeJoueur[j] = toutesLesCartes[j];

                        // On supprime la carte de la pile complete
                        toutesLesCartes = ArrayUtils.removeElement(toutesLesCartes, toutesLesCartes[j]);

                        // Si le nombre de cartes attribué correspond au nombre de cartes à attribuer
                        if (j == nbCartesParJoueur - 1) {
                            // On attribue les cartes au joueur
                            mesJoueurs[i].setMesCartes(cartesDeCeJoueur);
                        }
                    }
                }

                // On associe chaque joueur à une couleur
                mesJoueurs[i].setX(casesDeparts[i].getX());
                mesJoueurs[i].setY(casesDeparts[i].getY());
                mesJoueurs[i].setCouleur(casesDeparts[i].isCouleur());
            }

            // On mélange les joueurs
            Collections.shuffle(Arrays.asList(mesJoueurs));

            // On initialise le premier tours
            mesJoueurs[0].setAuToursDe(true);
        }
    }


    public Joueur[] getMesJoueurs() {
        return mesJoueurs;
    }


    public Joueur[] getJoueursObservateurs() {
        return joueursObservateurs;
    }


    public void setJoueursObservateurs(Joueur[] joueursObservateurs) {
        this.joueursObservateurs = joueursObservateurs;
    }

    // récupération du joueur qui "héberge" la partie

    public void setJoueurHost(Joueur joueurHost) {
        this.joueurHost = joueurHost;
    }


    public Joueur getJoueurHost() {
        return joueurHost;
    }

    // Retourne le nombre de joueurs de la partie

    public int getNbJoueurs(){
        return mesJoueurs.length;
    }


    public void setNbJoueursMax(int nbJoueursMax) {
        this.nbJoueursMax = nbJoueursMax;
    }


    public int getId() {
        return id;
    }


    public int getNbJoueursMax() {
        return nbJoueursMax;
    }

    public int getDejaPousserX() {
        return dejaPousserX;
    }

    public void setDejaPousserX(int dejaPousserX) {
        this.dejaPousserX = dejaPousserX;
    }

    public int getDejaPousserY() {
        return dejaPousserY;
    }

    public void setDejaPousserY(int dejaPousserY) {
        this.dejaPousserY = dejaPousserY;
    }

    // Récupération de toutes les cartes possible à distribuer

    public Carte[] getToutesCartes() {
        Carte[] toutesCartes = new Carte[24];
        toutesCartes[0] = new Carte(Tresor.tresor1);
        toutesCartes[1] = new Carte(Tresor.tresor2);
        toutesCartes[2] = new Carte(Tresor.tresor3);
        toutesCartes[3] = new Carte(Tresor.tresor4);
        toutesCartes[4] = new Carte(Tresor.tresor5);
        toutesCartes[5] = new Carte(Tresor.tresor6);
        toutesCartes[6] = new Carte(Tresor.tresor7);
        toutesCartes[7] = new Carte(Tresor.tresor8);
        toutesCartes[8] = new Carte(Tresor.tresor9);
        toutesCartes[9] = new Carte(Tresor.tresor10);
        toutesCartes[10] = new Carte(Tresor.tresor11);
        toutesCartes[11] = new Carte(Tresor.tresor12);
        toutesCartes[12] = new Carte(Tresor.tresor13);
        toutesCartes[13] = new Carte(Tresor.tresor14);
        toutesCartes[14] = new Carte(Tresor.tresor15);
        toutesCartes[15] = new Carte(Tresor.tresor16);
        toutesCartes[16] = new Carte(Tresor.tresor17);
        toutesCartes[17] = new Carte(Tresor.tresor18);
        toutesCartes[18] = new Carte(Tresor.tresor19);
        toutesCartes[19] = new Carte(Tresor.tresor20);
        toutesCartes[20] = new Carte(Tresor.tresor21);
        toutesCartes[21] = new Carte(Tresor.tresor22);
        toutesCartes[22] = new Carte(Tresor.tresor23);
        toutesCartes[23] = new Carte(Tresor.tresor24);
        Collections.shuffle(Arrays.asList(toutesCartes));
        return toutesCartes;
    }

    // Test si la partie est terminee ou non

    public boolean partieTerminee() {
        for(int i=0;i<mesJoueurs.length;i++){
            if(mesJoueurs[i].getMesCartes().length==0) {
                return true;
            }
        }
        return false;
    }


    public void setMesJoueurs(Joueur[] mesJoueurs) {
        this.mesJoueurs = mesJoueurs;
    }

    // Passage au tours suivant

    public void toursSuivant(){
        int idJoueurTours = 0;
        for(int i=0; i<getMesJoueurs().length;i++){
            if(mesJoueurs[i].isAuToursDe()){
                idJoueurTours = i;
            }
        }
        mesJoueurs[idJoueurTours].setAuToursDe(false);
        int a = getMesJoueurs().length;
        if (idJoueurTours == getMesJoueurs().length-1) {
            mesJoueurs[0].setAuToursDe(true);
        }else{
            mesJoueurs[idJoueurTours+1].setAuToursDe(true);
        }
    }


    public boolean isPrive() {
        return prive;
    }


    public void setPrive(boolean prive) {
        this.prive = prive;
    }


    public String toString() {
        String result = "";
        result = result+"Liste des joueurs \n";
        for(int i=0;i<getNbJoueurs();i++){
            result = result + i+" ";
            if(mesJoueurs[i].equals(getJoueurHost())){
                result = result + "[HOST] ";
            }
            result = result + mesJoueurs[i].getPseudo() + " : " + mesJoueurs[i].getCouleur();
            if(mesJoueurs[i].isAuToursDe()){
                result = result + " - [A son tour de jouer]";
            }
            result = result + "\n";
        }
        result = result + "\n";
        result = result+"Liste des observateurs \n";
        for(int i=0;i<getJoueursObservateurs().length;i++){
            result = result + i+" ";
            result = result + "- "+getJoueursObservateurs()[i].getPseudo()+"\n";
        }
        result = result + "\n";
        result = result+"Cartes de chaque joueur :\n";
        for(int i=0;i<getNbJoueurs();i++){
            result = result + "Cartes de "+mesJoueurs[i].getPseudo()+"\n";
            for(int j=0; j<mesJoueurs[i].getMesCartes().length;j++){
                result = result + "- " + mesJoueurs[i].getMesCartes()[j]+"\n";
            }
        }

        for (int i = 1; i < this.monPlateau.getTaille()-1; i++) {
            result = result+"|";
            for (int j = 1; j <  this.monPlateau.getTaille()-1; j++) {
                result = result+this.monPlateau.getMonPlateau()[i][j].getX()+""+this.monPlateau.getMonPlateau()[i][j].getY();
                for(int a=0;a<this.getMesJoueurs().length;a++){
                    if(getMesJoueurs()[a].getX()==this.monPlateau.getMonPlateau()[i][j].getX() && getMesJoueurs()[a].getY()==this.monPlateau.getMonPlateau()[i][j].getY()){
                        result = result + getMesJoueurs()[a].getPseudo().substring(0,1);
                    }
                }
                result = result + "|";
            }
            result += "\n";
        }

        result = result + "----------------------\n";
        for (int i = 1; i < this.monPlateau.getTaille()-1; i++) {
            result = result+"|";
            for (int j = 1; j <  this.monPlateau.getTaille()-1; j++) {
                result = result+this.monPlateau.getMonPlateau()[i][j].getForme();
                for(int a=0;a<this.getMesJoueurs().length;a++){
                    if(getMesJoueurs()[a].getX()==this.monPlateau.getMonPlateau()[i][j].getX() && getMesJoueurs()[a].getY()==this.monPlateau.getMonPlateau()[i][j].getY()){
                        result = result + getMesJoueurs()[a].getPseudo().substring(0,1);
                    }
                }
                result = result + "|";
            }
            result += "\n";
        }

        result = result + "\nPlateau de jeu\n";
        result = result + monPlateau.toString() + "\n";

        return result;
    }
}

