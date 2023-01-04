package com.codingf.morpion;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int nbCases = 3;
        Game game = new Game(nbCases);
        Scanner scanner = new Scanner(System.in);
        String rep ;
        String positions[];
        int line =0, col = 0;

        while (true){
            game.display();
            System.out.println(String.format("Joueur %d , entrez la position (ligne, colonne)", game.getPlayTurn()));
            rep = scanner.nextLine();

            if ( rep.equals(""))
                break;

            positions = rep.split(" |,");
            try {
                line = Integer.valueOf(positions[0].trim());
                col = Integer.valueOf(positions[1].trim());
            } catch (Exception e){
                System.err.println("Erreor while Integer conversion :"+e.getLocalizedMessage());
                continue;
            }
            if ( col >= nbCases || line >= nbCases ) {
                System.out.println(String.format("Le nombre de ligne/colonne ne peut être supérieur %d",nbCases-1));
                continue;
            }
            if ( ! game.play(line, col) )
                System.out.println(String.format("La position %d, %d est déjà jouée !",line,col));


            System.out.println(String.format("Line %d, colonne %d",line, col));
        }
        System.out.println("Fin du programme...");
    }
}