package modele;

import modele.exceptions.ExceptionDeplacementImpossible;
import modele.exceptions.ExceptionLoginDejaPris;
import modele.exceptions.ExceptionPartiePrivee;

import java.io.Serializable;
import java.util.List;

public interface GestionLabyrintheInterface extends Serializable {
    // Gestion partie
    void creerPartie(String pseudo, int nbJoueursMax, boolean prive);

    void rejoindrePartie(String pseudoHost, String pseudo) throws ExceptionPartiePrivee;

    boolean lancerPartie(String pseudoHost, String pseudo);

    void quitterPartie(String pseudoHost);

    Partie getPartie(String pseudo);

    void pivoterPlus90(String pseudoHost);

    void pivoterMinus90(String pseudoHost);

    List<JoueurInterface> getListeJoueursConnectes();

    boolean deplacerJoueur(String pseudoHost, int positionFinalX, int positionFinalY);

    void placerCase(String pseudoHost, int x, int y) throws ExceptionDeplacementImpossible;

    boolean trouverTresor(String pseudoHost, String tresor);

    Joueur getGagnant(String pseudoHost);

    //Gestion joueur
    boolean connexion(String pseudo, String password);

    void deconnexion(String pseudo);

    Joueur getJoueur(String pseudo);

    public JoueurInterface getJoueurByID(int id);

    // invitation
    void inviterJoueur(String pseudoInviteur, String pseudoAInvite);
    void ajouterPersonnePopulation (JoueurInterface j) throws ExceptionLoginDejaPris;
    void retirerPersonnePopulation (String pseudo);
    void setMaPopulation(List<JoueurInterface> population);
    List<String> getInvitationPartie(String pseudoHost);

    List<Partie> getLesPartiesRejoignables();
    List<Partie> getLesPartiesObservables();
    List<Invitation> getInvitationsByPseudo(String pseudo);

    void supprimerInvitation(String pseudoHost,String pseudo);

    void passerTourJoueur(String pseudo);
}
