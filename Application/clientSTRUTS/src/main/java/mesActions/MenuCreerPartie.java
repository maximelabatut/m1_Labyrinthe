package mesActions;

import com.opensymphony.xwork2.ActionSupport;
import modele.Joueur;
import modele.JoueurInterface;
import org.apache.struts2.interceptor.ApplicationAware;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MenuCreerPartie extends Environnement {
    private List<Integer> lesNbJoueurs;
    private List<JoueurInterface> lesJoueursInvitables;

    public List<JoueurInterface> getLesJoueursInvitables() {
        return lesJoueursInvitables;
    }

    public void setLesJoueursInvitables(List<JoueurInterface> lesJoueursInvitables) {
        this.lesJoueursInvitables = lesJoueursInvitables;
    }

    public List<Integer> getLesNbJoueurs() {
        return lesNbJoueurs;
    }

    public void setLesNbJoueurs(List<Integer> lesNbJoueurs) {
        this.lesNbJoueurs = lesNbJoueurs;
    }

    @Override
    public String execute() throws Exception {
        setLesJoueursInvitables(new ArrayList<JoueurInterface>(facadeRMI.getPopulation()));
        lesNbJoueurs=new ArrayList<Integer>();
        lesNbJoueurs.add(2);
        lesNbJoueurs.add(3);
        lesNbJoueurs.add(4);
        List<JoueurInterface> laPopulation = new ArrayList<JoueurInterface>(facadeRMI.getPopulation());
        for(int i=0;i<laPopulation.size();i++){
            if(laPopulation.get(i).getPseudo().equals(getPseudo())){
                laPopulation.remove(laPopulation.get(i));
            }
        }
        setLesJoueursInvitables(laPopulation);
        return SUCCESS;
    }
}
