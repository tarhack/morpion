package com.codingf.morpion;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class GameTest {

    @org.junit.jupiter.api.Test
    void play() throws IOException,InterruptedException {
        Game game = new Game(3);
        System.out.println("<<<Start tests for PLAY Method>>>");
        // Pass I
        game.play(0,0); // X
        game.play(0,1); // X O
        game.play(0,2); // X O X
        Morpion.display(game,false);
        System.out.printf("Winner %d, Next Turn : %d%n",game.over(), game.getPlayTurn());
        // Pass II
        // X O X
        // O O X
        game.play(1,0);
        game.play(1,2);
        game.play(1,1);
        Morpion.display(game,false);
        System.out.printf("Winner %d, Next Turn : %d%n",game.over(), game.getPlayTurn());
        // Pass III
        // X O X
        // O O X
        // O O X
        game.play(2,0);
        game.play(2,2);
        game.play(2,1);
        Morpion.display(game,false);
        System.out.printf("Winner %d, Next Turn : %d%n",game.over(), game.getPlayTurn());
        assertTrue(game.over()<0, "ERRUR Le jeu est en situation de blocage le retour de game.over() doit être négatif");
        System.out.println("<<<End tests for PLAY Method>>>");
    }
    @org.junit.jupiter.api.Test
    void clear() throws IOException, InterruptedException {
        System.out.println("-------------------------------------------");
        // Morpion.clear();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    @org.junit.jupiter.api.Test
    void banner() throws IOException {
        Morpion.banner("banner1.txt");
        int joueur = 1;
        Morpion.banner(String.format("joueur_%d.txt", joueur));
        joueur = 2;
        Morpion.banner(String.format("joueur_%d.txt", joueur));
    }
    @org.junit.jupiter.api.Test
    void grid(){
        String[] players = {"O","X","O","X","O"} ;
        String dline = "=".repeat(80);
        System.out.println(dline);
        System.out.println("╔════╦════╗");
        System.out.println("║ -  ║  - ║");
        System.out.println("╠════╬════╣");
        System.out.println("║ °  ║ °  ║");
        System.out.println("╚════╩════╝");
        System.out.println(dline);
        // Top Grid
        int count = 4;
        String tabs = "\t".repeat(8);
        String interligne = interline(count);

        top(count,tabs);
        System.out.printf("%s",tabs);
        for(int line=0;line<count;line++){
            for(int col=0;col<count;col++){
                System.out.printf("║ %s ", players[col]);
            }
            if ( line < count-1 )
                System.out.printf("║%n%s%s%n%s",tabs, interligne,tabs);
            else
                System.out.printf("║%n");
        }

        // Bottom Grid
        bottom(count,tabs);
    }

    private String interline(int count){
        String interline = "╠════╬════╣" ;
        String line = "╠═";
        for (int col=0;col<count-1;col++){
            line+="══╬═";
        }
        line+="══╣";
        return line;
    }
    private void top(int count, String tabs){
        System.out.printf("%s╔═",tabs);
        for(int line=0;line<count-1;line++){
            System.out.printf("══╦═");
        }
        System.out.printf("══╗%n");
    }
    private void bottom(int count, String tabs){
        System.out.printf("%s╚═",tabs);
        for(int line=0;line<count-1;line++){
            System.out.printf("══╩═");
        }
        System.out.printf("══╝%n");
    }
}