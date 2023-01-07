package com.codingf.morpion;

import com.codingf.morpion.modeles.Square;
import com.codingf.morpion.tools.Clear;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class Morpion {

    private static final String VERSION = "1.2.0" ;
    private static void help() throws IOException {
        System.out.printf("Moripon Version %s%n",VERSION);
        Morpion.banner("banner.txt");
        System.out.println(" ");
        System.out.println("Amusez vous : \uD83D\uDE0D");
        System.out.println("╔════╦════╗");
        System.out.println("║ -  ║  - ║");
        System.out.println("╠════╬════╣");
        System.out.println("║ °  ║ °  ║");
        System.out.println("╚════╩════╝");
    }

    public static void display(Game game,boolean clear) throws IOException, InterruptedException {
        if ( clear )
            Clear.clear();
        var grid = game.getGrid();
        var iter =grid.entrySet().stream().iterator();
        System.out.print("  ");
        for(int idx=0;idx < grid.size();idx++)
            System.out.print(idx+" ");
        System.out.println(" ");
        while (iter.hasNext()) {
            var square = iter.next();
            System.out.print(square.getKey() );
            var line = square.getValue();
            for (Square value : line) {
                System.out.print(" " + value.display());
            }
            System.out.println(" ");
        }
    }
    public static void banner(String fileName) throws IOException {
        ClassLoader classLoader = Morpion.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        Objects.requireNonNull(inputStream).transferTo(System.out);
        inputStream.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int nbCases = 3;
        help();
        System.out.printf("Nbre cases : %d%n",args.length);
        if ( args.length > 0 ){
            for ( int idx=0;idx< args.length;idx++){
                var p = args[idx];
                if ( p.startsWith("--number")){
                    nbCases = Integer.parseInt(args[idx+1]);
                }
            }
        }

        Game game = new Game(nbCases);
        Scanner scanner = new Scanner(System.in);
        String rep ;
        String[] positions;
        int line, col ;
        String sline="=".repeat(120);

        boolean clear=false;

        while (true){
            display(game, clear);

            if(!clear)
                clear=true;

            int currentPlayer = game.getPlayTurn();
            System.out.printf("Joueur %d , entrez la position (ligne, colonne)%n", currentPlayer);
            rep = scanner.nextLine();

            if ( rep.equals(""))
                break;

            positions = rep.split("[ ,]");
            try {
                line = Integer.parseInt(positions[0].trim());
                col = Integer.parseInt(positions[1].trim());
            } catch (Exception e){
                System.err.println("Vous devez entrez des coordonnées valides line,col ou line col, err="+e.getLocalizedMessage());
                continue;
            }
            if ( col >= nbCases || line >= nbCases ) {
                System.out.printf("Le nombre de ligne/colonne ne peut être supérieur %d%n",nbCases-1);
                continue;
            }
            if ( ! game.play(line, col) )
                System.out.printf("La position %d, %d est déjà jouée !%n",line,col);

            var winner = game.over();

            if ( winner != 0) {
                System.out.println(sline);
                if (winner > 0) {
                    System.out.printf("Le joueur %d GAGNE !!!%n", winner);
                    display(game,true);
                    Morpion.banner(String.format("joueur_%d.txt",winner));
                    System.out.println("\n"+sline);
                } else {
                    System.out.println("Situation de bloquage, on peut plus jouer !!!");
                    System.out.println(sline);
                }

                System.out.println("Voulez-vous refaire une partie (o, CR=Sortie) ");
                rep = scanner.nextLine();
                if (rep.equalsIgnoreCase("o") ){
                    game.init();
                    continue;
                } else
                    break;
            }

            System.out.printf("Joueur %s joue line %d, colonne %d%n",currentPlayer,line, col);
        }

        System.out.println("Fin du programme...");
    }
}