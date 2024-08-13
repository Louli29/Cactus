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
                Carte carte = choisirCarte(j,j);
                System.out.println("\n Cette carte est : " + carte + "\n");
            }
        }
    }

    private void jouer(List<Joueur> joueurs, List<Carte> poubelle) {
        Boolean cactus=false;
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
                System.out.println(choisirCarte(j,j) );
            case 11: pouvoirValet(j) ;
            //case 12: pouvoirDame();
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
        getPouvoir(j,carteJeter);

    }

    public String demanderJoueur(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    private void pouvoirValet(Joueur j){
        System.out.println("Le pouvoir du valet est activé");
        String adversaire = demanderJoueur("Tu veux voir la carte de quel joueur ? ");
        Joueur joueurAdverse = trouverJoueur(adversaire);
        while (joueurAdverse == null) {
            System.out.println("erreur ce joueur n'existe pas \n ");
            String nvAdversaire = demanderJoueur("Tu veux voir la carte de quel joueur ? ");
            joueurAdverse = trouverJoueur(nvAdversaire);
        }
        System.out.println(choisirCarte(j,joueurAdverse));

    }

    /* private void pouvoirDame (){
        String AdversaireA = demanderJoueur("Quel est le premier joueur dont tu veux échanger la carte ? ");
        Joueur joueurA = trouverJoueur(AdversaireA);
        while (joueurA == null) {
            System.out.println("erreur ce joueur n'existe pas \n ");
            String nvAdversaire = demanderJoueur("Quel est le premier joueur dont tu veux échanger la carte ? ");
            joueurA = trouverJoueur(nvAdversaire);
        }
        int indexA = 0;
        while (indexA > joueurA.jeu.size() || indexA <= 0) {
            System.out.println("Erreur cette carte n'existe pas \n ");
            String cA= demanderJoueur("Quel carte de son jeu veux tu échanger");
            indexA = Integer.parseInt(cA);
        }
        Carte carteA = joueurA.jeu.get(indexA);

        String AdversaireB = demanderJoueur("Quel est le premier joueur dont tu veux échanger la carte ? ");
        Joueur joueurB = trouverJoueur(AdversaireB);
        while (joueurB == null) {
            System.out.println("erreur ce joueur n'existe pas \n ");
            String Adversaire = demanderJoueur("Quel est le deuxième joueur dont tu veux échanger la carte ? ");
            joueurB = trouverJoueur(Adversaire);
        }
        int indexB = 0;
        while (indexB > joueurB.jeu.size() || indexB <= 0) {
            System.out.println("Erreur cette carte n'existe pas \n ");
            String cB= demanderJoueur("Quel carte de son jeu veux tu échanger");
            indexB = Integer.parseInt(cB);
        }
        Carte carteB = joueurB.jeu.get(indexA);

        joueurA.jeu.set(indexA, carteB);
        joueurB.jeu.set(indexB, carteA);

    }*/



    }




