package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Plateau {

    public static int NB_JOUEUR = 2;
    public static Scanner scanner = new Scanner(System.in);
    JeuCarte pioche;
    List<Joueur> joueurs ;
    List<Carte> poubelles ;

    /*public static final String ANSI_CLEAR_LINE = "\u001B[2K";
    public static final String ANSI_CURSOR_HOME = "\u001B[0G";
    public static final String ANSI_CURSOR_UP = "\u001B[%dA";

     */

    public Plateau() {
        this.pioche = new JeuCarte();
        this.pioche.melanger();
        this.joueurs = new ArrayList<>();
        this.poubelles = new ArrayList<>();

        for (int i = 0; i < NB_JOUEUR; i++) {
            String nomJ=demanderJoueur("Quel est ton nom: ");
            joueurs.add(new Joueur(pioche, nomJ));
        }
        initialisation(joueurs);
        jouer(joueurs,poubelles);
    }


    private void initialisation(List<Joueur> joueurs) {
        for (Joueur j : joueurs) {
            j.afficherMonJeu();
            for (int i = 0; i < Joueur.NB_CARTE_DEBUT/2 ; i++) {
                System.out.println(choisirCarte(j));
            }

        }
    }

    private void jouer(List<Joueur> joueurs,List<Carte> poubelle){
        for (Joueur j : joueurs){
            System.out.println(" C'est au tour de "+ j.nom+"\n");
            Carte carteMain=pioche.piocher();
            System.out.println("Ta carte pioché est : "+carteMain+"\n");
            choix(j,carteMain,poubelle);

        }
    }

    private void choix(Joueur j,Carte carteMain,List<Carte> poubelle){
        int verif=0;
        while (verif == 0) {
            String reponse = demanderJoueur("Que veux tu faire : " + "\n" + "jeter ta carte (tape 0) ou l'enchanger( tape 1)"+"\n");
            int choix = Integer.parseInt(reponse);
            if (choix == 0) {
                j.jeter(poubelle, carteMain);
                verif = 1;
            } else if (choix == 1) {
                System.out.println("tu as voulu changer");
                verif = 1;
            }
        }
    }

    private Joueur trouverJoueur(String nom){
        for (Joueur j : joueurs) {
            if (j.nom.equals(nom)){
                return j;
            }
        }
        return null;

    }

    public String demanderJoueur(String question){
        System.out.println(question);
        return scanner.nextLine();
    }

    private String choisirCarte(Joueur j){
        String numCarte=demanderJoueur(j.nom+" Quelle carte veux-tu voir ? ");
        return j.montrerCarte(numCarte);
    }

 /*   private void cacherLignes(int nombreLigne){
        //Thread.sleep(5000);
        for (int i = 0; i < nombreLigne; i++) {
            System.out.print(ANSI_CLEAR_LINE + ANSI_CURSOR_HOME);
            if (i < nombreLigne - 1) {
                // Déplacer le curseur vers le haut sauf pour la dernière ligne
                System.out.print(String.format(ANSI_CURSOR_UP, 1));
            }
        }
    }*/
}
