package com.android.example.tennisscore.models;

public class Stats {

    public Player player;
    public String points;
    public int games;
    public int sets;
    public boolean isWinner;

    public Stats(Player player) {
        this.player = player;
        this.points = "0";
        this.games = 0;
        this.sets = 0;
        this.isWinner = false;
    }

}
