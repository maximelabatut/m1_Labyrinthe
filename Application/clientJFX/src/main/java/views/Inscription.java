package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Inscription {
    public static final int nb_min_char = 2;
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
    private TextField password1;
    @FXML
    private TextField password2;

    public static Inscription creerInstance(Controleur c,Stage primaryStage) {
        URL location = Inscription.class.getResource("/views/inscription.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Inscription vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        return vue;
    }

    public void valider(ActionEvent actionEvent) {
        if(pseudo.getText().length() > nb_min_char && password1.getText().length() > nb_min_char && password2.getText().length() > nb_min_char) {
            if (password1.getText().equals(password2.getText())) {
                if(monControleur.inscription(pseudo.getText(),password1.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Inscription Validée");
                    alert.setHeaderText("Bravo l'inscription c'est bien déroulée!");
                    alert.setContentText("Vous pouvez maintenant vous connecter");
                    alert.show();
                    monControleur.goToIndex();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur!!");
                    alert.setHeaderText("Problème d'inscription");
                    alert.setContentText("Ce pseudo n'est pas disponible");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur!!");
                alert.setHeaderText("Problème de password");
                alert.setContentText("Les deux mots de passe sont pas identiques ");
                alert.showAndWait();
                }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!!");
            alert.setHeaderText("Problème d'inscription");
            alert.setContentText("Votre pseudo et mot de passe doit compter au moins "+(nb_min_char+1)+" caractères.");
            alert.showAndWait();
            }
    }

    public void retour(ActionEvent actionEvent){
        monControleur.goToIndex();
    }
}
