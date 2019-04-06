package mesActions;

import modele.Invitation;
import modele.JoueurInterface;
import modele.Partie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuInvitations extends Environnement {
    private Map<Invitation,Partie> lesInvitations;

    public Map<Invitation, Partie> getLesInvitations() {
        return lesInvitations;
    }

    public void setLesInvitations(Map<Invitation, Partie> lesInvitations) {
        this.lesInvitations = lesInvitations;
    }

    @Override
    public String execute() throws Exception {
        List<Invitation> lesInvitationsSeules = facadeRMI.getInvitationsByPseudo(getPseudo());

        Map<Invitation,Partie> lesInvitations = new HashMap<Invitation, Partie>();

        for(int i=0;i<lesInvitationsSeules.size();i++){
            Invitation laInvitation = lesInvitationsSeules.get(i);
            Partie laPartie = facadeRMI.getPartie(lesInvitationsSeules.get(i).getJoueurHost());
            lesInvitations.put(laInvitation,laPartie);
        }

        setLesInvitations(lesInvitations);
        return SUCCESS;
    }
}
