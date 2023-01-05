package com.codingf.morpion;

import com.codingf.morpion.modeles.Square;

import java.util.HashMap;

/**
 * Morpion Classe
 */
public class Game {
    private final int nbSquare;
    private int playTurn ;
    private HashMap<Integer, Square[]> grid;

    public Game(int nbSquare) {
        this.nbSquare = nbSquare;
        init();
    }

    public void init() {
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

    public int over(){
        int winner = 0;
        // Contrôle en ligne
        var iter = grid.entrySet().stream().iterator();
        while(iter.hasNext()){
            var squares = iter.next().getValue();
            winner = squares[0].getPlayer() ;
            if ( winner == 0 )
                continue;
            for(int idx=1;idx<squares.length;idx++){
                if ( squares[idx].getPlayer() !=  winner ) {
                    winner = 0;
                    break;
                }
            }
            if ( winner != 0 )
                break ;
        }

        if ( winner != 0 )
            return winner;

        for (int col=0;col<grid.size();col++){
            winner = grid.get(0)[col].getPlayer();
            if ( winner == 0)
                continue;
            for (int line=0;line<grid.size();line++){
                if( grid.get(line)[col].getPlayer() != winner ){
                    winner = 0;
                    break;
                }
            }
            if ( winner > 0 )
                break;
        }

        if ( winner != 0)
            return winner;

        winner = grid.get(0)[0].getPlayer();
        for (int line=0;line<grid.size();line++){
           if ( grid.get(line)[line].getPlayer() != winner ) {
               winner = 0;
               break;
           }
        }
        if ( winner != 0)
            return winner;

        winner = grid.get(0)[grid.size()-1].getPlayer();
        for (int line=1;line < grid.size();line++){
            int col = grid.size()-1-line;
            if ( grid.get(line)[col].getPlayer() != winner ) {
                winner = 0;
                break;
            }
        }

        if ( winner != 0)
            return winner;

        // Test situation de blocage
        boolean block = true;
        for(int line=0;line< grid.size();line++){
            var squares = grid.get(line);
            for(int col=0;col<squares.length;col++){
                if(squares[col].getPlayer() == 0) {
                    block = false;  // Si une case peut-être jouée alors pas de blocage
                    break;
                }
            }
            if ( ! block )
                break;
        }

        // System.out.println(String.format("Block O/N ? : %b , winner : %d ",block,winner));
        
        return block ? -1 : winner ;
    }
}
