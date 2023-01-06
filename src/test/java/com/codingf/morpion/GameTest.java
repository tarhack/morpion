package com.codingf.morpion;

import static org.junit.jupiter.api.Assertions.*;
class GameTest {

    @org.junit.jupiter.api.Test
    void play() {
        Game game = new Game(3);
        System.out.println("<<<Start tests for PLAY Method>>>");
        // Pass I
        game.play(0,0); // X
        game.play(0,1); // X O
        game.play(0,2); // X O X
        game.display();
        System.out.println(String.format("Winner %d, Next Turn : %d",game.over(), game.getPlayTurn()));
        // Pass II
        // X O X
        // O O X
        game.play(1,0);
        game.play(1,2);
        game.play(1,1);
        game.display();
        System.out.println(String.format("Winner %d, Next Turn : %d",game.over(), game.getPlayTurn()));
        // Pass III
        // X O X
        // O O X
        // O O X
        game.play(2,0);
        game.play(2,2);
        game.play(2,1);
        game.display();
        System.out.println(String.format("Winner %d, Next Turn : %d",game.over(), game.getPlayTurn()));
        assertTrue(game.over()<0, "ERRUR Le jeu est en situation de blocage le retour de game.over() doit être négatif");
        System.out.println("<<<End tests for PLAY Method>>>");
    }
    @org.junit.jupiter.api.Test
    void clear() {
        Game game = new Game(3);
        game.display();
        System.out.println("-------------------------------------------");
        game.display();
    }
}