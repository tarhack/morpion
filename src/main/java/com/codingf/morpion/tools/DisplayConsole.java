package com.codingf.morpion.tools;

import com.codingf.morpion.domain.Game;

import java.io.IOException;

/**
 * Cette classe à la responsabilité de l'affichhage formaté de la grille de jeu
 * Elle contient l'algorithme d'affichage et la mise en forme des lignes etc...
 * Elle contient la méthode clear() permettant d'effacer l'écran
 */
public class DisplayConsole {

    public static void display(Game game){
        int count = game.getNbSquare();
        var grid = game.getGrid();
        String tabs = "\t".repeat(8);
        String interligne = interline(count);
        System.out.println("\n\n\n");
        // Display TOP of Grid  ╔════╦════╗
        top(count,tabs);
        System.out.printf("%s",tabs);
        // Display Body of Grid ║ -  ║  - ║
        for(int line=0;line<count;line++){
            var cases = grid.get(line);
            for(int col=0;col<count;col++){
                System.out.printf("║ %s ", cases[col].display());
            }
            if ( line < count-1 )
                System.out.printf("║%n%s%s%n%s",tabs, interligne,tabs);
            else
                System.out.printf("║%n");
        }
        // Bottom Grid          ╚════╩════╝
        bottom(count,tabs);
    }

    private static String interline(int count){
        // Interligne ╠═══╬═══╬═══╣
        return "╠═" + "══╬═".repeat(Math.max(0, count - 1)) +
                "══╣";
    }

    private static void top(int count, String tabs){
        System.out.printf("%s╔═",tabs);
        for(int line=0;line<count-1;line++){
            System.out.print("══╦═");
        }
        System.out.printf("══╗%n");
    }

    private static void bottom(int count, String tabs){
        System.out.printf("%s╚═",tabs);
        for(int line=0;line<count-1;line++){
            System.out.print("══╩═");
        }
        System.out.printf("══╝%n");
    }

    public static void clear() throws IOException, InterruptedException {
        if ( System.getProperty("os.name").contains("Windows") )
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}
