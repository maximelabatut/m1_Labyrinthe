package views;

import controleur.Controleur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Carte;
import modele.Case;
import modele.Partie;
import modele.Plateau;

import java.io.IOException;
import java.net.URL;

public class PartieObservateur {
    public static final float CASE_SIZE = 60;
    private Controleur monControleur;
    private Timeline timeLine;

    @FXML
    private HBox maBox;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }
    public static PartieObservateur creerInstance(Controleur c,Stage primaryStage) {
        URL location = PartieObservateur.class.getResource("/views/partieObservateur.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setStyle("-fx-background-color: #FFFFFF;");
        PartieObservateur vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");
        primaryStage.setScene(new Scene(root, 800, 620));
        primaryStage.show();
        vue.setMonControleur(c);
        vue.init();
        vue.run();
        return vue;
    }


    public void run(){
        timeLine = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Partie laPartie= monControleur.getPartieByPseudo(monControleur.getPseudoHost());
                if(laPartie == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Partie TERMINEE");
                    alert.setHeaderText("L'un des joueurs a quitté la partie");
                    alert.setContentText("Un joueur a quitté la partie, elle a donc été détruite, bon jeu quand même");
                    alert.show();
                    timeLine.stop();
                    monControleur.goToMenuPrincipal();
                }else{
                    init();
                }
            }
        }));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    public void init(){
        maBox.getChildren().clear();
        Partie laPartie= monControleur.getPartieByPseudo(monControleur.getPseudoHost());
        Plateau monPlateau = laPartie.monPlateau;

        GridPane plateau = new GridPane();

        Hyperlink bouton;

        if(laPartie.partieTerminee()){
            monControleur.goToFinDePartieObservateur();
        }

        //2 VBox pour gérer l'affichage
        VBox boxPlateau = new VBox();
        VBox parametreJoueur = new VBox();

        for (int x=0;x<monPlateau.getTaille();x++){
            for(int y=0;y<monPlateau.getTaille();y++){

                int finalx=x;
                int finaly=y;
                // HAUT
                if(y==0 && x!=0 && x!=8 && x%2==0){
                    VBox box = new VBox();
                    box.setAlignment(Pos.CENTER);
                    plateau.add(box,x,y);
                }else{
                    // BAS
                    if(y==8 && x!=0 && x!=8 && x%2==0){
                        VBox box = new VBox();
                        box.setAlignment(Pos.CENTER);
                        plateau.add(box,x,y);
                    }else{
                        // GAUCHE
                        if(x==0 && y!=0 && y!=8 && y%2==0){
                            VBox box = new VBox();
                            box.setAlignment(Pos.CENTER);
                            plateau.add(box,x,y);
                        }else{
                            // DROITE
                            if(x==8 && y!=0 && y!=8 && y%2==0){
                                VBox box = new VBox();
                                box.setAlignment(Pos.CENTER);
                                plateau.add(box,x,y);
                            }else{
                                if(y!=0 && x!=0 && y!=8 && x!=8) {
                                    // La case est dans le plateau
                                    // Grille contenant la case
                                    GridPane laCase = new GridPane();
                                    for (int i = 0; i < 5; i++) {
                                        ColumnConstraints column = new ColumnConstraints(CASE_SIZE/6);
                                        RowConstraints row = new RowConstraints(CASE_SIZE/6);
                                        laCase.getColumnConstraints().add(column);
                                        laCase.getRowConstraints().add(row);
                                    }
                                    laCase.setMinHeight(CASE_SIZE);
                                    laCase.setMinWidth(CASE_SIZE);

                                    // On définit les murs "fixes"
                                    // HAUT-GAUCHE
                                    String color = monPlateau.getCase(y,x).isCouleur();
                                    laCase.add(getBox(color), 0, 0);
                                    laCase.add(getBox(color), 0, 1);
                                    laCase.add(getBox(color), 1, 0);
                                    // HAUT-DROITE
                                    laCase.add(getBox(color), 4, 0);
                                    laCase.add(getBox(color), 5, 0);
                                    laCase.add(getBox(color), 5, 1);
                                    // BAS-GAUCHE
                                    laCase.add(getBox(color), 0, 4);
                                    laCase.add(getBox(color), 0, 5);
                                    laCase.add(getBox(color), 1, 5);
                                    // BAS-DROITE
                                    laCase.add(getBox(color), 4, 5);
                                    laCase.add(getBox(color), 5, 4);
                                    laCase.add(getBox(color), 5, 5);

                                    // OUVERTURE HAUT OU NON
                                    if (monPlateau.getCase(y, x).getForme().isHaut()) {
                                        laCase.add(getBox("#FFFFFF"), 2, 0);
                                        laCase.add(getBox("#FFFFFF"), 3, 0);
                                    } else {
                                        laCase.add(getBox(color), 2, 0);
                                        laCase.add(getBox(color), 3, 0);
                                    }

                                    // OUVERTURE BAS OU NON
                                    if (monPlateau.getCase(y, x).getForme().isBas()) {
                                        laCase.add(getBox("#FFFFFF"), 2, 5);
                                        laCase.add(getBox("#FFFFFF"), 3, 5);
                                    } else {
                                        laCase.add(getBox(color), 2, 5);
                                        laCase.add(getBox(color), 3, 5);
                                    }

                                    // OUVERTURE GAUCHE OU NON
                                    if (monPlateau.getCase(y, x).getForme().isGauche()) {
                                        laCase.add(getBox("#FFFFFF"), 0, 2);
                                        laCase.add(getBox("#FFFFFF"), 0, 3);
                                    } else {
                                        laCase.add(getBox(color), 0, 2);
                                        laCase.add(getBox(color), 0, 3);
                                    }

                                    // OUVERTURE DROITE OU NON
                                    if (monPlateau.getCase(y, x).getForme().isDroite()) {
                                        laCase.add(getBox("#FFFFFF"), 5, 2);
                                        laCase.add(getBox("#FFFFFF"), 5, 3);
                                    } else {
                                        laCase.add(getBox(color), 5, 2);
                                        laCase.add(getBox(color), 5, 3);
                                    }
                                    if(!monPlateau.getCase(y,x).tresor.toString().equals("-")) {
                                        Image tresor = new Image("img/plateau/img_tresors/" + monPlateau.getCase(y, x).tresor.toString() + ".png");
                                        ImageView imageViewT = new ImageView(tresor);
                                        imageViewT.setFitHeight(CASE_SIZE/3);
                                        imageViewT.setFitWidth(CASE_SIZE/3);
                                        laCase.add(imageViewT, 2, 3, 2, 2);
                                    }
                                    for(int i=0;i<laPartie.getMesJoueurs().length;i++){
                                        if(laPartie.getMesJoueurs()[i].getX()==y && laPartie.getMesJoueurs()[i].getY()==x){
                                            laCase.add(getBox(laPartie.getMesJoueurs()[i].getCouleur()), i+1, 2);
                                        }else{
                                            laCase.add(getBox("#FFFFFF"), i+1, 2);
                                        }
                                    }
                                    plateau.add(laCase, x, y);
                                }
                            }
                        }
                    }
                }
            }
        }
        boxPlateau.getChildren().add(plateau);

        //Parametre Joueur
        Text listejoueurs = new Text();
        listejoueurs.setText("Voici la liste des joueurs dans la partie :" + " \n " );
        parametreJoueur.getChildren().add(listejoueurs);

        for(int i=0;i<laPartie.getMesJoueurs().length;i++){
            Text pseudoJoueur = new Text();
            pseudoJoueur.setText("• " + laPartie.getMesJoueurs()[i].getPseudo()+ " - " + " \n " );
            VBox boxCouleur = getBox(laPartie.getMesJoueurs()[i].getCouleur());

            HBox box = new HBox();
            box.getChildren().addAll(pseudoJoueur, boxCouleur);

            parametreJoueur.getChildren().add(box);
        }

        Text auTourDe = new Text();
        auTourDe.setText("c'est au tour de : "+monControleur.getJoueurEnCours() + " \n ");
        parametreJoueur.getChildren().add(auTourDe);

        Text tresorARecup = new Text("Voici les trésors à récupérer :" + " \n ");
        parametreJoueur.getChildren().add(tresorARecup);
        GridPane tableauTresor = new GridPane();

        for(int i=0;i<laPartie.getMesJoueurs().length;i++) {
            tableauTresor.add(new Text(laPartie.getMesJoueurs()[i].getPseudo()), 0, i);

            Carte[] lesCartes = laPartie.getMesJoueurs()[i].getMesCartes();
            for (int j = 0; j < lesCartes.length; j++) {
                Image tresor = new Image("img/plateau/img_tresors/" + lesCartes[j].getTresor().toString() + ".png");
                ImageView imageViewT = new ImageView(tresor);
                imageViewT.setFitHeight(CASE_SIZE/2);
                imageViewT.setFitWidth(CASE_SIZE/2);
                tableauTresor.setMargin(imageViewT, new Insets(5, 5, 5, 5));

                tableauTresor.add(imageViewT, j + 1, i);
            }
        }

        tableauTresor.setGridLinesVisible(true);

        parametreJoueur.getChildren().add(tableauTresor);
        boxPlateau.setStyle("-fx-border-width: 0 2 0 0; -fx-border-color: black; -fx-border-style: dashed; -fx-padding: 0 20 0 0 ;");
        parametreJoueur.setStyle("-fx-padding: 20px;");
        maBox.getChildren().addAll(boxPlateau, parametreJoueur);
    }

    public VBox getBox(String color){
        VBox blackBox = new VBox();
        blackBox.prefWidth(CASE_SIZE/6);
        blackBox.prefHeight(CASE_SIZE/6);
        blackBox.setMinHeight(CASE_SIZE/6);
        blackBox.setMinWidth(CASE_SIZE/6);
        blackBox.setMaxHeight(CASE_SIZE/6);
        blackBox.setMaxWidth(CASE_SIZE/6);
        blackBox.setBackground(new Background(new BackgroundFill(Color.web(color), CornerRadii.EMPTY, Insets.EMPTY)));
        return blackBox;
    }

    public void retour(ActionEvent actionEvent) { monControleur.goToMenuObserver();}
    public void deconnexion(ActionEvent actionEvent) { monControleur.deconnexion();}

}
