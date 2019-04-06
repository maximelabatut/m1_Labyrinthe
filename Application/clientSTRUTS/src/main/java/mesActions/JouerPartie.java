package mesActions;

import modele.*;

import java.util.Collection;
import java.util.List;

public class JouerPartie extends Environnement{

    private Case[][] monPlateau;
    private Joueur[] listeDesJoueurs;
    private Case caseLibre;
    private int action;
    private String pseudoHost;
    private Partie laPartie;
    private String leJoueurEnCours;
    private Carte[] mesCartes;
    private int x, y;
    private boolean auTourDe;
    private boolean placerUneCase;
    private boolean placerUneCaseObligatoire;
    private boolean deplacementImpossible;
    private boolean tresorTrouve;
    private Carte maCarte;
    private Case maCase;
    private int dejaPousserX;
    private int dejaPousserY;
    private String pseudoGagnant;
    private int id;
    private boolean isObservateur;
    private Integer population;
    private Collection<JoueurInterface> lesJoueursPopulation;

    public Collection<JoueurInterface> getLesJoueursPopulation() {
        return lesJoueursPopulation;
    }

    public void setLesJoueursPopulation(Collection<JoueurInterface> lesJoueursPopulation) {
        this.lesJoueursPopulation = lesJoueursPopulation;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public boolean isObservateur() {
        return isObservateur;
    }

    public void setObservateur(boolean observateur) {
        isObservateur = observateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudoGagnant() {
        return pseudoGagnant;
    }

    public void setPseudoGagnant(String pseudoGagnant) {
        this.pseudoGagnant = pseudoGagnant;
    }

    public boolean isTresorTrouve() {
        return tresorTrouve;
    }

    public void setTresorTrouve(boolean tresorTrouve) {
        this.tresorTrouve = tresorTrouve;
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

    public boolean isDeplacementImpossible() {
        return deplacementImpossible;
    }

    public void setDeplacementImpossible(boolean deplacementImpossible) {
        this.deplacementImpossible = deplacementImpossible;
    }

    public boolean isPlacerUneCaseObligatoire() {
        return placerUneCaseObligatoire;
    }

    public void setPlacerUneCaseObligatoire(boolean placerUneCaseObligatoire) {
        this.placerUneCaseObligatoire = placerUneCaseObligatoire;
    }

    public Case getMaCase() {
        return maCase;
    }

    public void setMaCase(Case maCase) {
        this.maCase = maCase;
    }

    public Carte getMaCarte() {
        return maCarte;
    }

    public void setMaCarte(Carte maCarte) {
        this.maCarte = maCarte;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
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

    public Partie getLaPartie() {
        return laPartie;
    }

    public void setLaPartie(Partie laPartie) {
        this.laPartie = laPartie;
    }

    @Override
    public String execute() throws Exception {
        setLaPartie(facadeRMI.getPartie(getPseudo()));

        if(laPartie == null){
            setPopulation(facadeRMI.getNbPopulation());
            setLesJoueursPopulation(facadeRMI.getPopulation());
            return "menuPrincipal";
        }

        setDejaPousserX(laPartie.getDejaPousserX());
        setDejaPousserY(laPartie.getDejaPousserY());

        setCaseLibre(laPartie.monPlateau.getCaseLibre());
        setMonPlateau(laPartie.monPlateau.getMonPlateau());
        setListeDesJoueurs(laPartie.getMesJoueurs());
        setLeJoueurEnCours(facadeRMI.getJoueur(laPartie.getJoueurHost().getPseudo()).getPseudo());
        setId(laPartie.getId());

        for(int i=0; i< listeDesJoueurs.length; i++){
            if(listeDesJoueurs[i].getPseudo().equals(getPseudo())){
                setMesCartes(listeDesJoueurs[i].getMesCartes());
            }
        }

        setMaCarte(mesCartes[mesCartes.length-1]);

        switch (action){
            case 1 :
                facadeRMI.placerCase(laPartie.getJoueurHost().getPseudo(),x,y);
                setPlacerUneCase(true);
                setPlacerUneCaseObligatoire(false);
                setDejaPousserX(x);
                setDejaPousserY(y);
                break;
            case 2 :
                facadeRMI.pivoterPlus90(laPartie.getJoueurHost().getPseudo());
                break;
            case 3 :
                facadeRMI.pivoterMinus90(laPartie.getJoueurHost().getPseudo());
                break;
            case 4 :
                if(placerUneCase){
                    facadeRMI.getPartie(getPseudo()).toursSuivant();
                    setPlacerUneCase(false);
                    setPlacerUneCaseObligatoire(false);
                }else{
                    setPlacerUneCaseObligatoire(true);
                }
                break;
            case 5 :
                if(placerUneCase){
                    if(facadeRMI.deplacerJoueur(laPartie.getJoueurHost().getPseudo(),x,y)){
                        setMaCase(facadeRMI.getPartie(getPseudo()).monPlateau.getCase(x,y));
                        if(facadeRMI.trouverTresor(laPartie.getJoueurHost().getPseudo(), maCase.getTresor().getNom())){
                            setTresorTrouve(true);
                        }
                        facadeRMI.getPartie(getPseudo()).toursSuivant();
                        setPlacerUneCaseObligatoire(false);
                        setPlacerUneCase(false);
                    }else{
                        setDeplacementImpossible(true);
                    }
                }else{
                    setPlacerUneCaseObligatoire(true);
                }
                break;
            case 6 :
                facadeRMI.quitterPartie(laPartie.getJoueurHost().getPseudo());
                setPopulation(facadeRMI.getNbPopulation());
                setLesJoueursPopulation(facadeRMI.getPopulation());
                return "menuPrincipal";
        }

        setLaPartie(facadeRMI.getPartie(getPseudo()));
        setCaseLibre(laPartie.monPlateau.getCaseLibre());
        setMonPlateau(laPartie.monPlateau.getMonPlateau());
        setListeDesJoueurs(laPartie.getMesJoueurs());
        setLeJoueurEnCours(facadeRMI.getJoueur(laPartie.getJoueurHost().getPseudo()).getPseudo());

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

        if(mesCartes.length-1 >= 0) {
            setMaCarte(mesCartes[mesCartes.length - 1]);
        }

        //facadeRMI.getJoueur(pseudoHost).getCouleur().equals(facadeRMI.getPartie(pseudoHost).monPlateau.getCase(x,y))
        if(facadeRMI.getPartie(getPseudo()).partieTerminee()){
            for(int i=0; i<listeDesJoueurs.length; i++){
                if(listeDesJoueurs[i].getMesCartes().length==0){
                    setPseudoGagnant(listeDesJoueurs[i].getPseudo());
                    return "gagner";
                }
            }
        }

        int cpt=0;
        for(int i=0; i< listeDesJoueurs.length; i++) {
            if(listeDesJoueurs[i].getPseudo().equals(getPseudo())){
                cpt++;
            }
        }

        if(cpt>0){
            setObservateur(false);
        }else{
            setObservateur(true);
        }
        return SUCCESS;
    }
}
