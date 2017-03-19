package com.android.example.tennisscore.data;

import com.android.example.tennisscore.R;
import com.android.example.tennisscore.models.Player;

import java.util.ArrayList;

public class PlayerDB {

    private ArrayList<Player> players;

    public PlayerDB() {
        this.players = new ArrayList<>();
    }

    public static PlayerDB GetSampleDB() {

        PlayerDB db = new PlayerDB();
        db.addPlayer(new Player("Roger", "Federer", "Switzerland", 1981, R.drawable.federer));
        db.addPlayer(new Player("Andy", "Murray with really long name, really", "Great Britain", 1987, R.drawable.murray));
        db.addPlayer(new Player("Novak", "Djokovic", "Serbia", 1987, R.drawable.djokovic));
        db.addPlayer(new Player("Rafael", "Nadal", "Spain", 1986, R.drawable.nadal));

        return db;

    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {

        if (this.players.contains(player)) {
            this.players.remove(player);
        }
    }

    public ArrayList<Player> GetPlayers() {
        return this.players;
    }
}
