package com.example.justinchong.androidchess;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.justinchong.androidchess.model.Record;
import com.example.justinchong.androidchess.view.ChessView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            GameActivity.replayGames = Record.read();

        }catch(Exception d){

        }
    }

    public void playButton(View v)
    {
        Bundle bundle = new Bundle();
        ChessView.gameOver = false;
        ChessView.gameStart = true;
        ChessView.playerTurn = false;
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    public void replayButton(View v)
    {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(MainActivity.this, ReplayActivity.class);
        startActivity(intent);
    }
}
