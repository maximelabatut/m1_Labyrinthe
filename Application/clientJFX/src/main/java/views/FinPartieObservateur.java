package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.Joueur;
import modele.Partie;
import modele.Plateau;

import java.io.IOException;
import java.net.URL;

public class FinPartieObservateur {
    private Controleur monControleur;

    @FXML
    private VBox maBox;

    @FXML
    private Label monLabel;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }
    public static FinPartieObservateur creerInstance(Controleur c,Stage primaryStage) {
        URL location = FinPartieObservateur.class.getResource("/views/finPartieObservateur.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FinPartieObservateur vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        vue.init();
        return vue;
    }

    public void init(){
        Partie laPartie= monControleur.getPartieByPseudo(monControleur.getPseudoHost());

        monLabel.setText("Score de la partie de " + monControleur.getPseudoHost() + " : \n");

        GridPane grid = new GridPane();
        Joueur [] listeDesJoueurs = laPartie.getMesJoueurs();
        for(int i=0; i<listeDesJoueurs.length; i++){
            int j=i;
            grid.addRow(i,new Text("Pseudo : "+laPartie.getMesJoueurs()[i].getPseudo()));
            grid.addRow(j+1,new Text("\n"));
            grid.addRow(j+2, new Text("Nombre de trésor restant : "+laPartie.getMesJoueurs()[i].getMesCartes().length));

        }

        maBox.getChildren().addAll(grid);
    }

    public void deconnexion(ActionEvent actionEvent) { monControleur.deconnexion();}
    public void menuPrincipal(ActionEvent actionEvent) { monControleur.goToMenuPrincipal();}

}
