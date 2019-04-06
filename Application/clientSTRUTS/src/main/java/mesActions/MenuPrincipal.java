package mesActions;

import modele.JoueurInterface;

import java.util.ArrayList;
import java.util.List;

public class MenuPrincipal extends Environnement{
    private Integer population;
    private List<JoueurInterface> lesJoueursPopulation;

    public List<JoueurInterface> getLesJoueursPopulation() {
        return lesJoueursPopulation;
    }

    public void setLesJoueursPopulation(List<JoueurInterface> lesJoueursPopulation) {
        this.lesJoueursPopulation = lesJoueursPopulation;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String execute() throws Exception {
        setPopulation(facadeRMI.getPopulation().size());
        setLesJoueursPopulation(new ArrayList<JoueurInterface>(facadeRMI.getPopulation()));
        return SUCCESS;
    }
}
