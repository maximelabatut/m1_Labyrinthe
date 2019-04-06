package modele;

import java.io.Serializable;

public class Invitation implements Serializable {
    private String joueurHost;
    private String joueurInvite;

    public Invitation(String joueurHost, String joueurInvite){
        this.joueurHost = joueurHost;
        this.joueurInvite = joueurInvite;
    }

    public String getJoueurHost() {
        return joueurHost;
    }

    public void setJoueurHost(String joueurHost) {
        this.joueurHost = joueurHost;
    }

    public String getJoueurInvite() {
        return joueurInvite;
    }

    public void setJoueurInvite(String joueurInvite) {
        this.joueurInvite = joueurInvite;
    }
}
