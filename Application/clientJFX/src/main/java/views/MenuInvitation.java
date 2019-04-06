package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.Invitation;
import modele.Partie;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuInvitation {
    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private VBox invitations;

    public static MenuInvitation creerInstance(Controleur c,Stage primaryStage) {
        URL location = MenuInvitation.class.getResource("/views/menuInvitation.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuInvitation vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        vue.setMonControleur(c);
        vue.init();
        return vue;
    }

    public void init(){
        List<Invitation> lesInvitationsSeules = monControleur.getInvitationsByPseudo();
        GridPane grid = new GridPane();

        Button refresh = new Button("Rafraichir");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                monControleur.goToMenuInvitations();
            }
        });
        refresh.setPrefWidth(150);

        if(lesInvitationsSeules.size()!=0 && lesInvitationsSeules!=null){
            grid.addRow(0,new Text(),new Text("Type de partie"), new Text("Joueurs"),new Text("Accepter"), new Text("Refuser"));
            grid.setGridLinesVisible(true);
            grid.setAlignment(Pos.CENTER);

            for(int i=0;i<lesInvitationsSeules.size();i++) {
                Text text = new Text();
                Partie laPartie = monControleur.getPartieByPseudo(lesInvitationsSeules.get(i).getJoueurHost());
                if(laPartie!=null) {
                    if (laPartie.isPrive()) {
                        text.setText("Partie privée");
                    } else {
                        text.setText("Partie publique");
                    }

                    Image imageAcc = new Image("/img/accept.png");
                    ImageView imgAcc = new ImageView(imageAcc);
                    imgAcc.setFitHeight(20);
                    imgAcc.setFitWidth(20);
                    Image imageDec = new Image("/img/decline.png");
                    ImageView imgDec = new ImageView(imageDec);
                    imgDec.setFitHeight(20);
                    imgDec.setFitWidth(20);

                    Hyperlink boutonAccept;
                    Hyperlink boutonDeny;
                    boutonAccept = new Hyperlink("");
                    boutonAccept.setGraphic(imgAcc);

                    boutonDeny = new Hyperlink("");
                    boutonDeny.setGraphic(imgDec);

                    int finali = i;

                    boutonAccept.setOnAction((ActionEvent event) -> {

                        if(laPartie.getMesJoueurs().length == laPartie.getNbJoueursMax()) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Trop Tard !");
                            alert.setHeaderText("Partie Complète");
                            alert.setContentText("Un joueur a rejoint la partie avant vous !");
                            alert.showAndWait();
                            monControleur.goToMenuPrincipal();
                        }else{
                            monControleur.accepterPartie(laPartie.getJoueurHost().getPseudo());
                            monControleur.goToLobby();
                        }

                    });

                    boutonDeny.setOnAction((ActionEvent event) -> {
                        monControleur.refuserPartie(lesInvitationsSeules.get(finali).getJoueurHost());
                        monControleur.goToMenuInvitations();
                    });
                    grid.add(new Text("invitation de " + lesInvitationsSeules.get(i).getJoueurHost()), 0, i + 1);
                    grid.add(text, 1, i + 1);
                    grid.add(new Text(laPartie.getMesJoueurs().length + "/" + laPartie.getNbJoueursMax()), 2, i + 1);
                    grid.add(boutonAccept, 3, i + 1);
                    grid.add(boutonDeny, 4, i + 1);
                }
            }

            invitations.getChildren().addAll(grid,refresh);
        }else{
            Text vide = new Text("Aucune invitation n'a été trouvée.");
            invitations.getChildren().addAll(vide,refresh);
        }
    }

    public void retour(ActionEvent actionEvent) { monControleur.goToMenuPrincipal(); }

    public void deconnexion(ActionEvent actionEvent) { monControleur.deconnexion();}
}
