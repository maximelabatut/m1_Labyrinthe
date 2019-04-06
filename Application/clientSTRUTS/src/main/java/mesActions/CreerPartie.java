package mesActions;

import modele.Joueur;
import modele.JoueurInterface;
import modele.Partie;
import rmiRegister.SchedularMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class CreerPartie extends Environnement {
    private List<String> lesUtilisateursInvites;
    private Joueur[] lesJoueurs;
    private boolean prive;
    private Integer nbJoueursMax;
    private String createur;
    private List<Integer> lesNbJoueurs;
    private List<JoueurInterface> lesJoueursInvitables;

    public List<Integer> getLesNbJoueurs() {
        return lesNbJoueurs;
    }

    public void setLesNbJoueurs(List<Integer> lesNbJoueurs) {
        this.lesNbJoueurs = lesNbJoueurs;
    }

    public List<JoueurInterface> getLesJoueursInvitables() {
        return lesJoueursInvitables;
    }

    public void setLesJoueursInvitables(List<JoueurInterface> lesJoueursInvitables) {
        this.lesJoueursInvitables = lesJoueursInvitables;
    }

    public boolean isPrive() {
        return prive;
    }

    public void setPrive(boolean prive) {
        this.prive = prive;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public List<String> getLesUtilisateursInvites() {
        return lesUtilisateursInvites;
    }

    public void setLesUtilisateursInvites(List<String> lesUtilisateursInvites) {
        this.lesUtilisateursInvites = lesUtilisateursInvites;
    }

    public Integer getNbJoueursMax() {
        return nbJoueursMax;
    }

    public void setNbJoueursMax(Integer nbJoueursMax) {
        this.nbJoueursMax = nbJoueursMax;
    }

    public Joueur[] getLesJoueurs() {
        return lesJoueurs;
    }

    public void setLesJoueurs(Joueur[] lesJoueurs) {
        this.lesJoueurs = lesJoueurs;
    }

    @Override
    public String execute() throws Exception {
        Timer timer = new Timer();
        SchedularMain scMain = new SchedularMain();
        timer.scheduleAtFixedRate(scMain,0,1000);

        if(lesUtilisateursInvites.size() <= nbJoueursMax-1){
            facadeRMI.creerPartie(getPseudo(), nbJoueursMax, prive);
            for(int i=0;i<lesUtilisateursInvites.size();i++) {
                facadeRMI.inviterJoueur(getPseudo(),lesUtilisateursInvites.get(i));
            }
        }else{
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
            addFieldError("lesUtilisateursInvites", "Vous ne pouvez pas selectionner plus de joueurs qu'il ne peut y en avoir dans la partie.");
            return INPUT;
        }

        setCreateur(getPseudo());
        setLesJoueurs(facadeRMI.getPartie(getPseudo()).getMesJoueurs());
        return SUCCESS;
    }
}
