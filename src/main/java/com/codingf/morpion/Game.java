package com.codingf.morpion;

import com.codingf.morpion.modeles.Square;

import java.util.HashMap;

public class Game {
    private int nbSquare;
    private int playTurn ;
    private HashMap<Integer, Square[]> grid;

    public Game(int nbSquare) {
        this.nbSquare = nbSquare;
        init();
    }

    private void init() {
        playTurn = 1;
        grid = new HashMap<>();
        for (int line = 0; line < nbSquare; line++) {
            Square[] squares = new Square[nbSquare];
            for (int col = 0; col < nbSquare; col++) {
                squares[col] = new Square(0);
            }
            grid.put(line, squares);
        }
    }

    public void display() {
        var iter = grid.entrySet().stream().iterator();
        while (iter.hasNext()) {
            var square = iter.next();
            System.out.print(square.getKey() + " ");
            var line = square.getValue();
            for (int i = 0; i < line.length; i++) {
                System.out.print(line[i].display());
            }
            System.out.println("");
        }
    }
}
