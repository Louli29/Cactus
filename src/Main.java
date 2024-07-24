import modele.JeuCarte;
import modele.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int NB_JOUEUR = 2;

    public static void main(String[] args) {
        JeuCarte pioche = new JeuCarte();
        pioche.melanger();
        List<Joueur> joueurs = new ArrayList<>();

        for (int i = 0; i < NB_JOUEUR; i++) {
            joueurs.add(new Joueur(pioche));
        }
        initialisation(joueurs);

    }

    public static void initialisation(List<Joueur> joueurs) {
        for (Joueur j : joueurs) {

            j.afficherMonJeu();
            for (int i = 0; i < Joueur.NB_CARTE_DEBUT / 2; i++) {
                j.regarderCarteDebut();
            }

        }
    }


}