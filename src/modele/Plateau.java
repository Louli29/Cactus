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
            System.out.println("Quel est ton nom: ");
            String nomJ = scanner.nextLine();
            joueurs.add(new Joueur(pioche, nomJ));
        }
        initialisation(joueurs);
    }


    public void initialisation(List<Joueur> joueurs) {
        for (Joueur j : joueurs) {
            j.afficherMonJeu();
            for (int i = 0; i < Joueur.NB_CARTE_DEBUT / 2; i++) {

                j.regarderCarteCache();
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
}
