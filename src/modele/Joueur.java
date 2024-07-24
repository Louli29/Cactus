package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Joueur {
    public static int NB_CARTE_DEBUT = 4;
    public List<Carte> jeu = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);

    private int nombrePoint;//todo changer ça en fonction
    public String nom;//todo amelioration


    public Joueur( JeuCarte pioche, String nom){
        for (int j=0 ; j<NB_CARTE_DEBUT ; j++){
            this.jeu.add(pioche.piocher());
        }

        for(Carte c:jeu){
            nombrePoint+=c.valeur;
        }
        this.nom = nom;
    }

    public void afficherMonJeu(){
        String carteCaractere = "\uD83C\uDCA0";
        for (int i=1; i<jeu.size()+1; i++){
            System.out.print(carteCaractere + i +"  ");
        }
    }



    public void regarderCarteCache(){
        System.out.println(nom+ "  Entre 1,2,3 et 4 pour regarder une carte : ");//todo modification car pas toujours 4 cartes
        String input = scanner.nextLine();
        int carteVu = Integer.parseInt(input);
        System.out.println("La carte "+ carteVu+ " est "+jeu.get(carteVu)+"\n");
    }

    public void jeter(List<Carte> poubelle,Carte carteJete){
        poubelle.add(carteJete);
        System.out.println(carteJete);
    }

}


