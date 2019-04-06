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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Partie;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MenuObserver {
    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private ComboBox selectGame;
    @FXML
    private VBox maBox;

    public static MenuObserver creerInstance(Controleur c,Stage primaryStage) {
        URL location = MenuObserver.class.getResource("/views/menuObserver.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuObserver vue = fxmlLoader.getController();
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
        List<Partie> lesParties = monControleur.getLesPartiesObservables();
        if(lesParties!=null && lesParties.size()!=0){
            selectGame.getItems().addAll(lesParties);
            selectGame.setButtonCell(new MenuObserver.SimpleCarListCell());
            selectGame.getSelectionModel().selectFirst();
            selectGame.setCellFactory(listView -> new MenuObserver.SimpleCarListCell());
            Text text = new Text("Liste des parties observables :");
            maBox.getChildren().addAll(text,selectGame);
        }else{
            maBox.getChildren().remove(selectGame);
            Text text = new Text("Aucune partie n'a été trouvée.");
            maBox.getChildren().add(text);
        }Button refresh = new Button("Rafraichir");
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
        monControleur.rejoindreObservateur(laPartie.getJoueurHost().getPseudo());
    }

    public void retour(ActionEvent actionEvent) { monControleur.goToMenuPrincipal(); }

    public void deconnexion(ActionEvent actionEvent) { monControleur.deconnexion();}
}
