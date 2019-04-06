package modele;


import java.io.Serializable;

/**
 *
 */
public enum Tresor implements Serializable {

    // Tresors fixes:
    // ╦
    tresor0(0,"-"),
    tresor1(1,"livre"),
    tresor2(2,"sacOr"),
    tresor5(5,"cle"),
    // ╣
    tresor6(6,"ossement"),
    tresor9(9,"emeraude"),
    tresor10(10,"epee"),
    // ╠
    tresor3(3,"carteTresor"),
    tresor4(4,"couronne"),
    tresor7(7,"bague"),
    // ╩
    tresor8(8,"coffre"),
    tresor11(11,"chandelier"),
    tresor12(12,"heaume"),

    // ----------------
    // Trésors amovibles:
    // Tresors en L
    tresor13(13,"salamandre"),
    tresor14(14,"chouette"),
    tresor15(15,"rat"),
    tresor16(16,"papillon"),
    tresor17(17,"scarabee"),
    tresor18(18,"araignee"),

    // Tresors en T
    tresor19(19,"troll"),
    tresor20(20,"chauveSouris"),
    tresor21(21,"dragon"),
    tresor22(22,"fantomeRieur"),
    tresor23(23,"fee"),
    tresor24(24,"fantomeBoulet");

    /**
     * Variables:
     */
    private int id;
    private String nom;

    /**
     * Constructeur d'un trésor
     * @param id
     * @param nom
     */
    Tresor(int id, String nom) {
        this.id = id;
        this.nom=nom;
    }

    /**
     * Retourne l'id d'un trésor
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le nom d'un trésor
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
