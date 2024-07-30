package modele;

import enums.Couleur;

public class Carte {
    private final int valeur;
    Couleur couleur;

    public Carte(int valeur, Couleur couleur){
        this.valeur=valeur;
        this.couleur=couleur;

    }

    public int getValeur(){
        switch (valeur){
            case 11,12: return 10;
            case 13:
                if (couleur==Couleur.TREFLE || couleur==Couleur.PIQUE){
                    return 15;
                }
                else{
                    return 0;
                }
            default: return valeur;
        }
    }

    public boolean getPouvoir(){
        return switch (valeur) {
            case 7, 11, 12 -> true;
            default -> false;
        };
    }

    public String toString(){
        return switch (valeur) {
            case 11 -> " Valet de " + couleur;
            case 12 -> " Reine de " + couleur;
            case 13 -> " Roi de " + couleur;
            default -> valeur + " de " + couleur;
        };

    }
}
