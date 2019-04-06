package mesActions;

public class DeconnecterJoueur extends Environnement{
    @Override
    public String execute() throws Exception {
        facadeRMI.deconnexion(getPseudo());
        return SUCCESS;
    }
}
