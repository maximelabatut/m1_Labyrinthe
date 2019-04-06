package mesActions;

import modele.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RejoindrePartie extends Environnement {
    private List<String> lesUtilisateursInvites;
    private List<String> lesJoueurs;
    private boolean prive;
    private Integer nbJoueursMax;
    private String createur;
    private String pseudoHost;
    private Integer population;
    private Collection<JoueurInterface> lesJoueursPopulation;
    private Partie laPartie;
    private String lesPartiesRejoignables;

    public Partie getLaPartie() {
        return laPartie;
    }

    public void setLaPartie(Partie laPartie) {
        this.laPartie = laPartie;
    }

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

    public List<String> getLesUtilisateursInvites() {
        return lesUtilisateursInvites;
    }

    public void setLesUtilisateursInvites(List<String> lesUtilisateursInvites) {
        this.lesUtilisateursInvites = lesUtilisateursInvites;
    }

    public boolean isPrive() {
        return prive;
    }

    public void setPrive(boolean prive) {
        this.prive = prive;
    }

    public Integer getNbJoueursMax() {
        return nbJoueursMax;
    }

    public void setNbJoueursMax(Integer nbJoueursMax) {
        this.nbJoueursMax = nbJoueursMax;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public String getPseudoHost() {
        return pseudoHost;
    }

    public void setPseudoHost(String pseudoHost) {
        this.pseudoHost = pseudoHost;
    }

    public void setLesJoueurs(List<String> lesJoueurs) {
        this.lesJoueurs = lesJoueurs;
    }

    public List<String> getLesJoueurs() {
        return lesJoueurs;
    }

    @Override
    public String execute() throws Exception {

        //Récupération de la partie
        laPartie = facadeRMI.getPartie(getPseudoHost());

        if(laPartie == null){
            setPopulation(facadeRMI.getNbPopulation());
            setLesJoueursPopulation(facadeRMI.getPopulation());
            return "menuPrincipal";
        }

        //Ajout des joueur dans la partie
        if(!laPartie.getJoueurHost().getPseudo().equals(getPseudo())) {
            facadeRMI.rejoindrePartie(pseudoHost, getPseudo());
        }

        List<String> lesJoueurs = new ArrayList<String>();
        for(int i=0;i<laPartie.getMesJoueurs().length;i++){
            lesJoueurs.add(laPartie.getMesJoueurs()[i].getPseudo());
        }

        List<Invitation> lesJoueursInvites = facadeRMI.getInvitationsByPseudo(getPseudo());
        for(int i=0; i<lesJoueursInvites.size();i++){
            if(lesJoueursInvites.get(i).getJoueurHost().equals(getPseudoHost())){
                facadeRMI.supprimerInvitation(getPseudoHost(), getPseudo());
            }
        }

        setLesUtilisateursInvites(new ArrayList<String>());
        setCreateur(pseudoHost);
        setLesJoueurs(lesJoueurs);
        setPrive(laPartie.isPrive());
        setNbJoueursMax(laPartie.getNbJoueursMax());

        return SUCCESS;
    }

    public void setLesPartiesRejoignables(String lesPartiesRejoignables) {
        this.lesPartiesRejoignables = lesPartiesRejoignables;
    }

    public String getLesPartiesRejoignables() {
        return lesPartiesRejoignables;
    }
}
