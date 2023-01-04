package com.codingf.morpion.modeles;

public class Square {
    private int player ;

    public Square(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public String display(){
        if (player == 0 )
            return "-";
        else if (player == 1)
            return "X";
        else
            return "O";
    }
}
