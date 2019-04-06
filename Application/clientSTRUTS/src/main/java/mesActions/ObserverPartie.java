package mesActions;

import modele.*;

import java.util.*;

public class ObserverPartie extends Environnement {
    private String pseudoHost;
    private Partie laPartie;
    private boolean isObservateur;
    private List<Partie> lesPartiesObservables;
    private List<String> lesJoueurs;
    private boolean placerUneCase=false;
    private Joueur[] listeDesJoueurs;
    private String leJoueurEnCours;
    private Case[][] monPlateau;
    private Carte[] mesCartes;
    private Carte maCarte;
    private Map<String, Carte[]> lesCartes;
    private Integer population;
    private Collection<JoueurInterface> lesJoueursPopulation;

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Collection<JoueurInterface> getLesJoueursPopulation() {
        return lesJoueursPopulation;
    }

    public void setLesJoueursPopulation(Collection<JoueurInterface> lesJoueursPopulation) {
        this.lesJoueursPopulation = lesJoueursPopulation;
    }

    public Map<String, Carte[]> getLesCartes() {
        return lesCartes;
    }

    public void setLesCartes(Map<String, Carte[]> lesCartes) {
        this.lesCartes = lesCartes;
    }

    public Carte getMaCarte() {
        return maCarte;
    }

    public void setMaCarte(Carte maCarte) {
        this.maCarte = maCarte;
    }

    public Case[][] getMonPlateau() {
        return monPlateau;
    }

    public void setMonPlateau(Case[][] monPlateau) {
        this.monPlateau = monPlateau;
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

    public Joueur[] getListeDesJoueurs() {
        return listeDesJoueurs;
    }

    public void setListeDesJoueurs(Joueur[] listeDesJoueurs) {
        this.listeDesJoueurs = listeDesJoueurs;
    }

    public boolean isObservateur() {
        return isObservateur;
    }

    public void setObservateur(boolean observateur) {
        isObservateur = observateur;
    }

    public List<Partie> getLesPartiesObservables() {
        return lesPartiesObservables;
    }

    public void setLesPartiesObservables(List<Partie> lesPartiesObservables) {
        this.lesPartiesObservables = lesPartiesObservables;
    }

    public String getPseudoHost() {
        return pseudoHost;
    }

    public void setPseudoHost(String pseudoHost) {
        this.pseudoHost = pseudoHost;
    }

    public Partie getLaPartie() {
        return laPartie;
    }

    public void setLaPartie(Partie laPartie) {
        this.laPartie = laPartie;
    }

    public List<String> getLesJoueurs() {
        return lesJoueurs;
    }

    public void setLesJoueurs(List<String> lesJoueurs) {
        this.lesJoueurs = lesJoueurs;
    }

    public boolean isPlacerUneCase() {
        return placerUneCase;
    }

    public void setPlacerUneCase(boolean placerUneCase) {
        this.placerUneCase = placerUneCase;
    }

    @Override
    public String execute() throws Exception {

        //Récupération de la partie
        Partie laPartie = facadeRMI.getPartie(getPseudoHost());

        if(laPartie == null){
            setPopulation(facadeRMI.getNbPopulation());
            setLesJoueursPopulation(facadeRMI.getPopulation());
            return "menuPrincipal";
        }

        //Ajout des joueur dans la partie
        if(!laPartie.getJoueurHost().getPseudo().equals(getPseudo())) {
            facadeRMI.rejoindrePartie(pseudoHost, getPseudo());
        }

        setObservateur(true);
        setLaPartie(laPartie);
        setMonPlateau(laPartie.monPlateau.getMonPlateau());
        setLeJoueurEnCours(facadeRMI.getJoueur(laPartie.getJoueurHost().getPseudo()).getPseudo());
        setListeDesJoueurs(laPartie.getMesJoueurs());
        Map<String, Carte[]> lesCartes2 = new HashMap<String, Carte[]>();
        for(int i=0; i< listeDesJoueurs.length; i++){
            setMesCartes(listeDesJoueurs[i].getMesCartes());
            lesCartes2.put(listeDesJoueurs[i].getPseudo(),mesCartes);
            if(listeDesJoueurs[i].getMesCartes().length==0){
                return "finPartie";
            }
        }
        setLesCartes(lesCartes2);

        return SUCCESS;
    }
}
