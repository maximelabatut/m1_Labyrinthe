package rmiService;

import dao.DAOInterface;
import dao.MaDAO;
import modele.Invitation;
import modele.Joueur;
import modele.JoueurInterface;
import modele.Partie;
import modele.exceptions.ExceptionDeplacementImpossible;
import modele.exceptions.ExceptionLoginDejaPris;
import modele.exceptions.ExceptionPartiePrivee;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;


public class MonServiceImpl implements MonService {

    DAOInterface maDAO = MaDAO.getInstance();

    @Override
    public void ajouterPersonne(String pseudo, String password) throws RemoteException {
        maDAO.ajouterPersonneDansLabyrinthe(pseudo,password);
    }

    @Override
    public void supprimerPersonne(String pseudo) throws RemoteException {
        maDAO.retirerPersonneDuLabyrinthe(pseudo);
    }

    @Override
    public void creerPartie(String pseudo, int nbJoueursMax,boolean prive) throws RemoteException {
        maDAO.creerPartie(pseudo, nbJoueursMax, prive);
    }

    @Override
    public JoueurInterface getPersonneById(Integer id) throws RemoteException {
        System.out.println("RMI:"+id);
        JoueurInterface p = maDAO.getJoueurByID(id);
        System.out.println(p);
        return p;
    }

    @Override
    public Integer getNbPopulation(){
        return this.maDAO.getNbPopulation();
    }

    public Collection<Partie> getLesPartiesRejoignables(){
        return this.maDAO.getLesPartiesRejoignables();
    }

    public Collection<Partie> getLesPartiesObservables(){
        return this.maDAO.getLesPartiesObservables();
    }

    public List<Invitation> getInvitationsByPseudo(String pseudo){
        return this.maDAO.getInvitationsByPseudo(pseudo);
    }

    @Override
    public Collection<JoueurInterface> getPopulation(){
        return this.maDAO.getPopulation();
    }

    @Override
    public void init() throws RemoteException {

    }

    public void inviterJoueur(String pseudoHost, String pseudo) throws ExceptionPartiePrivee {
        this.maDAO.inviterJoueur(pseudoHost,pseudo);
    }

    @Override
    public void rejoindrePartie(String pseudoHost, String pseudo) throws ExceptionPartiePrivee {
        this.maDAO.rejoindrePartie(pseudoHost,pseudo);
    }

    public void quitterPartie(String pseudoHost){
        this.maDAO.quitterPartie(pseudoHost);
    }

    public Partie getPartie(String pseudo){
        return this.maDAO.getPartie(pseudo);
    }

    public void pivoterPlus90(String pseudoHost){
        this.maDAO.pivoterPlus90(pseudoHost);
    }

    public void pivoterMinus90(String pseudoHost){
        this.maDAO.pivoterMinus90(pseudoHost);
    }

    public boolean deplacerJoueur(String pseudoHost, int x, int y){
        return this.maDAO.deplacerJoueur(pseudoHost,x,y);
    }

    public void placerCase(String pseudoHost, int x, int y) throws ExceptionDeplacementImpossible {
        this.maDAO.placerCase(pseudoHost,x,y);
    }

    public boolean trouverTresor(String pseudoHost, String tresor){
        return this.maDAO.trouverTresor(pseudoHost,tresor);
    }

    public Joueur getGagnant(String pseudoHost){
        return this.maDAO.getJoueur(pseudoHost);
    }

    //Gestion joueur
    public  boolean connexion(String pseudo, String password) throws ExceptionLoginDejaPris {
        return this.maDAO.connexion(pseudo, password);
    }

    public void deconnexion(String pseudo){
        this.maDAO.deconnexion(pseudo);
    }

    public Joueur getJoueur(String pseudo){
        return this.maDAO.getJoueur(pseudo);
    }

    @Override
    public boolean lancerPartie(String pseudoHost, String pseudo){
        System.out.println("test impl");
        return this.maDAO.lancerPartie(pseudoHost, pseudo);
    }

    public void supprimerInvitation(String pseudoHost,String pseudo){
        this.maDAO.supprimerInvitation(pseudoHost, pseudo);
    }

    public List<String> getInvitationPartie(String pseudoHost){
        return this.maDAO.getInvitationPartie(pseudoHost);
    }

    public void passerTourJoueur(String pseudo){
        this.maDAO.passerTourJoueur(pseudo);
    }
}
