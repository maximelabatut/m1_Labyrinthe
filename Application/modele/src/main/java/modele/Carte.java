package modele;

import java.io.Serializable;

public class Carte implements Serializable {

    // Variables
    private Tresor tresor;

    /**
     * Constructeur d'une carte d'un trésor
     * @param tresor trésor de la carte
     */
    public Carte(Tresor tresor){
        this.tresor = tresor;
    }

    /**
     * Récupère le trésor de la carte
     * @return tresor
     */
    public Tresor getTresor() {
        return tresor;
    }

    @Override
    public String toString() {
        return this.getTresor().toString();
    }
}
