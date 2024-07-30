package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Plateau {

    public static int NB_JOUEUR = 2;
    public static Scanner scanner = new Scanner(System.in);
    JeuCarte pioche;
    List<Joueur> joueurs;
    List<Carte> poubelles;


    public Plateau() {
        this.pioche = new JeuCarte();
        this.pioche.melanger();
        this.joueurs = new ArrayList<>();
        this.poubelles = new ArrayList<>();

        for (int i = 0; i < NB_JOUEUR; i++) {
            String nomJ = demanderJoueur("Quel est ton nom: ");
            joueurs.add(new Joueur(pioche, nomJ));
        }
        initialisation(joueurs);
        jouer(joueurs, poubelles);
    }


    private void initialisation(List<Joueur> joueurs) {
        for (Joueur j : joueurs) {
            j.afficherMonJeu();
            for (int i = 0; i < Joueur.NB_CARTE_DEBUT / 2; i++) {
                Carte carte = choisirCarte(j);
                System.out.println("\n Cette carte est : " + carte + "\n");
            }
        }
    }

    private void jouer(List<Joueur> joueurs, List<Carte> poubelle) {
        for (Joueur j : joueurs) {
            System.out.println(" C'est au tour de " + j.getNOM() + "\n");
            Carte carteMain = pioche.piocher();
            System.out.println("Ta carte pioché est : " + carteMain + "\n");
            choix(j, carteMain, poubelle);

        }
    }

    private void choix(Joueur j, Carte carteMain, List<Carte> poubelle) {
        boolean verif = false;
        while (!verif ) {
            String reponse = demanderJoueur("Que veux tu faire : \n  Jeter ta carte (tape 0) ou l'enchanger( tape 1)");
            int choix = Integer.parseInt(reponse);
            if (choix == 0) {
                j.jeterCarte(poubelle, carteMain);
                verif = true;
            } else if (choix == 1) {
                echanger(j, carteMain);
                verif = true;
            }
        }
    }

    private Joueur trouverJoueur(String nom) {
        for (Joueur j : joueurs) {
            if (j.getNOM().equals(nom)) {
                return j;
            }
        }
        return null;
    }

    private Carte choisirCarte(Joueur j) {
        String numCarte = demanderJoueur(j.getNOM() + "\n Quelle carte veux-tu voir ? ");
        while (j.montrerCarte(numCarte) == null) {
            System.out.println(" Erreur cette carte n'existe pas \n ");
            numCarte = demanderJoueur(j.getNOM() + " Quelle carte veux-tu voir ? ");
        }
        return j.montrerCarte(numCarte);
    }

    public void echanger(Joueur j, Carte carteMain) {
        String numCarte = demanderJoueur(" Quelle carte veux-tu échanger ? ");
        int indexCarte;
        try {
             indexCarte = Integer.parseInt(numCarte);
        }
        catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un numéro de carte valide.");
            return;
        }
        if (indexCarte < 1 || indexCarte > j.jeu.size()) {
            System.out.println("Numéro de carte invalide. Veuillez entrer un numéro compris entre 1 et " + j.jeu.size());
            return;
        }
        Carte carteJeter = j.jeu.get(indexCarte - 1);
        j.jeu.set(indexCarte - 1, carteMain);
        j.jeterCarte(poubelles, carteJeter);
    }

    public String demanderJoueur(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }



}
