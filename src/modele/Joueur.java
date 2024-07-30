package modele;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    public static int NB_CARTE_DEBUT = 4;
    public List<Carte> jeu = new ArrayList<>();
    private final String NOM;


    public Joueur(JeuCarte pioche, String nom) {
        for (int j = 0; j < NB_CARTE_DEBUT; j++) {
            this.jeu.add(pioche.piocher());
        }
        this.NOM = nom;
    }

    public void afficherMonJeu() {
        String carteCaractere = "\uD83C\uDCA0";
        for (int i = 1; i < jeu.size() + 1; i++) {
            System.out.print("  " + carteCaractere + i + "  ");
        }
    }

    public String getNOM() {
        return NOM;
    }

    public void jeterCarte(List<Carte> poubelle, Carte carteSupp) {
        poubelle.add(carteSupp);
        System.out.println(carteSupp + "\n \n \n");
    }


    public Carte montrerCarte(String numCarte) {
        int carteVu = Integer.parseInt(numCarte);
        if (carteVu <= jeu.size() && carteVu >= 0) {
            return jeu.get(carteVu - 1);
        }
        return null;
    }


    private int compterPoint() {
        int nombrePoint = 0;
        for (Carte c : jeu) {
            nombrePoint += c.getValeur();
        }
        return nombrePoint;
    }

}


