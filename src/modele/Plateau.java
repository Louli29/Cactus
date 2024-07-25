package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Plateau {

    public static int NB_JOUEUR = 2;
    public static Scanner scanner = new Scanner(System.in);
    JeuCarte pioche;
    List<Joueur> joueurs ;

    public Plateau() {
        this.pioche = new JeuCarte();
        this.pioche.melanger();
        this.joueurs = new ArrayList<>();

        for (int i = 0; i < NB_JOUEUR; i++) {
            String nomJ=demanderJoueur("Quel est ton nom: ");
            joueurs.add(new Joueur(pioche, nomJ));
        }
        initialisation(joueurs);
    }


    private void initialisation(List<Joueur> joueurs) {
        for (Joueur j : joueurs) {
            j.afficherMonJeu();
            for (int i = 0; i < Joueur.NB_CARTE_DEBUT/2 ; i++) {
                System.out.println(choisirCarte(j));
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
}
