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
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import modele.Carte;
import modele.Case;
import modele.Partie;
import modele.Plateau;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;

public class PartieJoueur {
    public static final float CASE_SIZE = 60;
    private Controleur monControleur;
    private static Timeline timeLine;
    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private HBox maBox;

    public static PartieJoueur creerInstance(Controleur c,Stage primaryStage) {
        URL location = PartieJoueur.class.getResource("/views/partieJoueur.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setStyle("-fx-background-color: #FFFFFF;");
        PartieJoueur vue = fxmlLoader.getController();
        primaryStage.setTitle("Labyrinthe - TDL");

        primaryStage.setScene(new Scene(root, 800, 620));
        primaryStage.show();
        vue.setMonControleur(c);
        vue.init();
        vue.run();
        return vue;
    }

    private int cptTour=0;
    private int cptAfk=0;

    public void run(){
        timeLine = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                cptTour+=3;
                cptAfk+=3;
                Partie laPartie = monControleur.getPartieByPseudo(monControleur.getPseudoConnecte());

                if (laPartie == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Partie TERMINEE");
                    alert.setHeaderText("L'un des joueurs a quitté la partie");
                    alert.setContentText("Un joueur a quitté la partie, elle a donc été détruite, bon jeu quand même");
                    alert.show();
                    timeLine.stop();
                    monControleur.goToMenuPrincipal();
                } else {
                    if(monControleur.getPseudoConnecte() == null){
                        cptAfk=0;
                        cptTour=0;
                    }
                    init();
                    if(monControleur.getJoueurEnCours().equals(monControleur.getPseudoConnecte())){
                        if(cptTour==15) {
                            monControleur.passerTour();
                            init();
                        }
                        if(cptAfk==120){
                            timeLine.stop();
                            monControleur.quitterPartie();
                            monControleur.deconnexion();
                        }
                    }else {
                        cptTour=0;
                    }
                }
                System.out.println("TIMELINE "+cptTour+" , "+cptAfk);
            }
        }));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    public void init() {
        maBox.getChildren().clear();

        Partie laPartie = monControleur.getPartieByPseudo(monControleur.getPseudoConnecte());

        if(laPartie.monPlateau!=null){
            String pseudoJoueurEnCours = monControleur.getJoueurEnCours();

            Plateau monPlateau = laPartie.monPlateau;

            GridPane plateau = new GridPane();

            Hyperlink bouton;

            if (laPartie.partieTerminee()) {
                monControleur.goToFinDePartie();
                timeLine.stop();
            }

            //2 VBox pour gérer l'affichage
            VBox boxPlateau = new VBox();
            VBox parametreJoueur = new VBox();

            if (pseudoJoueurEnCours.equals(monControleur.getPseudoConnecte())) {
                if (!monControleur.placerLaCase) {
                    for (int x = 0; x < monPlateau.getTaille(); x++) {
                        for (int y = 0; y < monPlateau.getTaille(); y++) {

                            int finalx = x;
                            int finaly = y;
                            // HAUT
                            if (y == 0 && x != 0 && x != 8 && x % 2 == 0) {
                                if (x != monControleur.dejaPousserY()) {
                                    Image image = new Image("/img/plateau/fleche_haut.png");
                                    ImageView imageView = new ImageView(image);
                                    imageView.setFitHeight(CASE_SIZE / 2);
                                    imageView.setFitWidth(CASE_SIZE / 2);
                                    bouton = new Hyperlink("");
                                    bouton.setGraphic(imageView);
                                    VBox box = new VBox();
                                    box.getChildren().add(bouton);
                                    box.setAlignment(Pos.CENTER);
                                    plateau.add(box, x, y);
                                    bouton.setOnAction((ActionEvent event) -> {
                                        pousser(finaly + 1, finalx);
                                    });
                                } else {
                                    Image image = new Image("/img/plateau/croix_rouge.png");
                                    ImageView imageView = new ImageView(image);
                                    imageView.setFitHeight(CASE_SIZE / 2);
                                    imageView.setFitWidth(CASE_SIZE / 2);
                                    VBox box = new VBox();
                                    box.getChildren().add(imageView);
                                    box.setAlignment(Pos.CENTER);
                                    plateau.add(box, x, y);
                                }
                            } else {
                                // BAS
                                if (y == 8 && x != 0 && x != 8 && x % 2 == 0) {
                                    if (x != monControleur.dejaPousserY()) {
                                        Image image = new Image("/img/plateau/fleche_bas.png");
                                        ImageView imageView = new ImageView(image);
                                        imageView.setFitHeight(CASE_SIZE / 2);
                                        imageView.setFitWidth(CASE_SIZE / 2);
                                        bouton = new Hyperlink("");
                                        bouton.setGraphic(imageView);
                                        VBox box = new VBox();
                                        box.getChildren().add(bouton);
                                        box.setAlignment(Pos.CENTER);
                                        plateau.add(box, x, y);
                                        bouton.setOnAction((ActionEvent event) -> {
                                            pousser(finaly - 1, finalx);
                                        });
                                    } else {
                                        Image image = new Image("/img/plateau/croix_rouge.png");
                                        ImageView imageView = new ImageView(image);
                                        imageView.setFitHeight(CASE_SIZE / 2);
                                        imageView.setFitWidth(CASE_SIZE / 2);
                                        VBox box = new VBox();
                                        box.getChildren().add(imageView);
                                        box.setAlignment(Pos.CENTER);
                                        plateau.add(box, x, y);
                                    }
                                } else {
                                    // GAUCHE
                                    if (x == 0 && y != 0 && y != 8 && y % 2 == 0) {
                                        if (y != monControleur.dejaPousserX()) {
                                            Image image = new Image("/img/plateau/fleche_gauche.png");
                                            ImageView imageView = new ImageView(image);
                                            imageView.setFitHeight(CASE_SIZE / 2);
                                            imageView.setFitWidth(CASE_SIZE / 2);
                                            bouton = new Hyperlink("");
                                            bouton.setGraphic(imageView);
                                            VBox box = new VBox();
                                            box.getChildren().add(bouton);
                                            box.setAlignment(Pos.CENTER);
                                            plateau.add(box, x, y);
                                            bouton.setOnAction((ActionEvent event) -> {
                                                pousser(finaly, finalx + 1);
                                            });
                                        } else {
                                            Image image = new Image("/img/plateau/croix_rouge.png");
                                            ImageView imageView = new ImageView(image);
                                            imageView.setFitHeight(CASE_SIZE / 2);
                                            imageView.setFitWidth(CASE_SIZE / 2);
                                            VBox box = new VBox();
                                            box.getChildren().add(imageView);
                                            box.setAlignment(Pos.CENTER);
                                            plateau.add(box, x, y);
                                        }
                                    } else {
                                        // DROITE
                                        if (x == 8 && y != 0 && y != 8 && y % 2 == 0) {
                                            if (y != monControleur.dejaPousserX()) {
                                                Image image = new Image("/img/plateau/fleche_droite.png");
                                                ImageView imageView = new ImageView(image);
                                                imageView.setFitHeight(CASE_SIZE / 2);
                                                imageView.setFitWidth(CASE_SIZE / 2);
                                                bouton = new Hyperlink("");
                                                bouton.setGraphic(imageView);
                                                VBox box = new VBox();
                                                box.getChildren().add(bouton);
                                                box.setAlignment(Pos.CENTER);
                                                plateau.add(box, x, y);
                                                bouton.setOnAction((ActionEvent event) -> {
                                                    pousser(finaly, finalx - 1);
                                                });
                                            } else {
                                                Image image = new Image("/img/plateau/croix_rouge.png");
                                                ImageView imageView = new ImageView(image);
                                                imageView.setFitHeight(CASE_SIZE / 2);
                                                imageView.setFitWidth(CASE_SIZE / 2);
                                                VBox box = new VBox();
                                                box.getChildren().add(imageView);
                                                box.setAlignment(Pos.CENTER);
                                                plateau.add(box, x, y);
                                            }
                                        } else {
                                            if (y != 0 && x != 0 && y != 8 && x != 8) {
                                                // La case est dans le plateau
                                                // Grille contenant la case
                                                GridPane laCase = new GridPane();
                                                for (int i = 0; i < 5; i++) {
                                                    ColumnConstraints column = new ColumnConstraints(CASE_SIZE / 6);
                                                    RowConstraints row = new RowConstraints(CASE_SIZE / 6);
                                                    laCase.getColumnConstraints().add(column);
                                                    laCase.getRowConstraints().add(row);
                                                }
                                                laCase.setMinHeight(CASE_SIZE);
                                                laCase.setMinWidth(CASE_SIZE);

                                                // On définit les murs "fixes"
                                                // HAUT-GAUCHE
                                                String color = monPlateau.getCase(y, x).isCouleur();
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
                                                if (!monPlateau.getCase(y, x).tresor.toString().equals("-")) {
                                                    Image tresor = new Image("img/plateau/img_tresors/" + monPlateau.getCase(y, x).tresor.toString() + ".png");
                                                    ImageView imageViewT = new ImageView(tresor);
                                                    imageViewT.setFitHeight(CASE_SIZE / 3);
                                                    imageViewT.setFitWidth(CASE_SIZE / 3);
                                                    laCase.add(imageViewT, 2, 3, 2, 2);
                                                }
                                                for (int i = 0; i < laPartie.getMesJoueurs().length; i++) {
                                                    if (laPartie.getMesJoueurs()[i].getX() == y && laPartie.getMesJoueurs()[i].getY() == x) {
                                                        laCase.add(getBox(laPartie.getMesJoueurs()[i].getCouleur()), i + 1, 2);
                                                    } else {
                                                        laCase.add(getBox("#FFFFFF"), i + 1, 2);
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
                } else {
                    for (int x = 0; x < monPlateau.getTaille(); x++) {
                        for (int y = 0; y < monPlateau.getTaille(); y++) {

                            int finalx = x;
                            int finaly = y;

                            if (y != 0 && x != 0 && y != 8 && x != 8) {
                                // La case est dans le plateau
                                // Grille contenant la case
                                GridPane laCase = new GridPane();
                                for (int i = 0; i < 5; i++) {
                                    ColumnConstraints column = new ColumnConstraints(CASE_SIZE / 6);
                                    RowConstraints row = new RowConstraints(CASE_SIZE / 6);
                                    laCase.getColumnConstraints().add(column);
                                    laCase.getRowConstraints().add(row);
                                }
                                laCase.setMinHeight(CASE_SIZE);
                                laCase.setMinWidth(CASE_SIZE);

                                // On définit les murs "fixes"
                                // HAUT-GAUCHE
                                String color = monPlateau.getCase(y, x).isCouleur();
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
                                if (!monPlateau.getCase(y, x).tresor.toString().equals("-")) {
                                    Image tresor = new Image("img/plateau/img_tresors/" + monPlateau.getCase(y, x).tresor.toString() + ".png");
                                    ImageView imageViewT = new ImageView(tresor);
                                    imageViewT.setFitHeight(CASE_SIZE / 3);
                                    imageViewT.setFitWidth(CASE_SIZE / 3);
                                    laCase.add(imageViewT, 2, 3, 2, 2);
                                }
                                for (int i = 0; i < laPartie.getMesJoueurs().length; i++) {
                                    if (laPartie.getMesJoueurs()[i].getX() == y && laPartie.getMesJoueurs()[i].getY() == x) {
                                        laCase.add(getBox(laPartie.getMesJoueurs()[i].getCouleur()), i + 1, 2);
                                    } else {
                                        laCase.add(getBox("#FFFFFF"), i + 1, 2);
                                    }
                                }
                                laCase.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        laCase.setCursor(Cursor.HAND);
                                    }
                                });

                                laCase.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        deplacerJoueur(finaly, finalx);
                                    }
                                });
                                laCase.getOnMouseClicked();
                                plateau.add(laCase, x, y);
                            }
                        }
                    }
                }
            } else {
                for (int x = 0; x < monPlateau.getTaille(); x++) {
                    for (int y = 0; y < monPlateau.getTaille(); y++) {

                        int finalx = x;
                        int finaly = y;

                        if (y != 0 && x != 0 && y != 8 && x != 8) {
                            // La case est dans le plateau
                            // Grille contenant la case
                            GridPane laCase = new GridPane();
                            for (int i = 0; i < 5; i++) {
                                ColumnConstraints column = new ColumnConstraints(CASE_SIZE / 6);
                                RowConstraints row = new RowConstraints(CASE_SIZE / 6);
                                laCase.getColumnConstraints().add(column);
                                laCase.getRowConstraints().add(row);
                            }
                            laCase.setMinHeight(CASE_SIZE);
                            laCase.setMinWidth(CASE_SIZE);

                            // On définit les murs "fixes"
                            // HAUT-GAUCHE
                            String color = monPlateau.getCase(y, x).isCouleur();
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
                            if (!monPlateau.getCase(y, x).tresor.toString().equals("-")) {
                                Image tresor = new Image("img/plateau/img_tresors/" + monPlateau.getCase(y, x).tresor.toString() + ".png");
                                ImageView imageViewT = new ImageView(tresor);
                                imageViewT.setFitHeight(CASE_SIZE / 3);
                                imageViewT.setFitWidth(CASE_SIZE / 3);
                                laCase.add(imageViewT, 2, 3, 2, 2);
                            }
                            for (int i = 0; i < laPartie.getMesJoueurs().length; i++) {
                                if (laPartie.getMesJoueurs()[i].getX() == y && laPartie.getMesJoueurs()[i].getY() == x) {
                                    laCase.add(getBox(laPartie.getMesJoueurs()[i].getCouleur()), i + 1, 2);
                                } else {
                                    laCase.add(getBox("#FFFFFF"), i + 1, 2);
                                }
                            }
                            plateau.add(laCase, x, y);
                        }
                    }
                }
            }
            boxPlateau.getChildren().add(plateau);

            //Parametre Joueur
            Text listejoueurs = new Text();
            listejoueurs.setText("Voici la liste des joueurs dans la partie :");
            parametreJoueur.getChildren().add(listejoueurs);
            for (int i = 0; i < laPartie.getMesJoueurs().length; i++) {
                Text pseudoJoueur = new Text();
                if (monControleur.getPseudoConnecte().equals(laPartie.getMesJoueurs()[i].getPseudo())) {
                    pseudoJoueur.setText("• [VOUS] " + laPartie.getMesJoueurs()[i].getPseudo() + " - ");
                } else {
                    pseudoJoueur.setText("• " + laPartie.getMesJoueurs()[i].getPseudo() + " - ");
                }
                VBox boxCouleur = getBox(laPartie.getMesJoueurs()[i].getCouleur());

                HBox box = new HBox();
                box.getChildren().addAll(pseudoJoueur, boxCouleur);

                parametreJoueur.getChildren().add(box);
            }

            if(pseudoJoueurEnCours.equals(monControleur.getPseudoConnecte())){
                Text caseAPlacer = new Text("Voici la case à placer :");
                parametreJoueur.getChildren().add(caseAPlacer);

                Case caseLibre = laPartie.monPlateau.getCaseLibre();
                // HAUT-GAUCHE
                GridPane laCaseLibre = new GridPane();
                laCaseLibre.add(getBox("#000000"), 0, 0);
                laCaseLibre.add(getBox("#000000"), 0, 1);
                laCaseLibre.add(getBox("#000000"), 1, 0);
                // HAUT-DROITE
                laCaseLibre.add(getBox("#000000"), 4, 0);
                laCaseLibre.add(getBox("#000000"), 5, 0);
                laCaseLibre.add(getBox("#000000"), 5, 1);
                // BAS-GAUCHE
                laCaseLibre.add(getBox("#000000"), 0, 4);
                laCaseLibre.add(getBox("#000000"), 0, 5);
                laCaseLibre.add(getBox("#000000"), 1, 5);
                // BAS-DROITE
                laCaseLibre.add(getBox("#000000"), 4, 5);
                laCaseLibre.add(getBox("#000000"), 5, 4);
                laCaseLibre.add(getBox("#000000"), 5, 5);

                // OUVERTURE HAUT OU NON
                if (caseLibre.getForme().isHaut()) {
                    laCaseLibre.add(getBox("#FFFFFF"), 2, 0);
                    laCaseLibre.add(getBox("#FFFFFF"), 3, 0);
                } else {
                    laCaseLibre.add(getBox("#000000"), 2, 0);
                    laCaseLibre.add(getBox("#000000"), 3, 0);
                }

                // OUVERTURE BAS OU NON
                if (caseLibre.getForme().isBas()) {
                    laCaseLibre.add(getBox("#FFFFFF"), 2, 5);
                    laCaseLibre.add(getBox("#FFFFFF"), 3, 5);
                } else {
                    laCaseLibre.add(getBox("#000000"), 2, 5);
                    laCaseLibre.add(getBox("#000000"), 3, 5);
                }

                // OUVERTURE GAUCHE OU NON
                if (caseLibre.getForme().isGauche()) {
                    laCaseLibre.add(getBox("#FFFFFF"), 0, 2);
                    laCaseLibre.add(getBox("#FFFFFF"), 0, 3);
                } else {
                    laCaseLibre.add(getBox("#000000"), 0, 2);
                    laCaseLibre.add(getBox("#000000"), 0, 3);
                }

                // OUVERTURE DROITE OU NON
                if (caseLibre.getForme().isDroite()) {
                    laCaseLibre.add(getBox("#FFFFFF"), 5, 2);
                    laCaseLibre.add(getBox("#FFFFFF"), 5, 3);
                } else {
                    laCaseLibre.add(getBox("#000000"), 5, 2);
                    laCaseLibre.add(getBox("#000000"), 5, 3);
                }
                if (!caseLibre.tresor.toString().equals("-")) {
                    Image tresor = new Image("img/plateau/img_tresors/" + caseLibre.tresor.toString() + ".png");
                    ImageView imageViewT = new ImageView(tresor);
                    imageViewT.setFitHeight(CASE_SIZE / 3);
                    imageViewT.setFitWidth(CASE_SIZE / 3);
                    laCaseLibre.add(imageViewT, 2, 3, 2, 2);
                }
                laCaseLibre.setStyle("-fx-padding:5px;");
                parametreJoueur.getChildren().add(laCaseLibre);
            }

            if (pseudoJoueurEnCours.equals(monControleur.getPseudoConnecte()) && !monControleur.placerLaCase) {
                HBox boxPivoter = new HBox();

                Image imagePlus = new Image("/img/plateau/pivoter_plus.png");
                ImageView imageViewPlus = new ImageView(imagePlus);
                imageViewPlus.setFitHeight(CASE_SIZE / 3);
                imageViewPlus.setFitWidth(CASE_SIZE / 3);
                Button pivoterPlus = new Button();
                pivoterPlus.setGraphic(imageViewPlus);
                pivoterPlus.setMaxWidth(CASE_SIZE / 2);
                boxPivoter.getChildren().add(pivoterPlus);
                pivoterPlus.setOnAction((ActionEvent event) -> {
                    pivoterPlus();
                });

                Image imageMinus = new Image("/img/plateau/pivoter_minus.png");
                ImageView imageViewMinus = new ImageView(imageMinus);
                imageViewMinus.setFitHeight(CASE_SIZE / 3);
                imageViewMinus.setFitWidth(CASE_SIZE / 3);
                Button pivoterMinus = new Button();
                pivoterMinus.setGraphic(imageViewMinus);
                boxPivoter.getChildren().add(pivoterMinus);
                pivoterMinus.setOnAction((ActionEvent event) -> {
                    pivoterMinus();

                });

                parametreJoueur.getChildren().addAll(boxPivoter);
            }

            if (monControleur.placerLaCase) {
                Button passerTour = new Button("Passer son Tour");
                parametreJoueur.getChildren().add(passerTour);
                passerTour.setOnAction((ActionEvent event) -> {
                    passerSonTour();
                });
            }

            Text auTourDe = new Text();

            if (pseudoJoueurEnCours.equals(monControleur.getPseudoConnecte())) {
                auTourDe.setText("A votre tour de jouer !");
            } else {
                auTourDe.setText("c'est au tour de : " + pseudoJoueurEnCours);
            }
            parametreJoueur.getChildren().add(auTourDe);

            Text tresorARecup = new Text("Voici le trésor à récupérer :");
            parametreJoueur.getChildren().add(tresorARecup);

            Carte[] mesCartes = monControleur.getCartesJoueur(monControleur.getPseudoConnecte());

            if (mesCartes.length - 1 >= 0) {
                Image tresor = new Image("img/plateau/img_tresors/" + mesCartes[mesCartes.length - 1].getTresor().getNom() + ".png");
                ImageView imageViewT = new ImageView(tresor);
                imageViewT.setFitHeight((CASE_SIZE / 3) * 2);
                imageViewT.setFitWidth((CASE_SIZE / 3) * 2);
                parametreJoueur.getChildren().add(imageViewT);

                Text tresorRestant = new Text("Il vous reste encore " + (monControleur.getCartesJoueur(monControleur.getPseudoConnecte()).length - 1) + " trésor(s) à récupérer.");
                parametreJoueur.getChildren().add(tresorRestant);
            }
            boxPlateau.setStyle("-fx-border-width: 0 2 0 0; -fx-border-color: black; -fx-border-style: dashed; -fx-padding: 0 20 0 0 ;");
            parametreJoueur.setStyle("-fx-padding: 20;");

            Button quitterPartie = new Button("Quitter la Partie");
            parametreJoueur.getChildren().add(quitterPartie);
            quitterPartie.setOnAction((ActionEvent event) -> {
                quitterPartie();
            });

            maBox.getChildren().addAll(boxPlateau, parametreJoueur);
        }
    }

    private void passerSonTour() {
        monControleur.passerTour();
        cptAfk=0;
        init();
    }

    private void quitterPartie() {
        monControleur.quitterPartie();
        timeLine.stop();
    }

    private void pivoterPlus() {
        monControleur.pivoterPlus();
        init();
    }

    private void pivoterMinus() {
        monControleur.pivoterMinus();
        init();
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

    public void pousser(int x, int y){
        monControleur.placerCase(x,y);
        init();
    }

    public void deplacerJoueur(int x, int y){
        if(monControleur.deplacerJoueur(x, y)){
            if(monControleur.trouverLeTresor){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bravo !");
                alert.setHeaderText("Vous avez trouvé un trésor");
                alert.setContentText("Bravo, vous venez de trouver un trésor ! Continuez ainsi pour remporter la victoire !");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!!");
            alert.setHeaderText("Déplacement IMPOSSIBLE");
            alert.setContentText("La case choisi n'est pas accessible !");
            alert.showAndWait();
        }
        monControleur.setTrouverLeTresor(false);
        cptAfk=0;
        init();
    }

    public void deconnexion(ActionEvent actionEvent) {
        timeLine.stop();
        monControleur.quitterPartie();
        monControleur.deconnexion();
    }
}
