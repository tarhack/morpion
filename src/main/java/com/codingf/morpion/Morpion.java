package com.codingf.morpion;

import com.codingf.morpion.domain.Game;
import com.codingf.morpion.tools.DisplayConsole;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class Morpion {

    public static final String VERSION = "1.4.5";

    private static void help() throws IOException {
        System.out.printf("Morpion (TicTacToe) Version %s%n", VERSION);
        Morpion.banner("banner2.txt");
        System.out.println(" ");
    }

    public static void display(Game game, boolean clear) throws IOException, InterruptedException {
        if (clear)
            DisplayConsole.clear();
        DisplayConsole.display(game);
    }

    public static void banner(String fileName) throws IOException {
        ClassLoader classLoader = Morpion.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        Objects.requireNonNull(inputStream).transferTo(System.out);
        inputStream.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int nbCases = 3;
        if (args.length > 0) {
            for (int idx = 0; idx < args.length; idx++) {
                var p = args[idx];
                if (p.startsWith("--number")) {
                    nbCases = Integer.parseInt(args[idx + 1]);
                }
            }
        }

        Game game = new Game(nbCases);
        Scanner scanner = new Scanner(System.in);
        String rep;
        String screenLine = "=".repeat(120);


        DisplayConsole.clear();
        help();
        boolean clear = false;

        while (true) {
            display(game, clear);

            if (!clear)
                clear = true;

            int currentPlayer = game.getPlayTurn();
            System.out.printf("Joueur %d , entrez le numéro de case à jouer %n", currentPlayer);
            rep = scanner.nextLine();

            if (rep.isEmpty())
                break;

            int numCase;
            try {
                numCase = Integer.parseInt(rep);
            } catch (Exception e) {
                System.err.println("Vous devez entrez un numéro de case valide, err=" + e.getLocalizedMessage());
                clear=false;
                continue;
            }

            if ((numCase > (nbCases * nbCases) - 1) || numCase < 0) {
                System.out.printf("Le numéro de case est incorrect, il doit être compris entre %d et %d %n", 0, (nbCases * nbCases) - 1);
                clear=false;
                continue;
            }

            if (!game.play(numCase)) {
                System.out.printf("La position %d est déjà jouée !%n", numCase);
                clear=false;
            }

            var winner = game.over();

            if (winner != 0) {
                System.out.println(screenLine);
                if (winner > 0) {
                    System.out.printf("Le joueur %d GAGNE !!!%n", winner);
                    display(game, true);
                    Morpion.banner(String.format("joueur_%d.txt", winner));
                    System.out.println("\n" + screenLine);
                } else {
                    System.out.println("Situation de blocage, on peut plus jouer !!!");
                    System.out.println(screenLine);
                }

                System.out.println("Voulez-vous refaire une partie (o, CR=Sortie) ");
                rep = scanner.nextLine();
                if (rep.equalsIgnoreCase("o")) {
                    game.init();
                    continue;
                } else
                    break;
            }

            System.out.printf("Joueur %s joue case %d %n", currentPlayer, numCase);
        }

        System.out.println("Fin du programme...");
    }
}