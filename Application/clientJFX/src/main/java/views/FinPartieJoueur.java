package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.Partie;

import java.io.IOException;
import java.net.URL;

public class FinPartieJoueur {
    private Controleur monControleur;

    @FXML
    private VBox maBox;
    @FXML
    private Text titreh1;
    @FXML
    private Text titreh2;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    public static FinPartieJoueur creerInstance(Controleur c,Stage primaryStage) {
        URL location = FinPartieJoueur.class.getResource("/views/finPartieJoueur.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FinPartieJoueur vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        vue.init();
        return vue;
    }

    public void init(){
        Partie laPartie = monControleur.getPartieByPseudo(monControleur.getPseudoConnecte());

        if(monControleur.getGagnant().getPseudo().equals(monControleur.getPseudoConnecte())){
            titreh1.setText("Bravo vous avez Gagné ");
        }else{
            titreh1.setText("Dommage vous avez Perdu");
        }

        titreh2.setText("Score de la partie de : ");

        GridPane grid = new GridPane();

        for(int i=0; i<laPartie.getMesJoueurs().length; i++){
            grid.addRow(i,new Text("Pseudo : "+laPartie.getMesJoueurs()[i].getPseudo()+"\n"+"Nombre de trésor restant : "+laPartie.getMesJoueurs()[i].getMesCartes().length+"\n"));
        }

        maBox.getChildren().addAll(grid);
    }

    public void deconnexion(ActionEvent actionEvent) { monControleur.deconnexion(); }
    public void menuPrincipal(ActionEvent actionEvent) {
        monControleur.goToMenuPrincipal();
    }

}
