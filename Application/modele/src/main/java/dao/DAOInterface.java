package dao;

import modele.*;
import modele.exceptions.ExceptionDeplacementImpossible;
import modele.exceptions.ExceptionLoginDejaPris;
import modele.exceptions.ExceptionPartiePrivee;

import java.util.Collection;
import java.util.List;

public interface DAOInterface {

    public void ajouterPersonneDansLabyrinthe(String pseudo, String password);

    public void retirerPersonneDuLabyrinthe(String pseudo);

    public JoueurInterface getJoueurByID(int id);

    public Integer getNbPopulation();

    public Collection<JoueurInterface> getPopulation();

    public void inviterJoueur(String pseudoInviteur, String pseudoAInvite);

    public Collection<Partie> getLesPartiesRejoignables();

    public Collection<Partie> getLesPartiesObservables();

    public List<Invitation> getInvitationsByPseudo(String pseudo);

    public void creerPartie(String pseudo, int nbJoueursMax,boolean prive);

    public void rejoindrePartie(String pseudoHost, String pseudo) throws ExceptionPartiePrivee;

    public void quitterPartie(String pseudoHost);

    public Partie getPartie(String pseudo);

    public void pivoterPlus90(String pseudoHost);

    public void pivoterMinus90(String pseudoHost);

    public boolean deplacerJoueur(String pseudoHost, int positionFinalX, int positionFinalY);

    public void placerCase(String pseudoHost, int x, int y) throws ExceptionDeplacementImpossible;

    public boolean trouverTresor(String pseudoHost, String tresor);

    public Joueur getGagnant(String pseudoHost);

    //Gestion joueur
    public boolean connexion(String pseudo, String password) throws ExceptionLoginDejaPris;

    public void deconnexion(String pseudo);

    public Joueur getJoueur(String pseudo);

    public boolean lancerPartie(String pseudoHost, String pseudo);

    public void supprimerInvitation(String pseudoHost,String pseudo);

    public List<String> getInvitationPartie(String pseudoHost);

    public void passerTourJoueur(String pseudo);
}
