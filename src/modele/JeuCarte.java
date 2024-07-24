package modele;

import enums.Couleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void melanger(){
        Collections.shuffle(pioche);
    }

    public Carte piocher() {
        Carte cartePioche = pioche.get(pioche.size()-1);
        pioche.remove(pioche.size()-1);
        return cartePioche;
    }

}
