package mesActions;

import modele.Joueur;
import modele.JoueurInterface;
import modele.Partie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnuleObservateur extends Environnement {
    private List<Partie> lesPartiesObservables;
    private Partie laPartie;
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

    public Partie getLaPartie() {
        return laPartie;
    }

    public void setLaPartie(Partie laPartie) {
        this.laPartie = laPartie;
    }

    public List<Partie> getLesPartiesObservables() {
        return lesPartiesObservables;
    }

    public void setLesPartiesObservables(List<Partie> lesPartiesObservables) {
        this.lesPartiesObservables = lesPartiesObservables;
    }

    @Override
    public String execute() throws Exception {
        List <Partie> lesParties = new ArrayList<Partie>(facadeRMI.getLesPartiesObservables());
        lesParties.remove(facadeRMI.getPartie(getPseudo()));
        setLesPartiesObservables(lesParties);



        setLaPartie(facadeRMI.getPartie(getPseudo()));

        if(laPartie == null){
            setPopulation(facadeRMI.getNbPopulation());
            setLesJoueursPopulation(facadeRMI.getPopulation());
            return "menuPrincipal";
        }

        Joueur[] nouvelleListeDesJoueursPartie = new Joueur[laPartie.getJoueursObservateurs().length-1];

        Joueur[] listeDesJoueursPartie = laPartie.getJoueursObservateurs();

        List<Joueur> pileDeJoueur = new ArrayList<Joueur>();

        for(int i=0; i<listeDesJoueursPartie.length; i++){
            if(!listeDesJoueursPartie[i].getPseudo().equals(getPseudo())){
                pileDeJoueur.add(listeDesJoueursPartie[i]);
            }
        }

        for(int i=0; i<pileDeJoueur.size(); i++){
            nouvelleListeDesJoueursPartie[i] = pileDeJoueur.get(i);
        }

        laPartie.setJoueursObservateurs(nouvelleListeDesJoueursPartie);


        return SUCCESS;
    }
}
