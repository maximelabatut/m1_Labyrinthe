package modele;

import java.io.Serializable;

public interface CaseInterface extends Serializable {

    /**
     * Retourne si la case est fixe ou non
     */
    boolean isFixe();

    /**
     * Retourne la couleur de la case de départ
     */
    String isCouleur();

}
