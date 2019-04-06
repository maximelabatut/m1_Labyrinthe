package dao;

import modele.*;
import modele.exceptions.ExceptionDeplacementImpossible;
import modele.exceptions.ExceptionLoginDejaPris;
import modele.exceptions.ExceptionPartiePrivee;

import java.util.Collection;
import java.util.List;

public class MaDAO implements DAOInterface {

    private GestionLabyrintheInterface population;

    private static MaDAO monInstance;

    private MaDAO(){
        population = new GestionLabyrinthe();
    }

    public static MaDAO getInstance(){
        if (monInstance == null){
            monInstance = new MaDAO();
        }
        return monInstance;
    }

    public JoueurInterface getJoueurByID(int id){
        System.out.println("getPersonne DAO"+id);
        return this.population.getJoueurByID(id);
    }

    public void ajouterPersonneDansLabyrinthe(String pseudo, String password) {
        JoueurInterface j = new Joueur(pseudo, password);
        try {
            this.population.ajouterPersonnePopulation(j);
            System.out.println("Personne ajoutee DAO ("+pseudo+")");
        }catch (ExceptionLoginDejaPris e){
            System.out.println("Personne non-ajoutee DAO");
        }
    }

    public void retirerPersonneDuLabyrinthe(String pseudo) {
        this.population.retirerPersonnePopulation(pseudo);
        System.out.println("Personne supprimée DAO ("+pseudo+")");
    }

    public void creerPartie(String pseudo, int nbJoueursMax,boolean prive) {
        this.population.creerPartie(pseudo, nbJoueursMax, prive);
        System.out.println("Partie crée DAO ("+pseudo+" "+" "+ nbJoueursMax+" "+prive+")");
    }

    public Integer getNbPopulation() {
        return this.population.getListeJoueursConnectes().size();
    }

    public Collection<JoueurInterface> getPopulation() {
        return population.getListeJoueursConnectes();
    }

    public Collection<Partie> getLesPartiesRejoignables() {
        return population.getLesPartiesRejoignables();
    }

    public Collection<Partie> getLesPartiesObservables() {
        return population.getLesPartiesObservables();
    }

    public List<Invitation> getInvitationsByPseudo(String pseudo) {
        return population.getInvitationsByPseudo(pseudo);
    }

//    ----

    public void rejoindrePartie(String pseudoHost, String pseudo) throws ExceptionPartiePrivee{
        this.population.rejoindrePartie(pseudoHost,pseudo);
    }

    public void quitterPartie(String pseudoHost){
        this.population.quitterPartie(pseudoHost);
    }

    public Partie getPartie(String pseudo){
        return this.population.getPartie(pseudo);
    }

    public void pivoterPlus90(String pseudoHost){
        this.population.pivoterPlus90(pseudoHost);
    }

    public void pivoterMinus90(String pseudoHost){
        this.population.pivoterMinus90(pseudoHost);
    }

    public boolean deplacerJoueur(String pseudoHost, int x, int y){
        return this.population.deplacerJoueur(pseudoHost,x,y);
    }

    public void placerCase(String pseudoHost, int x, int y) throws ExceptionDeplacementImpossible{
        this.population.placerCase(pseudoHost,x,y);
    }

    public boolean trouverTresor(String pseudoHost, String tresor){
        return this.population.trouverTresor(pseudoHost,tresor);
    }

    public Joueur getGagnant(String pseudoHost){
        return this.population.getJoueur(pseudoHost);
    }

    //Gestion joueur
    public  boolean connexion(String pseudo, String password) throws ExceptionLoginDejaPris{
        return this.population.connexion(pseudo,password);
    }

    public void deconnexion(String pseudo){
        this.population.deconnexion(pseudo);
    }

    public Joueur getJoueur(String pseudo){
        return this.population.getJoueur(pseudo);
    }

    public void inviterJoueur(String pseudoInviteur, String pseudoAInvite){
        this.population.inviterJoueur(pseudoInviteur,pseudoAInvite);
        System.out.println("Invitation ajoutee DAO ("+pseudoInviteur+" -> "+pseudoAInvite+")");
    }

    public boolean lancerPartie(String pseudoHost, String pseudo){
        System.out.println("test dao");
        return this.population.lancerPartie(pseudoHost,pseudo);
    }

    public void supprimerInvitation(String pseudoHost,String pseudo){
        this.population.supprimerInvitation(pseudoHost, pseudo);
    }

    public List<String> getInvitationPartie(String pseudoHost){
        return this.population.getInvitationPartie(pseudoHost);
    }
    public void passerTourJoueur(String pseudo){
        this.population.passerTourJoueur(pseudo);
    }
}
