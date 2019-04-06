package mesActions;

import modele.Joueur;
import modele.JoueurInterface;
import modele.Partie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnuleRejoindre extends Environnement {
    private Integer population;
    private Collection<JoueurInterface> lesJoueursPopulation;
    private Partie laPartie;

    public Partie getLaPartie() {
        return laPartie;
    }

    public void setLaPartie(Partie laPartie) {
        this.laPartie = laPartie;
    }

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

    @Override
    public String execute() throws Exception {
        setLaPartie(facadeRMI.getPartie(getPseudo()));

        Joueur[] nouvelleListeDesJoueursPartie = new Joueur[laPartie.getMesJoueurs().length-1];

        Joueur[] listeDesJoueursPartie = laPartie.getMesJoueurs();

        List<Joueur> pileDeJoueur = new ArrayList<Joueur>();

        for(int i=0; i<listeDesJoueursPartie.length; i++){
            if(!listeDesJoueursPartie[i].getPseudo().equals(getPseudo())){
                pileDeJoueur.add(listeDesJoueursPartie[i]);
            }
        }

        for(int i=0; i<pileDeJoueur.size(); i++){
            nouvelleListeDesJoueursPartie[i] = pileDeJoueur.get(i);
        }

        laPartie.setMesJoueurs(nouvelleListeDesJoueursPartie);

        setLesJoueursPopulation(facadeRMI.getPopulation());
        setPopulation(facadeRMI.getNbPopulation());

        return SUCCESS;
    }
}
