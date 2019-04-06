package modele;

import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.*;

import static java.util.Collections.shuffle;

public class Plateau implements PlateauInterface,Serializable {

    public static final int DEFAULT_SIZE = 9;
    // Variables
    private int taille;
    private Case[][] monPlateau;
    private Case caseLibre;
    private List<Case> toutesLesCasesAccessibles = new ArrayList<>();


    // Constructeur
    public Plateau(){
        this.taille = DEFAULT_SIZE;
        monPlateau = new Case[taille][taille];
    }

    @Override
    public Case getCaseLibre() {
        return caseLibre;
    }

    public void setCaseLibre(Case caseLibre) {
        this.caseLibre = caseLibre;
    }

    // Fonctions
    @Override
    public void initialiserPlateau() {

        //Récupération des cartes fixes et amovibles
        Case[] mesCasesFixes = getCasesFixes();
        Case[] mesCasesAmovibles = getCasesAmovibles();

        // On extrait la case libre
        caseLibre = mesCasesAmovibles[mesCasesAmovibles.length-1];
        mesCasesAmovibles = ArrayUtils.removeElement(mesCasesAmovibles, caseLibre);

        Case[] mesCases = new Case[mesCasesFixes.length + mesCasesAmovibles.length];
        int index = mesCasesFixes.length;

        for (int i = 0; i < mesCasesFixes.length; i++) {
            mesCases[i] = mesCasesFixes[i];
        }
        for (int i = 0; i < mesCasesAmovibles.length; i++) {
            mesCases[i + index] = mesCasesAmovibles[i];
        }

        // Initialisation cases fixes
        int indexCF = 0;
        int indexA = 0;
        for (int x = 1; x < monPlateau.length-1; x++) {
            for (int y = 1; y < monPlateau.length-1; y++) {
                if(x%2!=0 && y%2!=0) {
                    monPlateau[x][y] = mesCasesFixes[indexCF];
                    indexCF++;
                }else{
                    monPlateau[x][y] = mesCasesAmovibles[indexA];
                    indexA++;
                }
            }
        }
        for(int i=1; i<monPlateau.length-1; i++){
            for(int j=1; j<monPlateau.length-1; j++){
                monPlateau[i][j].setX(i);
                monPlateau[i][j].setY(j);
            }
        }
        for(int i=0;i<monPlateau.length;i++){
            for(int j=0;j<monPlateau.length;j++){
                if(i==0 || i==monPlateau.length-1 || j==0 || j==monPlateau.length-1){
                    monPlateau[i][j] = new Case(i,j,new CaseForme(false,false,false,false),null);
                }
            }
        }
    }

    @Override
    public Case[] getCasesFixes(){
        // Initialisation de la liste de cartes
        Case[] mesCasesFixes = new Case[16];

        // Cases départ fixes
        mesCasesFixes[0] = new Case(1, 1, new CaseForme(false, true, true, false), Tresor.tresor0);
        mesCasesFixes[3] = new Case(1, 7, new CaseForme(false, false, true, true), Tresor.tresor0);
        mesCasesFixes[12] = new Case(7, 1, new CaseForme(true, true, false, false), Tresor.tresor0);
        mesCasesFixes[15] = new Case(7, 7, new CaseForme(true, false, false, true), Tresor.tresor0);

        // Cases trésors fixes
        mesCasesFixes[1] = new Case(1, 3, new CaseForme(false, true, true, true), Tresor.tresor1);
        mesCasesFixes[2] = new Case(1, 5, new CaseForme(false, true, true, true), Tresor.tresor2);

        mesCasesFixes[4] = new Case(3, 1, new CaseForme(true, true, true, false), Tresor.tresor3);
        mesCasesFixes[5] = new Case(3, 3, new CaseForme(true, true, true, false), Tresor.tresor4);
        mesCasesFixes[6] = new Case(3, 5, new CaseForme(true, true, true, false), Tresor.tresor5);
        mesCasesFixes[7] = new Case(3, 7, new CaseForme(true, false, true, true), Tresor.tresor6);

        mesCasesFixes[8] = new Case(5, 1, new CaseForme(true, true, true, false), Tresor.tresor7);
        mesCasesFixes[9] = new Case(5, 3, new CaseForme(true, true, false, true), Tresor.tresor8);
        mesCasesFixes[10] = new Case(5, 5, new CaseForme(true, false, true, true), Tresor.tresor9);
        mesCasesFixes[11] = new Case(5, 7, new CaseForme(true, false, true, true), Tresor.tresor10);

        mesCasesFixes[13] = new Case(7, 3, new CaseForme(true, true, false, true), Tresor.tresor11);
        mesCasesFixes[14] = new Case(7, 5, new CaseForme(true, true, false, true), Tresor.tresor12);

        return mesCasesFixes;
    }

    @Override
    public Case[] getCasesAmovibles(){
        // Initialisation de la liste de cartes
        Case[] mesCasesAmovibles = new Case[34];

        // modele.Case vides I amovibles
        for(int i=0; i<12;i++){
            mesCasesAmovibles[i] = new Case(0, 0, new CaseForme(true, false, true, false), Tresor.tresor0);
        }

        // Cases vides V amovibles
        for(int i=12; i<22;i++){
            mesCasesAmovibles[i] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor0);
        }

        // 6 Cases trésor V amovibles
        mesCasesAmovibles[22] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor13);
        mesCasesAmovibles[23] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor14);
        mesCasesAmovibles[24] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor15);
        mesCasesAmovibles[25] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor16);
        mesCasesAmovibles[26] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor17);
        mesCasesAmovibles[27] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor18);

        // 6 Cases trésor T amovibles
        mesCasesAmovibles[28] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor19);
        mesCasesAmovibles[29] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor20);
        mesCasesAmovibles[30] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor21);
        mesCasesAmovibles[31] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor22);
        mesCasesAmovibles[32] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor23);
        mesCasesAmovibles[33] = new Case(0, 0, new CaseForme(true, true, false, false), Tresor.tresor24);

        int c = 0;
        for(int a = 1;a<this.taille-1;a++) {
            for (int b = 1; b<this.taille-1; b++) {
                // cases amovibles -> (pair,impair) + (impair,pair) + (pair,pair)
                if ((a % 2 != 0 && b % 2 == 0) || (a % 2 == 0 && b % 2 != 0) || (a % 2 == 0 && b % 2 == 0)) {
                    mesCasesAmovibles[c].setX(a);
                    mesCasesAmovibles[c].setY(b);
                    c++;
                }
            }
        }

        for(int i=0;i<mesCasesAmovibles.length-1;i++){
            mesCasesAmovibles[i].tournerAlea();
        }
        Collections.shuffle(Arrays.asList(mesCasesAmovibles));
        return mesCasesAmovibles;
    }

    @Override
    public Case getCase(int x, int y){
        for(int i=0;i<monPlateau.length;i++){
            for(int j=0;j<monPlateau.length;j++){
                if(monPlateau[i][j].getX()==x && monPlateau[i][j].getY()==y){
                    return monPlateau[i][j];
                }
            }
        }
        return null;
    }

    // Fonctions Interface
    @Override
    public String toString() {
        String result = "";
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

        for (int i = 1; i < this.getTaille()-1; i++) {
            result = result+"|";
            for (int j = 1; j <  this.getTaille()-1; j++) {
                result = result+this.getMonPlateau()[i][j].getForme();
                result = result + "|";
            }
            result += "\n";
        }
        result=result+"\n";
        result = result+"Case libre : "+caseLibre.getX()+""+caseLibre.getY()+" ("+caseLibre.getForme()+") "+caseLibre.getTresor()+"\n";
        result = result + "----------------------\n";
        return result;
    }

    public int getTaille() {
        return taille;
    }

    @Override
    public Case[][] getMonPlateau() {
        return monPlateau;
    }

    @Override
    public boolean estCompatible(int x1, int y1, int x2, int y2) {
        Case c = getCase(x1,y1);
        Case c2 = getCase(x2,y2);
        // c2 en haut de c
        if(x2==x1-1 && y2==y1){
            if(c.getForme().isHaut() && c2.getForme().isBas()){
                return true;
            }else{
                return false;
            }
        }
        // c2 en bas de c
        if(x2==x1+1 && y2==y1){
            if(c.getForme().isBas() && c2.getForme().isHaut()){
                return true;
            }else{
                return false;
            }
        }
        // c2 gauche de c
        if(x2==x1 && y2==y1-1){
            if(c.getForme().isGauche() && c2.getForme().isDroite()){
                return true;
            }else{
                return false;
            }
        }
        // c2 droite de c
        if(x2==x1 && y2==y1+1){
            if(c.getForme().isDroite() && c2.getForme().isGauche()){
                return true;
            }else{
                return false;
            }
        }

        return false;
    }

    @Override
    public List<Case> getToutesLesCaseClean(int x, int y){
        toutesLesCasesAccessibles.clear();
        return getCasesAdjacentes(x, y);
    }

    @Override
    public List<Case> getCasesAdjacentes(int positionJoueurX, int positionJoueurY) {

        if(!estDansLaListe(toutesLesCasesAccessibles, positionJoueurX, positionJoueurY)){
            toutesLesCasesAccessibles.add(getCase(positionJoueurX, positionJoueurY));
        }

        for(int i=0; i<4; i++){

            if(estCompatible(positionJoueurX, positionJoueurY, positionJoueurX-1, positionJoueurY)){
                if(!estDansLaListe(toutesLesCasesAccessibles,positionJoueurX-1, positionJoueurY)){
                    toutesLesCasesAccessibles.add(getCase(positionJoueurX-1, positionJoueurY));
                    getCasesAdjacentes(positionJoueurX-1, positionJoueurY);
                }
            }

            if(estCompatible(positionJoueurX, positionJoueurY, positionJoueurX+1, positionJoueurY)){
                if(!estDansLaListe(toutesLesCasesAccessibles, positionJoueurX+1, positionJoueurY)){
                    toutesLesCasesAccessibles.add(getCase(positionJoueurX+1, positionJoueurY));
                    getCasesAdjacentes(positionJoueurX+1, positionJoueurY);
                }
            }

            if(estCompatible(positionJoueurX, positionJoueurY, positionJoueurX, positionJoueurY-1)){
                if(!estDansLaListe(toutesLesCasesAccessibles, positionJoueurX, positionJoueurY-1)){
                    toutesLesCasesAccessibles.add(getCase(positionJoueurX, positionJoueurY-1));
                    getCasesAdjacentes(positionJoueurX, positionJoueurY-1);
                }
            }

            if(estCompatible(positionJoueurX, positionJoueurY, positionJoueurX, positionJoueurY+1)){
                if(!estDansLaListe(toutesLesCasesAccessibles, positionJoueurX, positionJoueurY+1)){
                    toutesLesCasesAccessibles.add(getCase(positionJoueurX, positionJoueurY+1));
                    getCasesAdjacentes(positionJoueurX, positionJoueurY+1);
                }
            }

        }

        return toutesLesCasesAccessibles;
    }

    public boolean estDansLaListe(List<Case> caseAccessibles, int caseX, int caseY){
        for(int i=0; i<caseAccessibles.size();i++){
            if(caseAccessibles.get(i).getX() == caseX && caseAccessibles.get(i).getY() == caseY){
                return true;
            }
        }
        return false;
    }
}
