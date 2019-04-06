package application;

import controleur.Controleur;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modele.Partie;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        Controleur monControleur = new Controleur(primaryStage);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if(monControleur.getPseudoConnecte()!=null) {
                    Partie laPartie = monControleur.getPartieByPseudo(monControleur.getPseudoConnecte());
                    if(laPartie!=null) {
                        if(monControleur.getPseudoConnecte().equals(laPartie.getJoueurHost().getPseudo())){
                            monControleur.annulePartie();
                        }else{
                            monControleur.quitterPartie();
                        }
                    }
                    monControleur.deconnexion();
                }
            }
        });
    }
}
