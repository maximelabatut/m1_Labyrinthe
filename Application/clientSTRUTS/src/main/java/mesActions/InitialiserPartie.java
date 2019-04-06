package mesActions;

import modele.*;

import java.util.List;

public class InitialiserPartie extends Environnement {
    private Case[][] monPlateau;
    private Joueur[] listeDesJoueurs;
    private String leJoueurEnCours;
    private Case caseLibre;
    private String pseudoHost;
    private Carte[] mesCartes;
    private Carte maCarte;
    private boolean auTourDe;
    private boolean placerUneCase;
    private boolean deplacementImpossible;
    private boolean placerUneCaseObligatoire;
    private int dejaPousserX;
    private int dejaPousserY;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isPlacerUneCaseObligatoire() {
        return placerUneCaseObligatoire;
    }

    public void setPlacerUneCaseObligatoire(boolean placerUneCaseObligatoire) {
        this.placerUneCaseObligatoire = placerUneCaseObligatoire;
    }

    public Carte getMaCarte() {
        return maCarte;
    }

    public void setMaCarte(Carte maCarte) {
        this.maCarte = maCarte;
    }

    public boolean isDeplacementImpossible() {
        return deplacementImpossible;
    }

    public void setDeplacementImpossible(boolean deplacementImpossible) {
        this.deplacementImpossible = deplacementImpossible;
    }

    public boolean isPlacerUneCase() {
        return placerUneCase;
    }

    public void setPlacerUneCase(boolean placerUneCase) {
        this.placerUneCase = placerUneCase;
    }

    public boolean isAuTourDe() {
        return auTourDe;
    }

    public void setAuTourDe(boolean auTourDe) {
        this.auTourDe = auTourDe;
    }

    public Carte[] getMesCartes() {
        return mesCartes;
    }

    public void setMesCartes(Carte[] mesCartes) {
        this.mesCartes = mesCartes;
    }

    public String getLeJoueurEnCours() {
        return leJoueurEnCours;
    }

    public void setLeJoueurEnCours(String leJoueurEnCours) {
        this.leJoueurEnCours = leJoueurEnCours;
    }

    public String getPseudoHost() {
        return pseudoHost;
    }

    public void setPseudoHost(String pseudoHost) {
        this.pseudoHost = pseudoHost;
    }

    public Case getCaseLibre() {
        return caseLibre;
    }

    public void setCaseLibre(Case caseLibre) {
        this.caseLibre = caseLibre;
    }

    public Joueur[] getListeDesJoueurs() {
        return listeDesJoueurs;
    }

    public void setListeDesJoueurs(Joueur[] listeDesJoueurs) {
        this.listeDesJoueurs = listeDesJoueurs;
    }

    public Case[][] getMonPlateau() {
        return monPlateau;
    }

    public void setMonPlateau(Case[][] monPlateau) {
        this.monPlateau = monPlateau;
    }

    @Override
    public String execute() throws Exception {

        //Récupération de la partie
        Partie laPartie = facadeRMI.getPartie(getPseudo());

        //Ajout des joueur dans la partie
        //facadeRMI.rejoindrePartie(getPseudo(), "Arthur");
        //facadeRMI.rejoindrePartie(getPseudo(), "Maxime");
        //facadeRMI.rejoindrePartie(getPseudo(), "blabla");

        facadeRMI.lancerPartie(getPseudo(),getPseudo());

        //Suppression de toutes les invitations des joueurs
        List<String> lesUtilisateursInvites = facadeRMI.getInvitationPartie(pseudoHost);

        for(int i=0; i<lesUtilisateursInvites.size(); i++){
            facadeRMI.supprimerInvitation(pseudoHost, lesUtilisateursInvites.get(i));
        }

        //Récupération de tout les paramètres
        setListeDesJoueurs(laPartie.getMesJoueurs());
        setMonPlateau(laPartie.monPlateau.getMonPlateau());
        setCaseLibre(laPartie.monPlateau.getCaseLibre());
        setPseudoHost(laPartie.getJoueurHost().getPseudo());
        setLeJoueurEnCours(facadeRMI.getJoueur(pseudoHost).getPseudo());

        //Gère la case obligatoire à poser
        setPlacerUneCase(false);
        setPlacerUneCaseObligatoire(false);

        //Gère le déplacement possible d'une case
        setDeplacementImpossible(false);

        //Gère l'impossibilité de pousser 2 fois la même ligne ou colonne
        setDejaPousserX(-1);
        setDejaPousserY(-1);

        if(leJoueurEnCours.equals(getPseudo())){
            setAuTourDe(true);
        }else{
            setAuTourDe(false);
        }

        for(int i=0; i< listeDesJoueurs.length; i++){
            if(listeDesJoueurs[i].getPseudo().equals(getPseudo())){
                setMesCartes(listeDesJoueurs[i].getMesCartes());
            }
        }

        setId(laPartie.getId());
        setMaCarte(mesCartes[mesCartes.length-1]);

        return SUCCESS;
    }
}
