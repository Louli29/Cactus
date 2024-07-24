package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Joueur {
    public static int NB_CARTE_DEBUT = 4;
    public List<Carte> jeu = new ArrayList<>();
    int nombrePoint;

    public Joueur( JeuCarte pioche){
        for (int j=0 ; j<NB_CARTE_DEBUT ; j++){
            jeu.add(pioche.piocher());
        }

        for(Carte c:jeu){
            nombrePoint+=c.valeur;
        }
    }

    public void afficherMonJeu(){
        String carteCaractere = "\uD83C\uDCA0";
        for (int i=1; i<jeu.size()+1; i++){
            System.out.print(carteCaractere + i +"  ");
        }
    }

    public Scanner scanner = new Scanner(System.in);
    public void regarderCarteDebut(){
        System.out.println("Entre 1,2,3 et 4 pour regarder une carte : ");
        String input = scanner.nextLine();
        int carteVu = Integer.parseInt(input);
        System.out.println("La carte "+ carteVu+ " est "+jeu.get(carteVu)+"\n");
    }

}


