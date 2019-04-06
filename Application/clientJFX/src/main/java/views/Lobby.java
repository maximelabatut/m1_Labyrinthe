package views;

import controleur.Controleur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Joueur;
import modele.Partie;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class Lobby {
    private Controleur monControleur;
    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private VBox maBox;

    @FXML
    private Button valider;

    public static Lobby creerInstance(Controleur c,Stage primaryStage) {
        URL location = Lobby.class.getResource("/views/lobby.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Lobby vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        vue.init();
        vue.run();
        return vue;
    }

    private Timeline timeLineLobby = new Timeline();

    public void run(){
        timeLineLobby = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                init();
            }
        }));
        timeLineLobby.setCycleCount(Timeline.INDEFINITE);
        timeLineLobby.play();
    }

    public void init(){
        maBox.getChildren().clear();
        GridPane grid = new GridPane();

        Partie laPartie = monControleur.getPartieByPseudo(monControleur.getPseudoConnecte());

        if(laPartie==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Partie Annulée !");
            alert.setHeaderText("Le créateur a annulé la partie !");
            alert.setContentText("La partie vient d'être annulée par le joueur hôte.");
            alert.setOnHidden(event -> monControleur.goToMenuPrincipal());
            alert.show();
            monControleur.goToMenuPrincipal();
            timeLineLobby.stop();
        }else {
            if (laPartie.monPlateau != null) {
                monControleur.goToPartieJoueur();
                timeLineLobby.stop();
            }else {

                Text label1 = new Text("Créateur de la partie");
                grid.add(label1, 0, 0);
                Text label11 = new Text(laPartie.getJoueurHost().getPseudo());
                grid.add(label11, 1, 0);

                Text label2 = new Text("Nombre de joueurs maximum");
                grid.add(label2, 0, 1);
                Text label21 = new Text(((Integer) laPartie.getNbJoueursMax()).toString());
                grid.add(label21, 1, 1);

                Text label3 = new Text("Type de partie");
                grid.add(label3, 0, 2);
                String prive;
                if (laPartie.isPrive()) {
                    prive = "Privée";
                } else {
                    prive = "Publique";
                }
                Text label31 = new Text(prive);
                grid.add(label31, 1, 2);

                Text label4 = new Text("Joueurs prêts");
                grid.add(label4, 0, 3);
                Joueur[] mesJoueurs = laPartie.getMesJoueurs();
                GridPane lesJoueurs = new GridPane();
                for (int i = 0; i < mesJoueurs.length; i++) {
                    Text pseudo = new Text(mesJoueurs[i].getPseudo());
                    lesJoueurs.add(pseudo, 0, i);
                }
                grid.add(lesJoueurs, 1, 3);

                grid.setGridLinesVisible(true);

                if (mesJoueurs.length >= 2 && laPartie.getJoueurHost().getPseudo().equals(monControleur.getPseudoConnecte())) {
                    valider.setVisible(true);
                } else {
                    valider.setVisible(false);
                }
                grid.setAlignment(Pos.CENTER);
                maBox.getChildren().add(grid);
            }
        }
    }

    public void valider(ActionEvent actionEvent) { timeLineLobby.stop();monControleur.lancerPartie(); }

    public void retour(ActionEvent actionEvent) {
        if(monControleur.getPseudoHost().equals(monControleur.getPseudoConnecte())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText("Annuler la création de la partie.");
            alert.setContentText("Êtes-vous sûr de vouloir annuler la partie ?");

            ButtonType buttonTypeOne = new ButtonType("Oui, je suis sûr");
            ButtonType buttonTypeTwo = new ButtonType("Annuler");
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                monControleur.annulePartie();
                timeLineLobby.stop();
                monControleur.goToMenuPrincipal();
            } else if (result.get() == buttonTypeTwo) {}
        }else {
            monControleur.quitterPartie();
            timeLineLobby.stop();
            monControleur.goToMenuPrincipal();
        }
    }

    public void deconnexion(ActionEvent actionEvent) { timeLineLobby.stop();monControleur.deconnexion();}
}
