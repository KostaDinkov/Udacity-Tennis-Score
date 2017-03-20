package com.android.example.tennisscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.example.tennisscore.models.Stats;
import com.android.example.tennisscore.models.TennisMatch;

public class MainActivity extends AppCompatActivity {

    private TennisMatch match;
    private int[] sets_views =
            {
                    R.id.top_set1,
                    R.id.top_set2,
                    R.id.top_set3,
                    R.id.top_set4,
                    R.id.top_set5,
                    R.id.bottom_set1,
                    R.id.bottom_set2,
                    R.id.bottom_set3,
                    R.id.bottom_set4,
                    R.id.bottom_set5

            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init() {

        for (int i = 0; i < sets_views.length; i++) {
            findViewById(sets_views[i]).setVisibility(View.GONE);
        }

        ((TextView) findViewById(R.id.top_name)).setText(TennisMatch.firstPlayer.toString());
        ((TextView) findViewById(R.id.top_age)).setText("Age: " + TennisMatch.firstPlayer.getAge());
        ((TextView) findViewById(R.id.top_country)).setText("Country: " + TennisMatch.firstPlayer.getCountry());
        ((ImageView) findViewById(R.id.top_avatar)).setImageResource(TennisMatch.firstPlayer.getAvatarId());

        ((TextView) findViewById(R.id.bottom_name)).setText(TennisMatch.secondPlayer.toString());
        ((TextView) findViewById(R.id.bottom_age)).setText("Age: " + TennisMatch.secondPlayer.getAge());
        ((TextView) findViewById(R.id.bottom_country)).setText("Country: " + TennisMatch.secondPlayer.getCountry());
        ((ImageView) findViewById(R.id.bottom_avatar)).setImageResource(TennisMatch.secondPlayer.getAvatarId());

        this.match = new TennisMatch();
        this.match.secondPlayerStats = new Stats(TennisMatch.firstPlayer);
        this.match.secondPlayerStats = new Stats(TennisMatch.secondPlayer);
        match.firstPlayerStats.isServing = true;
        setServingPlayer();

    }

    public void onAddPointClick(View view) {

        if (view.getId() == R.id.top_add) {

            match.addPoints(match.firstPlayerStats, match.secondPlayerStats);
            updateScoreDisplay();
        } else {
            match.addPoints(match.secondPlayerStats, match.firstPlayerStats);
            updateScoreDisplay();
        }
        if (match.isGameOver) {

            findViewById(R.id.top_add).setEnabled(false);
            findViewById(R.id.bottom_add).setEnabled(false);

            if (match.firstPlayerStats.isWinner) {

                TextView winner = ((TextView) findViewById(R.id.top_name));
                winner.setText(("WINNER\n" + winner.getText()));
            } else {
                TextView winner = ((TextView) findViewById(R.id.bottom_name));
                winner.setText(("WINNER\n" + winner.getText()));
            }
        }

    }

    public void updateScoreDisplay() {

        setServingPlayer();
        TextView currentSetTop = (TextView) findViewById(sets_views[TennisMatch.currentSet - 1]);
        TextView currentSetBottom = (TextView) findViewById(sets_views[TennisMatch.currentSet + 4]);

        currentSetTop.setVisibility(View.VISIBLE);
        currentSetBottom.setVisibility(View.VISIBLE);

        currentSetTop.setText(Integer.toString(match.firstPlayerStats.games));
        currentSetBottom.setText(Integer.toString(match.secondPlayerStats.games));

        if (TennisMatch.isSetOver) {
            match.firstPlayerStats.games = 0;
            match.secondPlayerStats.games = 0;
            TennisMatch.currentSet++;
            TennisMatch.isSetOver = false;
        }

        ((TextView) findViewById(R.id.top_score)).setText(match.firstPlayerStats.points);
        ((TextView) findViewById(R.id.bottom_score)).setText(match.secondPlayerStats.points);
    }

    private void setServingPlayer() {

        if (match.firstPlayerStats.isServing) {
            findViewById(R.id.serving_top).setVisibility(View.VISIBLE);
            findViewById(R.id.ball_top).setVisibility(View.VISIBLE);
            findViewById(R.id.serving_bottom).setVisibility(View.INVISIBLE);
            findViewById(R.id.ball_bottom).setVisibility(View.INVISIBLE);
        } else {
            findViewById(R.id.serving_top).setVisibility(View.INVISIBLE);
            findViewById(R.id.ball_top).setVisibility(View.INVISIBLE);
            findViewById(R.id.serving_bottom).setVisibility(View.VISIBLE);
            findViewById(R.id.ball_bottom).setVisibility(View.VISIBLE);
        }
    }
}
