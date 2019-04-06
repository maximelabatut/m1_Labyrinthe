package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class Index {


    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private Button inscription;
    @FXML
    private Button connexion;

    public static Index creerInstance(Controleur c,Stage primaryStage) {
        URL location = Index.class.getResource("/views/index.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Index vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        return vue;
    }

    public void inscription(ActionEvent actionEvent) {
        monControleur.goToInscription();
    }

    public void connexion(ActionEvent actionEvent) {
        monControleur.goToConnexion();
    }

}
