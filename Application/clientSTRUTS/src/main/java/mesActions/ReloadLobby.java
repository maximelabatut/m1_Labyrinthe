package mesActions;

import modele.Partie;

import java.util.ArrayList;
import java.util.List;

public class ReloadLobby extends Environnement{
    private List<String> lesUtilisateursInvites;
    private List<String> lesJoueurs;
    private boolean prive;
    private Integer nbJoueursMax;
    private String createur;
    private String pseudoHost;

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
        Partie laPartie = facadeRMI.getPartie(getPseudo());
        List<String> lesJoueurs = new ArrayList<String>();
        for(int i=0;i<laPartie.getMesJoueurs().length;i++){
            lesJoueurs.add(laPartie.getMesJoueurs()[i].getPseudo());
        }
        setLesUtilisateursInvites(new ArrayList<String>());
        setCreateur(laPartie.getJoueurHost().getPseudo());
        setLesJoueurs(lesJoueurs);
        setPrive(laPartie.isPrive());
        setNbJoueursMax(laPartie.getNbJoueursMax());

        return SUCCESS;
    }
}
