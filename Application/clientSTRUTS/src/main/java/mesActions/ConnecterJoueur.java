package mesActions;


import modele.Joueur;
import modele.JoueurInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ConnecterJoueur extends Environnement {
    private String pseudo;
    private String password;
    private Integer population;
    private List<JoueurInterface> lesJoueursPopulation;

    public List<JoueurInterface> getLesJoueursPopulation() {
        return lesJoueursPopulation;
    }

    public void setLesJoueursPopulation(List<JoueurInterface> lesJoueursPopulation) {
        this.lesJoueursPopulation = lesJoueursPopulation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getPopulation() throws Exception {
        return facadeRMI.getNbPopulation();
    }

    @Override
    public void validate() {
        if(pseudo==null || pseudo.length()<=2){
            addFieldError("pseudo",getText("message.error.pseudo"));
        }
    }

    @Override
    public String execute() throws Exception {
        List<JoueurInterface> laPopulation = new ArrayList<JoueurInterface>(facadeRMI.getPopulation());
        if(facadeRMI.connexion(pseudo,password)) {
            setLogin(pseudo);

            setLesJoueursPopulation(new ArrayList<JoueurInterface>(facadeRMI.getPopulation()));

            return SUCCESS;
        }else{
            addFieldError("pseudo",getText("message.error.identifiants"));
            return INPUT;
        }
    }
}
