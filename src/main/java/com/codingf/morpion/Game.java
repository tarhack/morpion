package com.codingf.morpion;

import com.codingf.morpion.modeles.Square;

import java.util.HashMap;

/**
 * Main Classe
 */
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

    public boolean play(int line, int col){
        if ( grid.get(line)[col].getPlayer() > 0 )
            return false;
        else
            grid.get(line)[col].setPlayer(playTurn);

        playTurn = playTurn==1?2:1;
        return true;
    }

    public void display() {
        var iter = grid.entrySet().stream().iterator();
        System.out.print("  ");
        for(int idx=0;idx < grid.size();idx++)
            System.out.print(idx+" ");
        System.out.println("");
        while (iter.hasNext()) {
            var square = iter.next();
            System.out.print(square.getKey() );
            var line = square.getValue();
            for (int i = 0 ; i < line.length; i++) {
                System.out.print(" "+line[i].display());
            }
            System.out.println("");
        }
    }

    public int getPlayTurn() {
        return playTurn;
    }

    public void setPlayTurn(int playTurn) {
        this.playTurn = playTurn;
    }
}
