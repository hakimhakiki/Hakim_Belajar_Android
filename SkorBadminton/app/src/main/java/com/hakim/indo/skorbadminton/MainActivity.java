package com.hakim.indo.skorbadminton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Skor, jumlah game awal dan status permainan
    private int scoreIna, scoreChn, gameIna, gameChn = 0;
    private int isPlaying = 1;

    // Skor pasti
    private int min_point = 2;
    private int max_point = 3;

    // Element yang dibutuhkan
    private TextView mScoreIna;
    private TextView mScoreChn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreIna = (TextView) findViewById(R.id.scoreText_ina);
        mScoreChn = (TextView) findViewById(R.id.scoreText_chn);

        if(savedInstanceState != null){
            gameIna = savedInstanceState.getInt("game_ina");
            gameChn = savedInstanceState.getInt("game_chn");
            scoreIna = savedInstanceState.getInt("score_ina");
            scoreChn = savedInstanceState.getInt("score_chn");
            isPlaying = savedInstanceState.getInt("play");
            viewScore();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("game_ina", gameIna);
        outState.putInt("game_chn", gameChn);
        outState.putInt("score_ina", scoreIna);
        outState.putInt("score_chn", scoreChn);
        outState.putInt("play", isPlaying);
    }

    private void checkWin() {

        if (scoreIna >= 21 && (gameIna > gameChn || gameIna == 3)) {
            //Indonesia menang
            isPlaying = 0;
            Toast toast = Toast.makeText(this, "Indonesia Menang", Toast.LENGTH_LONG);
            toast.show();

        } else if (scoreChn >= 21 && (gameIna < gameChn || gameChn == 3)) {
            //China menang
            isPlaying = 0;
            Toast toast = Toast.makeText(this, "China Menang", Toast.LENGTH_LONG);
            toast.show();

        } else if (isPlaying == 1) {
            checkGame();
        }
    }

    private void checkGame() {
        if (scoreIna > 21) {
            gameIna++;
            scoreIna = 0;
        } else if (scoreChn > 21) {
            gameChn++;
            scoreChn = 0;
        }
    }

    private void viewScore() {
        // Indonesia
        mScoreIna.setText(gameIna + " : " + scoreIna);
        //China
        mScoreChn.setText(gameChn + " : " + scoreChn);
    }

    public void inaAddMin(View view) {
        scoreIna = scoreIna + (min_point * isPlaying);
        checkWin();
        viewScore();
    }

    public void inaAddMax(View view) {
        scoreIna = scoreIna + (max_point * isPlaying);
        checkWin();
        viewScore();
    }

    public void chnAddMin(View view) {
        scoreChn = scoreChn + (min_point * isPlaying);
        checkWin();
        viewScore();
    }

    public void chnAddMax(View view) {
        scoreChn = scoreChn + (max_point * isPlaying);
        checkWin();
        viewScore();
    }

    public void resetAll(View view){
        gameChn = 0;
        gameIna = 0;
        scoreChn = 0;
        scoreIna = 0;
        isPlaying = 1;
        viewScore();
    }


}
