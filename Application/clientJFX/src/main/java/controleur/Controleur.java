package controleur;

import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import modele.*;
import modele.exceptions.ExceptionDeplacementImpossible;
import modele.exceptions.ExceptionLoginDejaPris;
import modele.exceptions.ExceptionPartiePrivee;
import rmiService.MonService;
import views.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Controleur {
    public static final String ERROR_SERVER = "----------------------------------------\n"+"|  Erreur de liaison avec le serveur.  |"+"\n----------------------------------------";
    private String pseudoConnecte;
    private String pseudoHost;
    private Stage stage;

    private Index index;
    private Connexion connexion;
    private Inscription inscription;
    private MenuPrincipal menuPrincipal;
    private MenuCreation menuCreation;
    private MenuRejoindre menuRejoindre;
    private MenuObserver menuObserver;
    private MenuInvitation menuInvitation;
    private MenuPopulation menuPopulation;
    private FinPartieJoueur finPartieJoueur;
    private FinPartieObservateur finPartieObservateur;

    private Lobby lobby;

    private PartieJoueur partieJoueur;
    private PartieObservateur partieObservateur;

    private MonService monModele;
    public static final String HOST = "127.0.0.1";

    public Boolean placerLaCase = false;
    public Boolean trouverLeTresor = false;

    public Controleur(Stage primaryStage) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, 9345);
            this.monModele = (MonService) registry.lookup(MonService.serviceName);
            if (monModele.getNbPopulation()==0) {
                System.out.println("Population vide.");
            } else {
                List<JoueurInterface> liste = new ArrayList<JoueurInterface>(monModele.getPopulation());
                System.out.println("Liste des personnes inscrites : \n  [PSEUDO:MOTDEPASSE]");
                for( int i=0; i<liste.size(); i++) {
                    System.out.println("- ["+liste.get(i).getPseudo()+":"+liste.get(i).getPassword()+"]");
                }
                System.out.println();
            }
            this.index = Index.creerInstance(this, primaryStage);
            stage = primaryStage;
        } catch (Exception e) {
            System.err.println(ERROR_SERVER);
        }
    }

    public Boolean getTrouverLeTresor() {
        return trouverLeTresor;
    }

    public void setTrouverLeTresor(Boolean trouverLeTresor) {
        this.trouverLeTresor = trouverLeTresor;
    }

    public Boolean getPlacerLaCase() {
        return placerLaCase;
    }

    public void setPlacerLaCase(Boolean placerLaCase) {
        this.placerLaCase = placerLaCase;
    }

    public String getPseudoHost() {
        return pseudoHost;
    }

    public void setPseudoHost(String pseudoHost) {
        this.pseudoHost = pseudoHost;
    }

    public String getPseudoConnecte() {
        return pseudoConnecte;
    }

    public void setPseudoConnecte(String pseudoConnecte) {
        this.pseudoConnecte = pseudoConnecte;
    }

    public boolean connexion(String pseudo, String password){
        try{
            if(monModele.connexion(pseudo,password)){
                setPseudoConnecte(pseudo);
                System.out.println("\""+getPseudoConnecte()+"\" vient de se connecter.");
                return true ;
            }else{
                return false;
            }
        }catch(RemoteException e) {
            System.out.println(ERROR_SERVER);
            return false;
        }catch (ExceptionLoginDejaPris e){
            return false;
        }
    }

    public void deconnexion(){
        try{
            monModele.deconnexion(getPseudoConnecte());
            System.out.println("\""+getPseudoConnecte()+"\" vient de se déconnecter.");
            setPseudoConnecte(null);
            goToIndex();
        }catch(RemoteException e) {
            System.out.println(ERROR_SERVER);
        }
    }

    public boolean inscription(String pseudo, String password){
        try{
            List<JoueurInterface> laPopulation = new ArrayList<JoueurInterface>(monModele.getPopulation());
            for(int i=0;i<laPopulation.size();i++){
                if(laPopulation.get(i).getPseudo().equals(pseudo)){
                    return false;
                }
            }
            monModele.ajouterPersonne(pseudo, password);
            return true;
        }catch(RemoteException e) {
            System.out.println(ERROR_SERVER);
            return false;
        }
    }

    public Collection<JoueurInterface> getPopulation(){
        try {
            return monModele.getPopulation();
        }catch(RemoteException e) {
            System.out.println(ERROR_SERVER);
            return null;
        }
    }

    public void creerPartie(String pseudoConnecte, int nbJoueursMax, boolean prive) {
        try {
            monModele.creerPartie(pseudoConnecte, nbJoueursMax, prive);
            setPseudoHost(getPseudoConnecte());
            System.out.println("\""+monModele.getPartie(pseudoConnecte).getJoueurHost().getPseudo()+"\" vient de créer une partie.");
        }catch(RemoteException e) {
            System.out.println(ERROR_SERVER);
        }
    }

    public void inviterJoueurs(String pseudoAInviter) {
        try {
            monModele.inviterJoueur(getPseudoConnecte(), pseudoAInviter);
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
        }catch (ExceptionPartiePrivee e){
            System.out.println(ERROR_SERVER);
        }
    }

    public void accepterPartie(String pseudohost) {
        try{
            rejoindreJoueur(pseudohost);
            monModele.supprimerInvitation(pseudohost,getPseudoConnecte());
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
        }
    }

    public void refuserPartie(String pseudohost) {
        try{
            System.out.println(pseudohost+" "+getPseudoConnecte());
            monModele.supprimerInvitation(pseudohost,getPseudoConnecte());
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
        }
    }

    public void rejoindreJoueur(String pseudoHost){
        try{
            monModele.rejoindrePartie(pseudoHost, getPseudoConnecte());
            setPseudoHost(pseudoHost);
            goToLobby();
        }catch(ExceptionPartiePrivee e){
            goToMenuRejoindre();
        }catch(RemoteException e){
            System.out.println(ERROR_SERVER);
        }
    }

    public Partie getPartieByPseudo(String pseudo){
        try {
            return monModele.getPartie(pseudo);
        }catch(RemoteException e) {
            System.out.println(ERROR_SERVER);
            return null;
        }
    }

    public List<Partie> getPartiesRejoignables(){
        try{
            List<Partie> lesParties = new ArrayList<>(monModele.getLesPartiesRejoignables());
            lesParties.remove(monModele.getPartie(getPseudoConnecte()));
            return lesParties;
        }catch(RemoteException e) {
            System.out.println(ERROR_SERVER);
            return null;
        }
    }

    public List<Partie> getLesPartiesObservables(){
        try{
            List <Partie> lesParties = new ArrayList<Partie>(monModele.getLesPartiesObservables());
            lesParties.remove(monModele.getPartie(getPseudoConnecte()));
            return lesParties;
        }catch(RemoteException e) {
            System.out.println(ERROR_SERVER);
            return null;
        }
    }

    public void rejoindreObservateur(String pseudoHost){
        try{
            monModele.rejoindrePartie(pseudoHost, getPseudoConnecte());
            setPseudoHost(pseudoHost);
            goToPartieObservateur();
        }catch(ExceptionPartiePrivee e){
            goToMenuObserver();
        }catch(RemoteException e){
            System.out.println(ERROR_SERVER);
        }
    }

    public void lancerPartie(){
        try{
            if(monModele.lancerPartie(getPseudoConnecte(),getPseudoConnecte())) {
                goToPartieJoueur();
            }else{
                goToMenuPrincipal();
            }
        }catch(RemoteException e) {
            System.out.println(ERROR_SERVER);
        }
    }

    public Carte[] getCartesJoueur(String pseudo){
        try{
            Joueur[] listeDesJoueurs = monModele.getPartie(getPseudoConnecte()).getMesJoueurs();
            for(int i=0; i< listeDesJoueurs.length; i++){
                if(listeDesJoueurs[i].getPseudo().equals(getPseudoConnecte())){
                    return listeDesJoueurs[i].getMesCartes();
                }
            }
            return null;
        }catch(RemoteException e) {
            System.out.println(ERROR_SERVER);
            return null;
        }
    }

    public void annulePartie() {
        try{
            if(pseudoConnecte.equals(pseudoHost)){
                List<String> lesUtilisateursInvites = monModele.getInvitationPartie(pseudoHost);
                for(int i=0; i<lesUtilisateursInvites.size(); i++){
                    monModele.supprimerInvitation(pseudoHost, lesUtilisateursInvites.get(i));
                }
                monModele.quitterPartie(pseudoHost);
                System.out.println("\""+pseudoHost + "\" a détruit sa partie.");
            }else{
                monModele.quitterPartie(getPseudoConnecte());
                System.out.println("Vous avez quitté la partie");
            }
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
        }
    }

    public void pivoterMinus(){
        try {
            monModele.pivoterMinus90(getPartieByPseudo(getPseudoConnecte()).getJoueurHost().getPseudo());
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
        }
    }

    public void pivoterPlus() {
        try {
            monModele.pivoterPlus90(getPartieByPseudo(getPseudoConnecte()).getJoueurHost().getPseudo());
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
        }
    }

    public void quitterPartie() {
        try {
            monModele.quitterPartie(getPartieByPseudo(getPseudoHost()).getJoueurHost().getPseudo());
            setPlacerLaCase(false);
            System.out.println("La partie a été détruite");
            goToMenuPrincipal();
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
        }
    }

    public void passerTour() {
        try{
            monModele.passerTourJoueur(getPseudoHost());
            setPlacerLaCase(false);
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
        }
    }

    public String getJoueurEnCours(){
        try{
            return monModele.getJoueur(pseudoHost).getPseudo();
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
        }
        return "";
    }

    public void placerCase(int x, int y) {
        try {
            monModele.placerCase(monModele.getPartie(getPseudoConnecte()).getJoueurHost().getPseudo(), x, y);
            monModele.getPartie(getPseudoConnecte()).setDejaPousserX(x);
            monModele.getPartie(getPseudoConnecte()).setDejaPousserY(y);
            setPlacerLaCase(true);
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
        } catch (ExceptionDeplacementImpossible exceptionDeplacementImpossible) {
            exceptionDeplacementImpossible.printStackTrace();
        }
    }

    public int dejaPousserX(){
        try {
            return monModele.getPartie(getPseudoConnecte()).getDejaPousserX();
        }catch (RemoteException e) {
            System.out.println(ERROR_SERVER);
            return -1;
        }
    }

    public int dejaPousserY(){
        try {
            return monModele.getPartie(getPseudoConnecte()).getDejaPousserY();
        }catch (RemoteException e) {
            System.out.println(ERROR_SERVER);
            return -1;
        }
    }

    public boolean deplacerJoueur(int x, int y) {
        try {
            if (monModele.deplacerJoueur(getPseudoConnecte(), x, y)) {
                if(trouverTresor(x,y)){
                    setTrouverLeTresor(true);
                }
                monModele.passerTourJoueur(getPseudoHost());
                placerLaCase = false;
                return true;
            } else {
                return false;
            }
        } catch (RemoteException e) {
            System.out.println(ERROR_SERVER);
            return false;
        }
    }

    public boolean trouverTresor(int x, int y){
        try {
            return monModele.trouverTresor(getPseudoConnecte(), monModele.getPartie(pseudoHost).monPlateau.getCase(x,y).getTresor().getNom());
        }catch (RemoteException e) {
            System.out.println(ERROR_SERVER);
            return false;
        }
    }

    public boolean partieTermine(){
        return getPartieByPseudo(getPseudoConnecte()).partieTerminee();
    }

    public List<Invitation> getInvitationsByPseudo(){
        try{
            return monModele.getInvitationsByPseudo(getPseudoConnecte());
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
            return null;
        }
    }

    public Joueur getGagnant() {
        try{
            Partie laPartie = monModele.getPartie(getPseudoHost());
            for(int i=0; i<laPartie.getMesJoueurs().length; i++){
                if(laPartie.getMesJoueurs()[i].getMesCartes().length==0){
                    return laPartie.getMesJoueurs()[i];
                }
            }
            return null;
        }catch (RemoteException e){
            System.out.println(ERROR_SERVER);
            return null;
        }
    }

    public void goToIndex() {
        this.index = Index.creerInstance(this,stage);
    }

    public void goToConnexion() {
        this.connexion = Connexion.creerInstance(this,stage);
    }
    public void goToInscription() {
        this.inscription = Inscription.creerInstance(this,stage);
    }

    public void goToMenuPrincipal() {
        this.menuPrincipal = MenuPrincipal.creerInstance(this,stage);
    }
    public void goToMenuCreation() {
        this.menuCreation = MenuCreation.creerInstance(this,stage);
    }
    public void goToMenuRejoindre() {
        this.menuRejoindre = MenuRejoindre.creerInstance(this,stage);
    }
    public void goToMenuObserver() {
        this.menuObserver = MenuObserver.creerInstance(this,stage);
    }
    public void goToMenuInvitations() {
        this.menuInvitation = MenuInvitation.creerInstance(this,stage);
    }
    public void goToMenuPopulation() { this.menuPopulation = MenuPopulation.creerInstance(this,stage);  }

    public void goToLobby() { this.lobby = Lobby.creerInstance(this,stage);  }

    public void goToPartieJoueur() { this.partieJoueur = PartieJoueur.creerInstance(this,stage);  }
    public void goToPartieObservateur() { this.partieObservateur = PartieObservateur.creerInstance(this,stage);  }

    public void goToFinDePartie() { this.finPartieJoueur = FinPartieJoueur.creerInstance(this,stage); }
    public void goToFinDePartieObservateur() { this.finPartieObservateur = FinPartieObservateur.creerInstance(this,stage); }
}
