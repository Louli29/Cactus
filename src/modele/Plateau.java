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
            for (int i = 0; i < Joueur.NB_CARTE_DEBUT / 2; i++) {
                j.afficherMonJeu();
                Carte carte = choisirCarte(j,j);
                System.out.println("\n Cette carte est : " + carte + "\n");
            }
        }
    }

    private void jouer(List<Joueur> joueurs, List<Carte> poubelle) {
        boolean cactus=false;
        while (!cactus) {
            for (Joueur j : joueurs) {
                System.out.println(" C'est au tour de " + j.getNOM() + "\n");
                Carte carteMain = pioche.piocher();
                System.out.println("Ta carte pioché est : " + carteMain + "\n");
                choix(j, carteMain, poubelle);
            }
        }

    }

    private void choix(Joueur j, Carte carteMain, List<Carte> poubelle) {
        boolean verif = false;
        while (!verif ) {
            String reponse = demanderJoueur("Que veux tu faire : \n  Jeter ta carte (tape 0) ou l'enchanger( tape 1)");
            int choix = Integer.parseInt(reponse);
            if (choix == 0) {
                j.jeterCarte(poubelle, carteMain);
                getPouvoir(j,carteMain);
                verif = true;
            } else if (choix == 1) {
                echanger(j, carteMain);
                verif = true;
            }
        }
    }

    public void getPouvoir(Joueur j,Carte carteMain){
        switch(carteMain.getValeur()){
            case 7:
                System.out.println(choisirCarte(j,j));
                break;
            case 11: pouvoirValet(j);
                break;
            case 12: pouvoirDame();
                break;
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

    private Carte choisirCarte(Joueur j, Joueur jadv) {
        String numCarte = demanderJoueur("\n "+j.getNOM() + "\n Quelle carte veux-tu voir ? ");
        while (jadv.montrerCarte(numCarte) == null) {
            System.out.println(" Erreur cette carte n'existe pas \n ");
            numCarte = demanderJoueur("\n "+j.getNOM() + " Quelle carte veux-tu voir ? ");
        }
        return jadv.montrerCarte(numCarte);
    }

    public void echanger(Joueur j, Carte carteMain) {
        int indexCarte=trouverEtVerificationCarte(j);
        Carte carteJeter = j.jeu.get(indexCarte );
        j.jeu.set(indexCarte , carteMain);
        j.jeterCarte(poubelles, carteJeter);
        getPouvoir(j,carteJeter);
    }

    public String demanderJoueur(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    private void pouvoirValet(Joueur j){
        Joueur joueurAdverse=verificationJoueur("Tu veux voir la carte de quel joueur ? ");
        System.out.println(choisirCarte(j,joueurAdverse));

    }

    private Joueur verificationJoueur(String question) {
        while (true) {
            String adversaire = demanderJoueur(question);
            Joueur joueurAdverse = trouverJoueur(adversaire);

            if (joueurAdverse != null) {
                return joueurAdverse;
            } else {
                System.out.println("Joueur introuvable. Veuillez entrer un nom valide.");
            }
        }
    }

    private int trouverEtVerificationCarte( Joueur j) {
        String cA = demanderJoueur(" Quelle carte veux-tu échanger ? ");
        int index;
        try {
            index = Integer.parseInt(cA);
            if (index < 1 || index >= j.jeu.size()+1) {
                System.out.println("Numéro de carte invalide. Veuillez entrer un numéro compris entre 1 et " + j.jeu.size());
            } else {
                return index-1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un numéro de carte valide.");
        }
        return trouverEtVerificationCarte(j);
    }


    private void pouvoirDame (){
        Joueur joueurA =verificationJoueur(" Entre le nom du premier joueur dont tu veux échanger la carte ");
        int indexA =trouverEtVerificationCarte(joueurA);
        Carte carteA = joueurA.jeu.get(indexA);

        Joueur joueurB = verificationJoueur(" Entre le nom du deuxième joueur dont tu veux échanger la carte ");
        int indexB =trouverEtVerificationCarte(joueurB);
        Carte carteB = joueurB.jeu.get(indexA);

        joueurA.jeu.set(indexA, carteB);
        joueurB.jeu.set(indexB, carteA);
    }



    }




