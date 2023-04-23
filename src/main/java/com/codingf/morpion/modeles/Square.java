package com.codingf.morpion.modeles;

public class Square {
    private int player ;
    private Integer number;
    public Square(){}
    public Square(int player) {
        this(player, null);
    }
    public Square(int player, Integer number) {
        this.player = player;
        this.number = number;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
