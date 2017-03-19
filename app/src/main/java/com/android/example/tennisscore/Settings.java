package com.android.example.tennisscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.example.tennisscore.data.PlayerDB;
import com.android.example.tennisscore.models.Player;
import com.android.example.tennisscore.models.TennisMatch;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    Spinner firstPlayerSpinner;
    Spinner secondPlayerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        populateSpinners();

    }

    private void populateSpinners() {
        firstPlayerSpinner = (Spinner) findViewById(R.id.first_player_spinner);
        secondPlayerSpinner = (Spinner) findViewById(R.id.spinner_two);

        ArrayList<Player> players = PlayerDB.GetSampleDB().GetPlayers();

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, players);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        firstPlayerSpinner.setAdapter(spinnerArrayAdapter);
        secondPlayerSpinner.setAdapter(spinnerArrayAdapter);
    }

    public void startMatch(View view) {

        Player firstPlayer = (Player) ((Spinner) findViewById(R.id.first_player_spinner)).getSelectedItem();
        Player secondPlayer = (Player) ((Spinner) findViewById(R.id.spinner_two)).getSelectedItem();

        if (firstPlayer.equals(secondPlayer)) {
            Toast.makeText(this, "First and second player must not be the same!", Toast.LENGTH_SHORT).show();
        } else {

            TennisMatch.firstPlayer = firstPlayer;
            TennisMatch.secondPlayer = secondPlayer;

            if (((RadioButton) findViewById(R.id.five_set_radio)).isChecked()) {
                TennisMatch.setCount = 5;
            } else {
                TennisMatch.setCount = 3;
            }

            startActivity(new Intent(this, MainActivity.class));
        }

    }

}
