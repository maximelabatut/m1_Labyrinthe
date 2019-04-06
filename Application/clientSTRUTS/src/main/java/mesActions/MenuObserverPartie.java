package mesActions;

import com.opensymphony.xwork2.ActionSupport;
import modele.Partie;
import org.apache.struts2.interceptor.ApplicationAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuObserverPartie extends Environnement {
    private List<Partie> lesPartiesObservables;

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
        return SUCCESS;
    }
}
