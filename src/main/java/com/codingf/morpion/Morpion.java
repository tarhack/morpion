package com.codingf.morpion;

import java.util.Scanner;

public class Morpion {

    public static void main(String[] args) {
        int nbCases = 3;
        Game game = new Game(nbCases);
        Scanner scanner = new Scanner(System.in);
        String rep ;
        String positions[];
        int line =0, col = 0;
        String sline="=".repeat(80);
        while (true){
            game.display();
            int currentPlayer = game.getPlayTurn();
            System.out.println(String.format("Joueur %d , entrez la position (ligne, colonne)", currentPlayer));
            rep = scanner.nextLine();

            if ( rep.equals(""))
                break;

            positions = rep.split(" |,");
            try {
                line = Integer.valueOf(positions[0].trim());
                col = Integer.valueOf(positions[1].trim());
            } catch (Exception e){
                System.err.println("Vous devez entrez des coordonnées valides line,col ou line col, err="+e.getLocalizedMessage());
                continue;
            }
            if ( col >= nbCases || line >= nbCases ) {
                System.out.println(String.format("Le nombre de ligne/colonne ne peut être supérieur %d",nbCases-1));
                continue;
            }
            if ( ! game.play(line, col) )
                System.out.println(String.format("La position %d, %d est déjà jouée !",line,col));

            var winner = game.over();

            if ( winner > 0 || winner < 0) {
                if (winner > 0) {
                    System.out.println(sline);
                    System.out.println(String.format("Le joueur %d GAGNE !!!", winner));
                    System.out.println(sline);
                } else if (winner < 0) {
                    System.out.println(sline);
                    System.out.println(String.format("Situation de bloquage, on peut plus jouer !!!"));
                    System.out.println(sline);
                }
                System.out.println("Voulez-vous refaire une partie (o, CR=Sortie) ");
                rep = scanner.nextLine();
                if (rep.toLowerCase().equals("o")) {
                    game.init();
                    continue;
                } else
                    break;
            }

            System.out.println(String.format("Joueur %s joue line %d, colonne %d",currentPlayer,line, col));
        }

        System.out.println("Fin du programme...");
    }
}