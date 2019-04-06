package modele;

import java.io.Serializable;
import java.util.List;

public interface PlateauInterface extends Serializable {
    // Fonctions
    void initialiserPlateau();

    Case[] getCasesFixes();

    Case[] getCasesAmovibles();

    Case getCaseLibre();

    Case getCase(int x, int y);

    Case[][] getMonPlateau();

    boolean estCompatible(int x1, int y1, int x2, int y2);

    List<Case> getCasesAdjacentes(int x, int y);

    List<Case> getToutesLesCaseClean(int x, int y);
}
