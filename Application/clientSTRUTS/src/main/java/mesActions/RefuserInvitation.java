package mesActions;

public class RefuserInvitation extends Environnement{
    private String pseudoHost;

    public String getPseudoHost() {
        return pseudoHost;
    }

    public void setPseudoHost(String pseudoHost) {
        this.pseudoHost = pseudoHost;
    }

    @Override
    public String execute() throws Exception {
        facadeRMI.supprimerInvitation(pseudoHost,getPseudo());
        return SUCCESS;
    }
}
