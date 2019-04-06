package views;

import controleur.Controleur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Partie;

import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MenuRejoindre {
    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private ComboBox selectGame;

    @FXML
    private VBox maBox;

    public static MenuRejoindre creerInstance(Controleur c,Stage primaryStage) {
        URL location = MenuRejoindre.class.getResource("/views/menuRejoindre.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuRejoindre vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        vue.init();
        return vue;
    }

    public void refresh(){
        init();
    }

    public void init(){
        maBox.getChildren().clear();
        List<Partie> lesParties = monControleur.getPartiesRejoignables();
        if(lesParties!=null && lesParties.size()!=0) {
            selectGame.getItems().clear();
            selectGame.getItems().addAll(lesParties);
            selectGame.setButtonCell(new MenuRejoindre.SimpleCarListCell());
            selectGame.getSelectionModel().selectFirst();
            selectGame.setCellFactory(listView -> new MenuRejoindre.SimpleCarListCell());
            Text text = new Text("Liste des parties rejoignables :");
            maBox.getChildren().addAll(text,selectGame);
        }else{
            maBox.getChildren().remove(selectGame);
            Text text = new Text("Aucune partie n'a été trouvée.");
            maBox.getChildren().add(text);
        }
        Button refresh = new Button("Rafraichir");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refresh();
            }
        });
        refresh.setPrefWidth(150);
        maBox.getChildren().add(refresh);
    }

    public class SimpleCarListCell extends ListCell<Partie> {
        @Override
        protected void updateItem(Partie item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            if (!empty && item != null) {
                final String text = String.format("%s %s %s %s", item.getJoueurHost().getPseudo(),item.getMesJoueurs().length,"/",item.getNbJoueursMax());
                setText(text);
            }
        }
    }

    public void valider(ActionEvent actionEvent) {
        Partie laPartie = (Partie) selectGame.getValue();
        Partie laPartie2 = monControleur.getPartieByPseudo(laPartie.getJoueurHost().getPseudo());

        if(laPartie2.getMesJoueurs().length == laPartie2.getNbJoueursMax()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Trop Tard !");
            alert.setHeaderText("Partie Complète");
            alert.setContentText("Un joueur a rejoint la partie avant vous !");
            alert.showAndWait();
            monControleur.goToMenuRejoindre();
        }else{
            monControleur.rejoindreJoueur(laPartie.getJoueurHost().getPseudo());
        }
    }

    public void retour(ActionEvent actionEvent) { monControleur.goToMenuPrincipal(); }

    public void deconnexion(ActionEvent actionEvent) { monControleur.deconnexion();}
}
