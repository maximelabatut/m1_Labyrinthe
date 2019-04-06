package mesActions;

import modele.Joueur;
import modele.JoueurInterface;

import java.util.Collection;
import java.util.List;

public class PartieAnnule extends Environnement{
    private List<String> lesUtilisateursInvites;
    private String pseudoHost;
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

    public List<String> getLesUtilisateursInvites() {
        return lesUtilisateursInvites;
    }

    public void setLesUtilisateursInvites(List<String> lesUtilisateursInvites) {
        this.lesUtilisateursInvites = lesUtilisateursInvites;
    }

    public String getPseudoHost() {
        return pseudoHost;
    }

    public void setPseudoHost(String pseudoHost) {
        this.pseudoHost = pseudoHost;
    }

    @Override
    public String execute() throws Exception {
        setLesUtilisateursInvites(facadeRMI.getInvitationPartie(pseudoHost));

        for(int i=0; i<lesUtilisateursInvites.size(); i++){
            facadeRMI.supprimerInvitation(pseudoHost, lesUtilisateursInvites.get(i));
        }

        facadeRMI.quitterPartie(pseudoHost);

        setLesJoueursPopulation(facadeRMI.getPopulation());
        setPopulation(facadeRMI.getNbPopulation());

        return SUCCESS;
    }
}
