package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MenuPrincipal {
    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private Text pseudo;
    @FXML
    private Text population;

    public static MenuPrincipal creerInstance(Controleur c, Stage primaryStage) {
        URL location = MenuPrincipal.class.getResource("/views/menuPrincipal.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuPrincipal vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        vue.init();
        return vue;
    }

    public void init(){
        int nbPersonnes = monControleur.getPopulation().size();
        pseudo.setText("Vous êtes connecté en tant que : "+monControleur.getPseudoConnecte());
        population.setText("La population est actuellement de "+ nbPersonnes+" personnes(s).");
    }

    public void creer(ActionEvent actionEvent){
        monControleur.goToMenuCreation();
    }
    public void rejoindre(ActionEvent actionEvent){
        monControleur.goToMenuRejoindre();
    }
    public void observer(ActionEvent actionEvent){
        monControleur.goToMenuObserver();
    }
    public void invitations(ActionEvent actionEvent){
        monControleur.goToMenuInvitations();
    }
    public void population(ActionEvent actionEvent){
        monControleur.goToMenuPopulation();
    }
    public void deconnexion(ActionEvent actionEvent) { monControleur.deconnexion();
    }
}
