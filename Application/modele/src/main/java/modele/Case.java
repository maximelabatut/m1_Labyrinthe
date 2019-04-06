package modele;


import java.io.Serializable;

/**
 *
 */
public class Case implements CaseInterface, Serializable {

    // Variables
    public int x, y;
    public CaseForme forme;
    public Tresor tresor;


    /**
     * Constructeur d'une Case
     * @param x coordonnée horizontale x de la case
     * @param y coordonnée verticale y de la case
     * @param forme forme de la case
     * @param tresor le trésor de la case si elle en possède un
     */
    public Case(int x, int y, CaseForme forme, Tresor tresor) {
        this.x = x;
        this.y = y;
        this.forme = forme;
        this.tresor = tresor;
    }

    /**
     * Permet de retourner la forme avec l'orientation de la case
     * @return forme
     */
    public CaseForme getForme() {
        return this.forme;
    }


    /**
     * Permet de retourner le tresor de la case si elle en possède un!
     * @return tresor
     */
    public Tresor getTresor() {
            return this.tresor;
    }


    /**
     * Donne la coordonnée horizontale x de la case
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Donne la coordonnée verticale y de la case
     * @return y
     */
    public int getY() {
        return y;
    }


    /**
     * Modification de la coordonnée verticale y de la case
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Modification de la coordonnée horizontale x de la case
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }


    /**
     * Modification de la forme de la case
     * @param forme
     */
    public void setForme(CaseForme forme) {
        this.forme = forme;
    }


    /**
     * Permet de faire tourner aléatoirement une case
     */
    public void tournerAlea(){
        // modele.Case I
        if(this.getForme().equals(new CaseForme(true, false, true, false)) || this.getForme().equals(new CaseForme(false, true, false, true))){
            double random = Math.random();
            if(random<0.5){
                this.setForme(new CaseForme(true, false, true, false));
            }else{
                this.setForme(new CaseForme(false, true, false, true));
            }
        }

        // modele.Case V
        if(this.getForme().equals(new CaseForme(true, true, false, false)) ||
                this.getForme().equals(new CaseForme(false, true, true, false)) ||
                this.getForme().equals(new CaseForme(false, false, true, true)) ||
                this.getForme().equals(new CaseForme(true, false, false, true))){
            double random = Math.random();
            if(random <0.25){
                this.setForme(new CaseForme(true, true, false, false));
            }else{
                if(random<0.5){
                    this.setForme(new CaseForme(false, true, true, false));
                }else{
                    if(random<0.75){
                        this.setForme(new CaseForme(false, false, true, true));
                    }else{
                        if(random<1){
                            this.setForme(new CaseForme(true, false, false, true));
                        }
                    }
                }
            }
        }

        // modele.Case T
        if(this.getForme().equals(new CaseForme(false, true, true, true)) ||
                this.getForme().equals(new CaseForme(true, false, true, true)) ||
                this.getForme().equals(new CaseForme(true, true, false, true)) ||
                this.getForme().equals(new CaseForme(true, true, true, false))){
            double random = Math.random();
            if(random<0.25){
                this.setForme(new CaseForme(false, true, true, true));
            }else{
                if(random<0.5){
                    this.setForme(new CaseForme(true, false, true, true));
                }else{
                    if(random<0.75){
                        this.setForme(new CaseForme(true, true, false, true));
                    }else{
                        if(random<1){
                            this.setForme(new CaseForme(true, true, true, false));
                        }
                    }
                }
            }
        }
    }

    /**
     * @return true si la case est fixe / false si la case est amovible
     */
    @Override
    public boolean isFixe() {
        if(this.getX()%2 != 0 && this.getY()%2 != 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return la couleur de la case départ sinon retourne rien
     */
    @Override
    public String isCouleur() {
        if(this.getX() == 1) {
            if (this.getY() == 1) {
                return "#FF0000";
            } else {
                if (this.getY() == 7) {
                    return "#008000";
                }
            }
        }else{
            if(this.getX() == 7){
                if(this.getY() == 1){
                    return "#0000FF";
                }else{
                    if(this.getY() == 7){
                        return "#FFA500";
                    }
                }
            }
        }
        return "#000000";
    }

}
