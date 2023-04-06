package com.codingf.morpion.domain;

import com.codingf.morpion.modeles.Square;

import java.util.HashMap;

/**
 * Cette classe execute toute la logique du jeu du Morpion
 *  <p>
 * Elle a la reponsabilité des fonctions suivantes :
 * <p>
 *  -1 Initilisation d'une partie, avec le nombre de cases du jeu
 *  -2 Elle joue un coup, et attribue le tour au joueur suivant
 *  -3 Elle restitue le symbole du joueur (X, O, etc ...)
 *  -4 Elle calcule l'état du jeu : le jeu continue, il y a un gagnant, le jeu est bloqué
 */
public class Game {
    private final int nbSquare;
    private int playTurn ;
    private HashMap<Integer, Square[]> grid;

    public Game(int nbSquare) {
        this.nbSquare = nbSquare;
        init();
    }


    public HashMap<Integer, Square[]> getGrid() {
        return grid;
    }

    public int getNbSquare() {
        return nbSquare;
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

    /**
     * Cette méthode convertie un numéro de case en coordonnées ligne, colonne de l'aire de jeu
     *
     */
    public boolean play(int numCase){
        var coords = toLineAndColumn(numCase);
        return play(coords[0],coords[1]);
    }

    /**
     *
     * @param line
     * @param col
     * @return
     */
    public boolean play(int line, int col){
        if ( grid.get(line)[col].getPlayer() > 0 )
            return false;
        else
            grid.get(line)[col].setPlayer(playTurn);

        playTurn = playTurn==1?2:1;
        return true;
    }

    public int getPlayTurn() {
        return playTurn;
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
            for (Square square : squares) {
                if (square.getPlayer() == 0) {
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

    /**
     * Cette méthode convertie une coordonnée ligne, colonne en numéro de case sur aire de jeu
     * ligne, colonne et numéro commencent à 0
     * @param game - Aire de jeu
     * @param line - No Ligne
     * @param col - No Colonne
     * @return : Un numéro de case
     */
    public int toCaseNumber(Game game, int line, int col){
        int numCase = line * getNbSquare() ;
        numCase += col;
        return numCase;
    }

    /**
     * Cette méthode convertie un numéro de case en coordonnées ligne, colonne de l'aire de jeu
     * ligne, colonne et numéro commencent à 0
     * @param numCase
     * @return : Un tableau de int où t[0] =ligne, t[1]=colonne
     */
    public int[] toLineAndColumn(int numCase){
        int l = numCase / getNbSquare();
        int c = numCase % getNbSquare();
        return new int[]{l,c} ;
    }

}
