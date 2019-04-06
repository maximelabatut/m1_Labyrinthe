package views;

import controleur.Controleur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

public class Connexion {

    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private Button valider;
    @FXML
    private Button retour;
    @FXML
    private TextField pseudo;
    @FXML
    private TextField password;

    public static Connexion creerInstance(Controleur c,Stage primaryStage) {
        URL location = Connexion.class.getResource("/views/connexion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connexion vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        return vue;
    }

    public void valider(ActionEvent actionEvent) {
        if (monControleur.connexion(pseudo.getText(), password.getText())) {
            monControleur.goToMenuPrincipal();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!!");
            alert.setHeaderText("Problème d'authentification");
            alert.setContentText("Utilisateur inconnu / Deja Connecte");
            alert.showAndWait();
        }
    }

    public void retour(ActionEvent actionEvent){
        monControleur.goToIndex();
    }
}
