package modele;

import modele.exceptions.ExceptionDeplacementImpossible;
import modele.exceptions.ExceptionLoginDejaPris;
import modele.exceptions.ExceptionPartiePrivee;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.*;

public class GestionLabyrinthe implements GestionLabyrintheInterface, Serializable {

    // Variables
    private Map<String, Partie> logins;
    private Map<Invitation,Partie> invitations;

    private List<JoueurInterface> maPopulation;
    private List<JoueurInterface> populationConnecte;

    // blabla
    // Constructeur
    public GestionLabyrinthe() {
        this.logins = new HashMap<>();
        this.invitations = new HashMap<>();
        this.maPopulation = new ArrayList<JoueurInterface>();
        this.populationConnecte = new ArrayList<JoueurInterface>();
    }

    @Override
    public boolean deplacerJoueur(String pseudoHost, int positionFinalX, int positionFinalY){
        Partie laPartie = getPartie(pseudoHost);
        Joueur joueur = getJoueur(pseudoHost);

        System.out.println(joueur.getX()+" "+joueur.getY());

        Map<String, List<Case>> toutesLesCases = new HashMap<>();
        toutesLesCases.put(getJoueur(pseudoHost).getPseudo(), laPartie.monPlateau.getToutesLesCaseClean(joueur.getX(), joueur.getY()));

        List<Case> caseJoueur = toutesLesCases.get(getJoueur(pseudoHost).getPseudo());

        for(int i=0; i<caseJoueur.size(); i++){
            if(caseJoueur.get(i).getX() == positionFinalX && caseJoueur.get(i).getY() == positionFinalY){
                joueur.setX(positionFinalX);
                joueur.setY(positionFinalY);
                return true;
            }
        }

        return false;
    }

    //retourne la position du joueur
    public Case getCase(String pseudoHost, String pseudo){
        Partie laPartie = this.logins.get(pseudoHost);

        for(int i=0; i<laPartie.getMesJoueurs().length; i++){
            if(laPartie.getMesJoueurs()[i].getPseudo().equals(pseudo)){
                return laPartie.getMesJoueurs()[i].getCase();
            }
        }

        return null;
    }

    @Override
    public void placerCase(String pseudoHost, int x, int y) throws ExceptionDeplacementImpossible {
        int xOriginel=x;
        int yOriginel=y;

        getPartie(pseudoHost).setDejaPousserX(x);
        getPartie(pseudoHost).setDejaPousserY(y);

        Map<String, Boolean> dejaDeplacer = new HashMap<>();

        Case caseRemplacante;

        Case[][] monPlateau = getPartie(pseudoHost).monPlateau.getMonPlateau();

        Case caseLibre = getPartie(pseudoHost).monPlateau.getCaseLibre();

        Joueur[] mesJoueurs = getPartie(pseudoHost).getMesJoueurs();

        for(int i=0; i<mesJoueurs.length; i++){
            dejaDeplacer.put(mesJoueurs[i].getPseudo(), false);
        }

        if((x==1 && y%2==0 && y>0 && y<8) || (y==1 && x%2==0 && x>0 && x<8) || (x==7 && y%2==0 && y>0 && y<8) || (y==7 && x%2==0 && x>0 && x<8)) {
            //HAUT
            if (x == 1 && y % 2 == 0 && y > 0 && y < 8) {
                for (int i = 0; i < 7; i++) {
                    caseRemplacante = monPlateau[x][y];
                    caseLibre.setX(x);
                    caseLibre.setY(y);
                    monPlateau[x][y] = caseLibre;
                    caseLibre = caseRemplacante;
                    getPartie(pseudoHost).monPlateau.setCaseLibre(caseRemplacante);
                    x++;
                }

                x = xOriginel;

                for (int a = 0; a < 7; a++) {
                    for (int j = 0; j < mesJoueurs.length; j++) {
                        if (mesJoueurs[j].getX() == 7 && mesJoueurs[j].getX() == x && mesJoueurs[j].getY() == y && !dejaDeplacer.get(mesJoueurs[j].getPseudo()) ) {
                            mesJoueurs[j].setX(xOriginel);
                            dejaDeplacer.put(mesJoueurs[j].getPseudo(),true);
                        }else{
                            if (mesJoueurs[j].getX() == x && mesJoueurs[j].getY() == y && !dejaDeplacer.get(mesJoueurs[j].getPseudo())){
                                mesJoueurs[j].setX(mesJoueurs[j].getX() + 1);
                                dejaDeplacer.put(mesJoueurs[j].getPseudo(),true);
                            }
                        }
                    }
                    x++;
                }
            }

            //GAUCHE
            if (y == 1 && x % 2 == 0 && x > 0 && x < 8) {
                for (int i = 0; i < 7; i++) {
                    caseRemplacante = monPlateau[x][y];
                    caseLibre.setX(x);
                    caseLibre.setY(y);
                    monPlateau[x][y] = caseLibre;
                    caseLibre = caseRemplacante;
                    getPartie(pseudoHost).monPlateau.setCaseLibre(caseRemplacante);
                    y++;
                }
                y = yOriginel;

                for (int a = 0; a < 7; a++) {
                    for (int j = 0; j < mesJoueurs.length; j++) {
                        if (mesJoueurs[j].getY() == 7 && mesJoueurs[j].getX() == x && mesJoueurs[j].getY() == y && !dejaDeplacer.get(mesJoueurs[j].getPseudo()) ) {
                            mesJoueurs[j].setY(yOriginel);
                            dejaDeplacer.put(mesJoueurs[j].getPseudo(),true);
                        }else{
                            if (mesJoueurs[j].getX() == x && mesJoueurs[j].getY() == y && !dejaDeplacer.get(mesJoueurs[j].getPseudo())){
                                mesJoueurs[j].setY(mesJoueurs[j].getY() + 1);
                                dejaDeplacer.put(mesJoueurs[j].getPseudo(),true);
                            }
                        }
                    }
                    y++;
                }
            }

            //BAS
            if (x == 7 && y % 2 == 0 && y > 0 && y < 8) {
                for (int i = 0; i < 7; i++) {
                    caseRemplacante = monPlateau[x][y];
                    caseLibre.setX(x);
                    caseLibre.setY(y);
                    monPlateau[x][y] = caseLibre;
                    caseLibre = caseRemplacante;
                    getPartie(pseudoHost).monPlateau.setCaseLibre(caseRemplacante);
                    x--;
                }
                x = xOriginel;

                for (int a = 0; a < 7; a++) {
                    for (int j = 0; j < mesJoueurs.length; j++) {
                        if (mesJoueurs[j].getX() == 1 && mesJoueurs[j].getX() == x && mesJoueurs[j].getY() == y && !dejaDeplacer.get(mesJoueurs[j].getPseudo()) ) {
                            mesJoueurs[j].setX(xOriginel);
                            dejaDeplacer.put(mesJoueurs[j].getPseudo(),true);
                        }else{
                            if (mesJoueurs[j].getX() == x && mesJoueurs[j].getY() == y && !dejaDeplacer.get(mesJoueurs[j].getPseudo())){
                                mesJoueurs[j].setX(mesJoueurs[j].getX() - 1);
                                dejaDeplacer.put(mesJoueurs[j].getPseudo(),true);
                            }
                        }
                    }
                    x--;
                }
            }

            //DROITE
            if (y == 7 && x % 2 == 0 && x > 0 && x < 8) {
                for (int i = 0; i < 7; i++) {
                    caseRemplacante = monPlateau[x][y];
                    caseLibre.setX(x);
                    caseLibre.setY(y);
                    monPlateau[x][y] = caseLibre;
                    caseLibre = caseRemplacante;
                    getPartie(pseudoHost).monPlateau.setCaseLibre(caseRemplacante);
                    y--;
                }
                y = yOriginel;

                for (int a = 0; a < 7; a++) {
                    for (int j = 0; j < mesJoueurs.length; j++) {
                        if (mesJoueurs[j].getY() == 1 && mesJoueurs[j].getX() == x && mesJoueurs[j].getY() == y && !dejaDeplacer.get(mesJoueurs[j].getPseudo()) ) {
                            mesJoueurs[j].setY(yOriginel);
                            dejaDeplacer.put(mesJoueurs[j].getPseudo(),true);
                        }else{
                            if (mesJoueurs[j].getX() == x && mesJoueurs[j].getY() == y && !dejaDeplacer.get(mesJoueurs[j].getPseudo())){
                                mesJoueurs[j].setY(mesJoueurs[j].getY() - 1);
                                dejaDeplacer.put(mesJoueurs[j].getPseudo(),true);
                            }
                        }
                    }
                    y--;
                }
            }
        }else{
            throw new ExceptionDeplacementImpossible();
        }
    }

    @Override
    // Pivoter une case à 90 degrés dans le sens anti-horaire
    public void pivoterPlus90(String pseudoHost) {
        Case c = getPartie(pseudoHost).monPlateau.getCaseLibre();
        CaseForme formes[] = new CaseForme[10];
        formes[0] = new CaseForme(true, false, true, false); // ║
        formes[1] = new CaseForme(false, true, false, true); // ═

        formes[2] = new CaseForme(true, true, false, false); // ╚
        formes[3] = new CaseForme(false, true, true, false); // ╔
        formes[4] = new CaseForme(false, false, true, true); // ╗
        formes[5] = new CaseForme(true, false, false, true); // ╝

        formes[6] = new CaseForme(true, true, true, false); // ╠
        formes[7] = new CaseForme(false, true, true, true); // ╦
        formes[8] = new CaseForme(true, false, true, true); // ╣
        formes[9] = new CaseForme(true, true, false, true); // ╩

        if (c.getForme().equals(formes[0])) {
            c.setForme(formes[1]);
        } else {
            if (c.getForme().equals(formes[1])) {
                c.setForme(formes[0]);
            } else {
                if (c.getForme().equals(formes[2])) {
                    c.setForme(formes[3]);
                } else {
                    if (c.getForme().equals(formes[3])) {
                        c.setForme(formes[4]);
                    } else {
                        if (c.getForme().equals(formes[4])) {
                            c.setForme(formes[5]);
                        } else {
                            if (c.getForme().equals(formes[5])) {
                                c.setForme(formes[2]);
                            } else {
                                if (c.getForme().equals(formes[6])) {
                                    c.setForme(formes[7]);
                                } else {
                                    if (c.getForme().equals(formes[7])) {
                                        c.setForme(formes[8]);
                                    } else {
                                        if (c.getForme().equals(formes[8])) {
                                            c.setForme(formes[9]);
                                        } else {
                                            if (c.getForme().equals(formes[9])) {
                                                c.setForme(formes[6]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public JoueurInterface getJoueurByID(int id) {
        for(JoueurInterface p : maPopulation){
            System.out.println("Personne:" + p.getIdentifiant());
            if (p.getIdentifiant() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    // Pivoter une case à 90 degrés dans le sens horaire
    public void pivoterMinus90(String pseudoHost) {
        Case c = getPartie(pseudoHost).monPlateau.getCaseLibre();
        CaseForme formes[] = new CaseForme[10];
        formes[0] = new CaseForme(true, false, true, false); // ║
        formes[1] = new CaseForme(false, true, false, true); // ═

        formes[2] = new CaseForme(true, true, false, false); // ╚
        formes[3] = new CaseForme(false, true, true, false); // ╔
        formes[4] = new CaseForme(false, false, true, true); // ╗
        formes[5] = new CaseForme(true, false, false, true); // ╝

        formes[6] = new CaseForme(true, true, true, false); // ╠
        formes[7] = new CaseForme(false, true, true, true); // ╦
        formes[8] = new CaseForme(true, false, true, true); // ╣
        formes[9] = new CaseForme(true, true, false, true); // ╩

        if (c.getForme().equals(formes[0])) {
            c.setForme(formes[1]);
        } else {
            if (c.getForme().equals(formes[1])) {
                c.setForme(formes[0]);
            } else {
                if (c.getForme().equals(formes[2])) {
                    c.setForme(formes[5]);
                } else {
                    if (c.getForme().equals(formes[3])) {
                        c.setForme(formes[2]);
                    } else {
                        if (c.getForme().equals(formes[4])) {
                            c.setForme(formes[3]);
                        } else {
                            if (c.getForme().equals(formes[5])) {
                                c.setForme(formes[4]);
                            } else {
                                if (c.getForme().equals(formes[6])) {
                                    c.setForme(formes[9]);
                                } else {
                                    if (c.getForme().equals(formes[7])) {
                                        c.setForme(formes[6]);
                                    } else {
                                        if (c.getForme().equals(formes[8])) {
                                            c.setForme(formes[7]);
                                        } else {
                                            if (c.getForme().equals(formes[9])) {
                                                c.setForme(formes[8]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Partie getPartie(String pseudo) {
        return this.logins.get(pseudo);
    }

    @Override
    public boolean trouverTresor(String pseudoHost, String caseTresor) {
        Partie laPartie = getPartie(pseudoHost);
        Joueur joueur = getJoueur(laPartie.getJoueurHost().getPseudo());
        // On supprime la carte en haut de la pile du joueur
        if(caseTresor.equals(joueur.getMesCartes()[joueur.getMesCartes().length-1].getTresor().getNom())){
            joueur.setMesCartes(ArrayUtils.removeElement(joueur.getMesCartes(), joueur.getMesCartes()[joueur.getMesCartes().length-1]));
            System.out.println("Le trésors a été trouvé");
            return true;
        }
        return false;
    }

    @Override
    public Joueur getGagnant(String pseudoHost) {
        Partie laPartie = getPartie(pseudoHost);
        Joueur[] lesJoueurs = laPartie.getMesJoueurs();
        for(int i=0;i<lesJoueurs.length;i++){
            if(lesJoueurs[i].getMesCartes().length==0){
                return lesJoueurs[i];
            }
        }
        return null;
    }

    @Override
    public void creerPartie(String pseudo, int nbJoueursMax, boolean prive) {
        Joueur[] joueurs = new Joueur[1];
        Joueur joueurCreateur = new Joueur(pseudo, 0,0);
        joueurs[0] = joueurCreateur;
        Partie laPartie = new Partie(joueurs);
        laPartie.setJoueurHost(joueurs[0]);
        laPartie.setNbJoueursMax(nbJoueursMax);
        laPartie.setPrive(prive);
        this.logins.put(pseudo,laPartie);
    }

    @Override
    public void rejoindrePartie(String pseudoHost, String pseudo) throws ExceptionPartiePrivee {
        Joueur[] joueursDejaPresents = getPartie(pseudoHost).getMesJoueurs();
        Partie partie = getPartie(pseudoHost);
        Boolean estDansLaPartie = false;

        for(int i=0; i<partie.getMesJoueurs().length; i++){
            if(partie.getMesJoueurs()[i].getPseudo().equals(pseudo)){
                estDansLaPartie = true;
            }
        }

        if((partie.isPrive() && isJoueurInvitePartie(partie,pseudo)) || !partie.isPrive()) {
            if(!estDansLaPartie){
                int nbJoueurs = partie.getNbJoueurs() + 1;
                int nbJoueursMax = partie.getNbJoueursMax();
                if (nbJoueurs <= nbJoueursMax) {
                    if (joueursDejaPresents.length < getPartie(pseudoHost).getNbJoueursMax()) {
                        Joueur[] tableauNouveauxJoueurs = new Joueur[joueursDejaPresents.length + 1];
                        for (int i = 0; i < tableauNouveauxJoueurs.length; i++) {
                            if (i == tableauNouveauxJoueurs.length - 1) {
                                tableauNouveauxJoueurs[i] = new Joueur(pseudo, 0, 0);
                            } else {
                                tableauNouveauxJoueurs[i] = joueursDejaPresents[i];
                            }
                        }
                        getPartie(pseudoHost).setMesJoueurs(tableauNouveauxJoueurs);
                        this.logins.put(pseudo, getPartie(pseudoHost));
                    }
                } else {
                    int nbObservateurs = partie.getJoueursObservateurs().length;
                    Joueur[] nouveauxObservateurs = new Joueur[partie.getJoueursObservateurs().length + 1];
                    for (int i = 0; i < nouveauxObservateurs.length - 1; i++) {
                        nouveauxObservateurs[i] = partie.getJoueursObservateurs()[i];
                    }
                    for(int i=0;i<maPopulation.size();i++){
                        if(maPopulation.get(i).getPseudo().equals(pseudo)) {
                            nouveauxObservateurs[nouveauxObservateurs.length - 1]=(Joueur)maPopulation.get(i);
                        }
                    }
                    partie.setJoueursObservateurs(nouveauxObservateurs);
                }
            }
        }else{
            throw new ExceptionPartiePrivee();
        }
    }

    @Override
    public boolean lancerPartie(String pseudoHost, String pseudo){
        System.out.println("test");
        if(pseudoHost.equals(pseudo)){
            if(getPartie(pseudoHost).getMesJoueurs().length>1){
                getPartie(pseudoHost).setNbJoueursMax(getPartie(pseudoHost).getMesJoueurs().length);
                getPartie(pseudoHost).initialiserPartie();
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public void quitterPartie(String pseudoHost) {
        Partie laPartie = getPartie(pseudoHost);
        Joueur[] lesJoueursDeLaPartie = getPartie(pseudoHost).getMesJoueurs();
        for(int i=0;i<lesJoueursDeLaPartie.length;i++){
            this.logins.remove(lesJoueursDeLaPartie[i].getPseudo());
        }
    }

    @Override
    public Joueur getJoueur(String pseudoHost) {
        Partie laPartie = getPartie(pseudoHost);
        for (int j = 0; j < laPartie.getNbJoueurs(); j++) {
            if (laPartie.getMesJoueurs()[j].isAuToursDe()) {
                return laPartie.getMesJoueurs()[j];
            }
        }
        return null;
    }

    @Override
    public void inviterJoueur(String pseudoInviteur, String pseudoAInvite) {
        if(logins.containsKey(pseudoInviteur)) {
            Invitation invitation = new Invitation(pseudoInviteur,pseudoAInvite);
            invitations.put(invitation, getPartie(pseudoInviteur));
        }

    }

    private boolean isJoueurInvitePartie(Partie laPartie,String pseudo){
        return true;
    }

    @Override
    public boolean connexion(String pseudo, String password){
        for(int i=0;i<maPopulation.size();i++) {
            if (maPopulation.get(i).getPseudo().equals(pseudo) && maPopulation.get(i).getPassword().equals(password)) {
                if(populationConnecte.contains(maPopulation.get(i))){
                    return false;
                }else{
                    populationConnecte.add(maPopulation.get(i));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void deconnexion(String pseudo) {
        for(int i=0;i<populationConnecte.size();i++){
            if(populationConnecte.get(i).getPseudo().equals(pseudo)){
                JoueurInterface joueur = populationConnecte.get(i);
                //Partie laPartie = this.logins.get(pseudo);
                //quitterPartie(laPartie.getJoueurHost().getPseudo(), pseudo);
                populationConnecte.remove(joueur);
            }
        }
    }

    @Override
    public List<JoueurInterface> getListeJoueursConnectes() {
        return maPopulation;
    }

    @Override
    public void ajouterPersonnePopulation (JoueurInterface j) throws ExceptionLoginDejaPris{
        for(int i=0;i<maPopulation.size();i++) {
            if (maPopulation.get(i).getPseudo().equals(j.getPseudo())) {
                throw new ExceptionLoginDejaPris();
            }
        }
        maPopulation.add(j);
    }

    @Override
    public void retirerPersonnePopulation (String pseudo){
        for(int i=0;i<maPopulation.size();i++){
            if(maPopulation.get(i).getPseudo().equals(pseudo)){
                maPopulation.remove(maPopulation.get(i));
            }
        }
    }

    @Override
    public void setMaPopulation(List<JoueurInterface> population){
        this.maPopulation=population;
    }

    @Override
    public List<Partie> getLesPartiesRejoignables() {
        List<Partie> lesParties=new ArrayList<Partie>();
        Map<String, Partie> map = this.logins;
        for (Map.Entry<String, Partie> entry : map.entrySet())
        {
            if(!lesParties.contains(entry.getValue()) && !entry.getValue().isPrive() &&
                    entry.getValue().getNbJoueursMax() > entry.getValue().getMesJoueurs().length){
                lesParties.add(entry.getValue());
            }
        }
        return lesParties;
    }

    @Override
    public List<Partie> getLesPartiesObservables() {
        List<Partie> lesParties=new ArrayList<Partie>();
        Map<String, Partie> map = this.logins;
        for (Map.Entry<String, Partie> entry : map.entrySet())
        {
            if(!lesParties.contains(entry.getValue()) && entry.getValue().getMesJoueurs().length==entry.getValue().getNbJoueursMax()){
                if(entry.getValue().monPlateau != null){
                    lesParties.add(entry.getValue());
                }
            }
        }
        return lesParties;
    }

    @Override
    public List<Invitation> getInvitationsByPseudo(String pseudo) {
        List<Invitation> lesParties=new ArrayList<Invitation>();
        Map<Invitation, Partie> map = this.invitations;
        for (Map.Entry<Invitation, Partie> entry : map.entrySet())
        {
            if(entry.getKey().getJoueurInvite().equals(pseudo)){
                lesParties.add(entry.getKey());
            }
        }
        return lesParties;
    }

    @Override
    public List<String> getInvitationPartie(String pseudoHost){
        List<String> listeDesJoueursInvites = new ArrayList<String>();

        Partie laPartie = getPartie(pseudoHost);
        //listeDesJoueursInvites = laPartie.getMesJoueurs();

        Map<Invitation, Partie> map = this.invitations;
        for (Map.Entry<Invitation, Partie> entry : map.entrySet())
        {
            if(entry.getValue().equals(laPartie)){
                listeDesJoueursInvites.add(entry.getKey().getJoueurInvite());
            }
        }

        return listeDesJoueursInvites;
    }

    @Override
    public void supprimerInvitation(String pseudoHost,String pseudo){
        List<Invitation> lesParties=new ArrayList<Invitation>();
        Map<Invitation, Partie> map = this.invitations;
        for (Map.Entry<Invitation, Partie> entry : map.entrySet()) {
            if(entry.getKey().getJoueurHost().equals(pseudoHost) && entry.getKey().getJoueurInvite().equals(pseudo)){
                this.invitations.remove(entry.getKey());
            }
        }
    }

    @Override
    public void passerTourJoueur(String pseudo){
        getPartie(pseudo).toursSuivant();
    }
}
