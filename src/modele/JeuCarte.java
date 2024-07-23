package modele;

import enums.Couleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JeuCarte {
    List<Carte> pioche;
    public static int NB_CARTE_COULEUR=14;

    public JeuCarte() {
        pioche = new ArrayList<>();
        for (int i = 1; i < NB_CARTE_COULEUR; i++) {
            for (Couleur couleur : Couleur.values()) {
                pioche.add(new Carte(i, couleur));
            }
        }
    }

    public Carte piocher() {
        Random random = new Random();
        Carte carte = pioche.get(random.nextInt(pioche.size()));
        pioche.remove(carte);
        return carte;
    }
}
