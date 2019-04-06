package modele;

import java.io.Serializable;

public interface JoueurInterface extends Serializable {
    boolean isAuToursDe();

    String getCouleur();

    int getX();

    int getY();

    String getPseudo();

    Carte[] getMesCartes();

    Case getCase();

    void setAuToursDe(boolean auToursDe);

    void setCouleur(String couleur);

    void setX(int x);

    void setY(int y);

    void setMesCartes(Carte[] mesCartes);

    int getIdentifiant();

    String getPassword();

    void setPassword(String password);
}
