package modele;

import enums.Couleur;

public class Carte {
    int valeur;
    Couleur couleur;

    public Carte(int valeur, Couleur couleur){
        this.valeur=valeur;
        this.couleur=couleur;
    }

    public String toString(){
        return valeur + " de " +couleur;
    }
}
