package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.JoueurInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuPopulation {
    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private ScrollPane maBox;

    public static MenuPopulation creerInstance(Controleur c, Stage primaryStage) {
        URL location = MenuPopulation.class.getResource("/views/menuPopulation.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuPopulation vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        vue.initialiserTableau();
        return vue;
    }

    public void initialiserTableau(){
        GridPane grid = new GridPane();
        List<JoueurInterface> lesJoueurs = new ArrayList<JoueurInterface>(monControleur.getPopulation());

        for(int i=0;i<lesJoueurs.size();i++){
            grid.addRow(i+1,new Text("- "+lesJoueurs.get(i).getPseudo()));
        }

        maBox.setContent(grid);
    }

    public void retour(ActionEvent actionEvent) { monControleur.goToMenuPrincipal(); }
    public void deconnexion(ActionEvent actionEvent) { monControleur.deconnexion();}
}
