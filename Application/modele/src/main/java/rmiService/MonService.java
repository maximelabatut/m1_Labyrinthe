package rmiService;

import modele.Invitation;
import modele.Joueur;
import modele.JoueurInterface;
import modele.Partie;
import modele.exceptions.ExceptionDeplacementImpossible;
import modele.exceptions.ExceptionLoginDejaPris;
import modele.exceptions.ExceptionPartiePrivee;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface MonService extends Remote{


    public final String serviceName = "PopulationService";

    public abstract void ajouterPersonne(String pseudo, String password) throws RemoteException;

    public abstract void supprimerPersonne(String pseudo) throws RemoteException;

    public abstract JoueurInterface getPersonneById(Integer id) throws RemoteException;

    public abstract Integer getNbPopulation() throws  RemoteException;

    public abstract Collection<JoueurInterface> getPopulation() throws  RemoteException;

//    public abstract Collection<Partie> getPartie() throws  RemoteException;

    public abstract void creerPartie(String pseudo, int nbJoueursMax,boolean prive) throws RemoteException;

    public abstract Collection<Partie> getLesPartiesRejoignables() throws RemoteException;

    public abstract Collection<Partie> getLesPartiesObservables() throws RemoteException;

    public abstract List<Invitation> getInvitationsByPseudo(String pseudo) throws RemoteException;

    public abstract void init() throws RemoteException;

    public abstract void quitterPartie(String pseudoHost) throws RemoteException;

    public abstract Partie getPartie(String pseudo) throws RemoteException;

    public abstract void pivoterPlus90(String pseudoHost) throws RemoteException;

    public abstract void pivoterMinus90(String pseudoHost) throws RemoteException;

    public abstract boolean deplacerJoueur(String pseudoHost, int x, int y) throws RemoteException;

    public abstract void placerCase(String pseudoHost, int x, int y) throws ExceptionDeplacementImpossible,RemoteException;

    public abstract boolean trouverTresor(String pseudoHost, String tresor) throws RemoteException;

    public abstract Joueur getGagnant(String pseudoHost) throws RemoteException;

    public abstract void inviterJoueur(String pseudoHost, String pseudo) throws ExceptionPartiePrivee,RemoteException;
    //Gestion joueur
    public abstract boolean connexion(String pseudo, String password) throws ExceptionLoginDejaPris,RemoteException;

    public abstract void deconnexion(String pseudo) throws RemoteException;

    public abstract Joueur getJoueur(String pseudo) throws RemoteException;

    public abstract boolean lancerPartie(String pseudoHost, String pseudo) throws RemoteException;

    public abstract void supprimerInvitation(String pseudoHost,String pseudo) throws RemoteException;

    public abstract List<String> getInvitationPartie(String pseudoHost) throws RemoteException;

    public abstract void rejoindrePartie(String pseudoHost, String pseudo) throws ExceptionPartiePrivee,RemoteException;

    public abstract void passerTourJoueur(String pseudo) throws RemoteException;
}
