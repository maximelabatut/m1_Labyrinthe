//package modele;
//
//import modele.GestionLabyrinthe;
//import modele.GestionLabyrintheInterface;
//import modele.Joueur;
//import modele.Partie;
//import modele.exceptions.ExceptionDeplacementImpossible;
//import modele.exceptions.ExceptionLoginDejaPris;
//import modele.exceptions.ExceptionPartiePrivee;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class GestionLabyrintheTest {
//
//    GestionLabyrintheInterface gestionLabyrinthe;
//
//    /*
//     * Le before permet d'initialiser la nouvelle facade avant chaque Test
//     * Réinitialisation de la façade a chaque test
//     */
//    @Before
//    public void intialisationFacade(){
//        gestionLabyrinthe = new GestionLabyrinthe();
//    }
//
//
//    /*
//     * Test de la connexion lorsque le pseudo est unique --> Test Connexion OK
//     */
//    @Test
//    public void testConnexionOK() throws ExceptionLoginDejaPris {
//
//        //Execution de la méthode a tester
//        String nomDeCompte = "projetIHM";
//        gestionLabyrinthe.connexion(nomDeCompte);
//
//        //Definition du verdict
//        List<String> connectes = gestionLabyrinthe.getListeJoueursConnectes();
//        Assert.assertTrue("La connexion a été effectué", connectes.contains("projetIHM"));
//    }
//    /*
//     * Test de l'exception ExceptionLoginDejaPris --> Test Connexion KO
//     */
//    @Test(expected = ExceptionLoginDejaPris.class)
//    public void testConnexionKO() throws ExceptionLoginDejaPris{
//
//        //Preparation du scénraio
//        gestionLabyrinthe.connexion("projetIHM");
//
//        //Execution de la méthode a tester
//        String nomDeCompte = "projetIHM";
//        gestionLabyrinthe.connexion(nomDeCompte);
//    }
//    /*
//     * Test de la déconnexion avec pseudo en paramètre
//     */
//    @Test
//    public void testDeconnexion() throws ExceptionLoginDejaPris{
//        //Preparation du scénraio
//        gestionLabyrinthe.connexion("projetIHM");
//
//        //Execution de la méthode
//        gestionLabyrinthe.deconnexion("projetIHM");
//
//        //Definition du verdict
//        List<String> connectes = gestionLabyrinthe.getListeJoueursConnectes();
//        Assert.assertTrue("La déconnexion a échoué", !connectes.contains("projetIHM"));
//    }
//
//
//
//    /*
//     * Test si un joueur peut rejoindre une partie
//     */
//    @Test
//    public void testRejoindrePartieOK() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM",2,true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        int idPartie = laPartie.getId();
//
//        //Execution de la méthode
//        gestionLabyrinthe.rejoindrePartie("projetIHM","rejoinsProjetIHM");
//
//        //Definition du verdict
//        Joueur[] lesJoueurs = gestionLabyrinthe.getPartie("projetIHM").getMesJoueurs();
//
//        List<Joueur> listeJoueurs = new ArrayList<Joueur>(Arrays.asList(lesJoueurs));
//
//        List<String> listeDesJoueursDansLaPartie = new ArrayList<>();
//
//        for(int i=0; i<listeJoueurs.size(); i++){
//            listeDesJoueursDansLaPartie.add(listeJoueurs.get(i).getPseudo());
//        }
//
//        Assert.assertTrue("Le joueur n'est pas dans la partie", listeDesJoueursDansLaPartie.contains("rejoinsProjetIHM"));
//    }
//    /*
//     * Test si un joueur peut rejoindre une partie non existante
//     */
//    @Test
//    public void testRejoindrePartieKO() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 3, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins2ProjetIHM");
//
//        //Execution de la méthode
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins3ProjetIHM");
//
//        //Definition du verdict
//        Joueur[] lesJoueurs = gestionLabyrinthe.getPartie("projetIHM").getMesJoueurs();
//
//        List<Joueur> listeJoueurs = new ArrayList<Joueur>(Arrays.asList(lesJoueurs));
//
//        List<String> listeDesJoueursDansLaPartie = new ArrayList<>();
//
//        for(int i=0; i<listeJoueurs.size(); i++){
//            listeDesJoueursDansLaPartie.add(listeJoueurs.get(i).getPseudo());
//        }
//
//        Assert.assertTrue("La partie est complète, le joueur n'y est donc pas", !listeDesJoueursDansLaPartie.contains("rejoins3ProjetIHM"));
//    }
//
//
//
//
//    /*
//     * Test de la méthode pousser case sous toutes ces formes
//     */
//    @Test
//    public void placerCaseHaut() throws ExceptionDeplacementImpossible, ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 3, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins2ProjetIHM");
//
//        Case caseLibre = gestionLabyrinthe.getPartie("projetIHM").monPlateau.getCaseLibre();
//
//        //la méthode
//        gestionLabyrinthe.placerCase("projetIHM", 1,2);
//
//        Assert.assertTrue("La case n'a pas été poussé", caseLibre.getX()==1 && caseLibre.getY()==2);
//    }
//
//    @Test
//    public void placerCaseBas() throws ExceptionDeplacementImpossible, ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 3, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins2ProjetIHM");
//
//        Case caseLibre = gestionLabyrinthe.getPartie("projetIHM").monPlateau.getCaseLibre();
//
//        //la méthode
//        gestionLabyrinthe.placerCase("projetIHM", 7,2);
//
//        Assert.assertTrue("La case n'a pas été poussé", caseLibre.getX()==7 && caseLibre.getY()==2);
//    }
//
//    @Test
//    public void placerCaseGauche() throws ExceptionDeplacementImpossible, ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 3, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins2ProjetIHM");
//
//        Case caseLibre = gestionLabyrinthe.getPartie("projetIHM").monPlateau.getCaseLibre();
//
//        //la méthode
//        gestionLabyrinthe.placerCase("projetIHM", 2,1);
//
//        Assert.assertTrue("La case n'a pas été poussé", caseLibre.getX()==2 && caseLibre.getY()==1);
//    }
//
//    @Test
//    public void placerCaseDroite() throws ExceptionDeplacementImpossible, ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 3, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins2ProjetIHM");
//
//        Case caseLibre = gestionLabyrinthe.getPartie("projetIHM").monPlateau.getCaseLibre();
//
//        //la méthode
//        gestionLabyrinthe.placerCase("projetIHM", 2,7);
//
//        Assert.assertTrue("La case n'a pas été poussé", caseLibre.getX()==2 && caseLibre.getY()==7);
//    }
//
//    @Test(expected = ExceptionDeplacementImpossible.class)
//    public void placerCaseKO() throws ExceptionDeplacementImpossible, ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 3, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins2ProjetIHM");
//
//        Case caseLibre = gestionLabyrinthe.getPartie("projetIHM").monPlateau.getCaseLibre();
//
//        //la méthode
//        gestionLabyrinthe.placerCase("projetIHM", 3,7);
//
//        Assert.assertTrue("La case n'a pas été poussé", caseLibre.getX()==3 && caseLibre.getY()==7);
//    }
//
//
//
//
//
//    /*
//     * Test quitter partie ok et ko
//     */
//    @Test
//    public void quitterPartieOK() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 3, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins2ProjetIHM");
//
//        int laPartieFini = gestionLabyrinthe.getPartie("projetIHM").getId();
//
//        //la méthode
//        gestionLabyrinthe.quitterPartie("projetIHM", "rejoins1ProjetIHM");
//
//        if(gestionLabyrinthe.getPartie("projetIHM") == null){
//            laPartieFini = -1;
//        }
//
//        Assert.assertTrue("La partie est terminée", laPartieFini==-1);
//    }
//
//    @Test
//    public void quitterPartieKO() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        gestionLabyrinthe.connexion("projet2IHM");
//        gestionLabyrinthe.creerPartie("projet2IHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//        gestionLabyrinthe.rejoindrePartie("projet2IHM", "rejoins2ProjetIHM");
//
//        int laPartieFini = gestionLabyrinthe.getPartie("projetIHM").getId();
//
//        //la méthode
//        gestionLabyrinthe.quitterPartie("projet2IHM", "rejoins2ProjetIHM");
//
//        if(gestionLabyrinthe.getPartie("projetIHM") == null){
//            laPartieFini = -1;
//        }
//
//        Assert.assertTrue("La partie n'est pas terminée", laPartieFini!=-1);
//    }
//
//
//
//
//
//
//    /*
//     * Test de pivoter Plus pour le baton ║ ═
//     */
//    @Test
//    public void pivoterPlusBatonVertical() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true,false,true,false), null);
//        Case caseTest = new Case(0,0,new CaseForme(false, true, false, true),null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterPlusBatonHorizontal() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(false, true, false, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,false,true,false), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//
//    /*
//     * Test de pivoter Plus pour le virage ╔ ╗ ╚ ╝
//     */
//    @Test
//    public void pivoterPlusVirageDroiteBas() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(false, true, true, false),null);
//        Case caseTest = new Case(0,0,new CaseForme(false,false,true,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterPlusVirageGaucheBas() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(false, false, true, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,false,false,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterPlusVirageHautGauche() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true, false, false, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,true,false,false), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterPlusVirageHautDroite() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true, true, false, false),null);
//        Case caseTest = new Case(0,0,new CaseForme(false,true,true,false), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//
//    /*
//     * Test de pivoter Plus pour le virage en T ╩ ╠ ╦ ╣
//     */
//    @Test
//    public void pivoterPlusTGaucheHautDroite() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true, true, false, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,true,true,false), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterPlusTHautDroiteBas() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true, true, true, false),null);
//        Case caseTest = new Case(0,0,new CaseForme(false,true,true,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterPlusTGaucheBasDroite() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(false, true, true, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,false,true,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterPlusTBasGaucheHaut() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true, false, true, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,true,false,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//
//
//
//
//    /*
//     * Test de pivoter Minus pour le baton ║ ═
//     */
//    @Test
//    public void pivoterMinusBatonVertical() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true,false,true,false), null);
//        Case caseTest = new Case(0,0,new CaseForme(false, true, false, true),null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterMinusBatonHorizontal() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(false, true, false, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,false,true,false), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//
//
//    /*
//     * Test de pivoter Minus pour les virages en T ╩ ╠ ╦ ╣
//     */
//    @Test
//    public void pivoterMinusTGaucheHautDroite() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true, true, false, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,false,true,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterMinus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterMinusTHautDroiteBas() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true, true, true, false),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,true,false,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterMinus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterMinusTGaucheBasDroite() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(false, true, true, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,true,true,false), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterMinus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterMinusTBasGaucheHaut() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true, false, true, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(false,true,true,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterMinus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//
//
//    /*
//     * Test de pivoter Minus pour le Virage ╔ ╗ ╚ ╝
//     */
//    @Test
//    public void pivoterMinusVirageDroiteBas() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(false, true, true, false),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,true,false,false), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterMinus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterMinusVirageGaucheBas() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(false, false, true, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,false,false,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterPlus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterMinusVirageHautGauche() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true, false, false, true),null);
//        Case caseTest = new Case(0,0,new CaseForme(false,false,true,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterMinus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//    @Test
//    public void pivoterMinusVirageHautDroite() throws ExceptionLoginDejaPris, ExceptionPartiePrivee {
//        //Preparation du scénario
//        gestionLabyrinthe.connexion("projetIHM");
//        gestionLabyrinthe.creerPartie("projetIHM", 2, true);
//
//        Partie laPartie = gestionLabyrinthe.getPartie("projetIHM");
//        laPartie.initialiserPartie();
//
//        gestionLabyrinthe.rejoindrePartie("projetIHM", "rejoins1ProjetIHM");
//
//        Case caseLibreTest = new Case(0,0,new CaseForme(true, true, false, false),null);
//        Case caseTest = new Case(0,0,new CaseForme(true,false,false,true), null);
//
//        gestionLabyrinthe.getPartie("projetIHM").monPlateau.setCaseLibre(caseLibreTest);
//
//        gestionLabyrinthe.pivoterMinus90("projetIHM");
//
//        Assert.assertTrue("La case n'a pas été pivoté", caseLibreTest.getForme().equals(caseTest.getForme()));
//    }
//
//}
