package mesActions;

public class Desinscription extends Environnement{
    @Override
    public String execute() throws Exception {
        facadeRMI.deconnexion(getPseudo());
        facadeRMI.supprimerPersonne(getPseudo());
        return SUCCESS;
    }
}
