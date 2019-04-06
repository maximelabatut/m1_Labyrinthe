package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.JoueurInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuCreation {
    private Controleur monControleur;
    private CheckBox cbType;
    private ToggleGroup tg;
    List<CheckBox> lesInvites;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private GridPane monForm;

    public static MenuCreation creerInstance(Controleur c,Stage primaryStage) {
        URL location = MenuCreation.class.getResource("/views/menuCreation.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuCreation vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        vue.init();
        return vue;
    }

    public void init(){
        Label nbJoueursLabel = new Label("Nombre maximum de joueurs : ");
        monForm.add(nbJoueursLabel,0,0);
        tg = new ToggleGroup();
        RadioButton rb2 = new RadioButton("2");
        rb2.setUserData(2);
        rb2.setSelected(true);
        RadioButton rb3 = new RadioButton("3");
        rb3.setUserData(3);
        RadioButton rb4 = new RadioButton("4");
        rb4.setUserData(4);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);
        rb4.setToggleGroup(tg);
        GridPane gridRadio = new GridPane();
        gridRadio.addRow(0,rb2);
        gridRadio.addRow(1,rb3);
        gridRadio.addRow(2,rb4);
        monForm.add(gridRadio,1,0);

        Label type = new Label("Type de partie : ");
        monForm.add(type,0,1);
        cbType = new CheckBox("Partie privée");
        monForm.add(cbType,1,1);

        Label invites = new Label("Joueurs à inviter : ");
        monForm.add(invites,0,2);
        lesInvites = new ArrayList<CheckBox>();
        GridPane gridInvites = new GridPane();
        List<JoueurInterface> lesjoueursInvitables = new ArrayList<JoueurInterface>(monControleur.getPopulation());
        for(int i=0;i<lesjoueursInvitables.size();i++){
            if(!lesjoueursInvitables.get(i).getPseudo().equals(monControleur.getPseudoConnecte())) {
                CheckBox cbInvites = new CheckBox(lesjoueursInvitables.get(i).getPseudo());
                lesInvites.add(cbInvites);
                gridInvites.addRow(i,cbInvites);
            }
        }
        ScrollPane sp = new ScrollPane();
        sp.setPadding(new Insets(5,5,5,5));
        sp.setContent(gridInvites);
        monForm.add(sp,1,2);
        monForm.setHgap(10);
        monForm.setVgap(10);
//        grid.setGridLinesVisible(true);

    }

    public void retour(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention !");
        alert.setHeaderText("Annuler la création de la partie.");
        alert.setContentText("Êtes-vous sûr de vouloir annuler la partie ?");

        ButtonType buttonTypeOne = new ButtonType("Oui, je suis sûr");
        ButtonType buttonTypeTwo = new ButtonType("Annuler");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            monControleur.goToMenuPrincipal();
        } else if (result.get() == buttonTypeTwo) {}
    }
    public void deconnexion(ActionEvent actionEvent) { monControleur.deconnexion();}
    public void valider(ActionEvent actionEvent) {
        int index = 0;

        for(int i=0; i<lesInvites.size(); i++){
            if(lesInvites.get(i).isSelected()){
                index++;
            }
        }

        // Le nombre de joueur invité <= nombre de joueur max possible - le créateur
        if(index <= (int)tg.getSelectedToggle().getUserData() - 1){
            monControleur.creerPartie(monControleur.getPseudoConnecte(), (int)tg.getSelectedToggle().getUserData(), cbType.isSelected());
            for(int i=0; i<lesInvites.size(); i++) {
                if (lesInvites.get(i).isSelected()) {
                    monControleur.inviterJoueurs(lesInvites.get(i).getText());
                }
            }
            monControleur.goToLobby();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!!");
            alert.setHeaderText("Nombre de joueur MAX dépassé...");
            alert.setContentText("Vous ne pouvez pas invité plus de joueur que le nombre maximum");
            alert.showAndWait();
        }
    }
}
