package mesActions;


import modele.JoueurInterface;
import modele.exceptions.ExceptionLoginDejaPris;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CreerJoueur extends Environnement {
    private String pseudo;
    private String password1;
    private String password2;

    @Override
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public void validate() {
        if(pseudo.length()<3 || pseudo==null){
            addFieldError("pseudo",getText("message.error.pseudo"));
        }
        if(password1.length()<3 || password1==null){
            addFieldError("password1",getText("message.error.password1"));
        }
        if(!password1.equals(password2)){
            addFieldError("password2",getText("message.error.password2"));
        }
    }

    @Override
    public String execute() throws Exception {
        List<JoueurInterface> laPopulation = new ArrayList<JoueurInterface>(facadeRMI.getPopulation());
        for(int i=0;i<laPopulation.size();i++){
            if(laPopulation.get(i).getPseudo().equals(pseudo)){
                addFieldError("pseudo",getText("message.error.unavailable.pseudo"));
                return INPUT;
            }
        }
        facadeRMI.ajouterPersonne(this.pseudo, this.password1);
        return SUCCESS;
    }
}
