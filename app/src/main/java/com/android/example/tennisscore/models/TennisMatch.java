package com.android.example.tennisscore.models;

public class TennisMatch {

    public static Player firstPlayer;
    public static Player secondPlayer;
    public static int currentSet = 1;
    public static boolean isSetOver = false;
    public static int setCount;
    public boolean isGameOver;

    public Stats firstPlayerStats;
    public Stats secondPlayerStats;

    public TennisMatch() {

        this.firstPlayerStats = new Stats(firstPlayer);
        this.secondPlayerStats = new Stats(secondPlayer);
        this.isGameOver = false;
    }

    public void addPoints(Stats playerStats, Stats compareStats) {

        switch (playerStats.points) {
            case "0":
                playerStats.points = "15";
                break;
            case "15":
                playerStats.points = "30";
                break;
            case "30":
                playerStats.points = "40";
                break;
            case "40":
                if (compareStats.points.equals("40")) {
                    playerStats.points = "AD";
                } else if (compareStats.points.equals("AD")) {
                    compareStats.points = "40";
                } else {
                    addGame(playerStats, compareStats);
                    compareStats.points = "0";
                    playerStats.points = "0";
                }
                break;
            case "AD":
                addGame(playerStats, compareStats);
                compareStats.points = "0";
                playerStats.points = "0";
                break;
        }
    }

    private void addGame(Stats playerStats, Stats compareStats) {

        if (playerStats.games == 5) {
            if (compareStats.games < 5) {
                playerStats.games++;
                isSetOver = true;
                addSet(playerStats);

            } else {
                playerStats.games++;
            }

        } else if (playerStats.games == 6) {
            playerStats.games++;
            isSetOver = true;
            addSet(playerStats);
        } else {
            playerStats.games++;
        }

    }

    private void addSet(Stats playerStats) {

        playerStats.sets++;

        if (playerStats.sets == (setCount / 2) + 1) {
            gameOver(playerStats);
        }
    }

    private void gameOver(Stats playerStats) {

        this.isGameOver = true;
        playerStats.isWinner = true;
    }
}
