package modele;

import java.io.Serializable;

public class CaseForme implements Serializable {

    // Variables
    private boolean haut, droite, bas, gauche;

    /**
     * Constructeur de la forme de la case
     * @param haut passage vers le haut
     * @param droite passage vers la droite
     * @param bas passage vers le bas
     * @param gauche passage vers la gauche
     */
    public CaseForme(boolean haut, boolean droite, boolean bas, boolean gauche) {
        this.haut = haut;
        this.droite = droite;
        this.bas = bas;
        this.gauche = gauche;
    }


    /**
     * @return true s'il y a un passage vers le haut
     */
    public boolean isHaut() {
        return haut;
    }

    /**
     * @return true s'il y a un passage vers le bas
     */
    public boolean isBas() {
        return bas;
    }

    /**
     * @return true s'il y a un passage vers la gauche
     */
    public boolean isGauche() {
        return gauche;
    }

    /**
     * @return true s'il y a un passage vers la droite
     */
    public boolean isDroite() {
        return droite;
    }

    /**
     * @param c
     * @return true si la case en paramètre c à la même forme qu'une autre case qui appelle la fonction / false si les deux cases sont différentes
     */
    public boolean equals(CaseForme c){
        if(this.isHaut() == c.isHaut() && this.isBas() == c.isBas() && this.isGauche() == c.isGauche() && this.isDroite() == c.isDroite()){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
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

        if(this.equals(formes[0])){
            return "║";
        }else {
            if (this.equals(formes[1])) {
                return "═";
            } else {
                if (this.equals(formes[2])) {
                    return "╚";
                } else {
                    if (this.equals(formes[3])) {
                        return "╔";
                    } else {
                        if (this.equals(formes[4])) {
                            return "╗";
                        } else {
                            if (this.equals(formes[5])) {
                                return "╝";
                            } else {
                                if (this.equals(formes[6])) {
                                    return "╠";
                                } else {
                                    if (this.equals(formes[7])) {
                                        return "╦";
                                    } else {
                                        if (this.equals(formes[8])) {
                                            return "╣";
                                        } else {
                                            if (this.equals(formes[9])) {
                                                return "╩";
                                            }else{
                                                return " ";
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
}
