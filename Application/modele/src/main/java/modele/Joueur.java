package modele;

import java.io.Serializable;

public class Joueur implements JoueurInterface,Serializable {
    // Variables
    private String pseudo, password, couleur;
    private Carte[] mesCartes;
    private Plateau monPlateau;
    private int x, y;
    private boolean auToursDe = false;
    private boolean possedeTresor;
    private int identifiant;
    private static int id = 0;


    //Constructeurs
    // Joueur sans partie
    public Joueur(String pseudo,String password){
        this.pseudo = pseudo;
        this.password = password;
        this.identifiant = id;
        this.id= id + 1;
    }

    //Joueur dans une partie
    public Joueur (String pseudo, int x, int y){
        this.pseudo = pseudo;
    }

    // Getters
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAuToursDe() {
        return auToursDe;
    }

    @Override
    public String getCouleur() {
        return couleur;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String getPseudo() {
        return pseudo;
    }

    @Override
    public Carte[] getMesCartes(){
        return mesCartes;
    }

    @Override
    public Case getCase() {
        return this.monPlateau.getCase(this.x,this.y);
    }

    // Setters

    @Override
    public void setAuToursDe(boolean auToursDe) {
        this.auToursDe = auToursDe;
    }

    @Override
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setMesCartes(Carte[] mesCartes){this.mesCartes=mesCartes;}

    @Override
    public int getIdentifiant() {
        return identifiant;
    }
}
