package mesActions;

import modele.Partie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MenuRejoindrePartie extends Environnement {
    private List<Partie> lesPartiesRejoignables;

    public List<Partie> getLesPartiesRejoignables() {
        return lesPartiesRejoignables;
    }

    public void setLesPartiesRejoignables(List<Partie> lesPartiesRejoignables) {
        this.lesPartiesRejoignables = lesPartiesRejoignables;
    }

    @Override
    public String execute() throws Exception {
        List <Partie> lesParties = new ArrayList<Partie>(facadeRMI.getLesPartiesRejoignables());
        lesParties.remove(facadeRMI.getPartie(getPseudo()));
        setLesPartiesRejoignables(lesParties);
        return SUCCESS;
    }
}