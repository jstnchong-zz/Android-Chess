package com.example.justinchong.androidchess;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justinchong.androidchess.model.Game;
import com.example.justinchong.androidchess.model.Placeholder;
import com.example.justinchong.androidchess.model.Queen;
import com.example.justinchong.androidchess.model.Record;
import com.example.justinchong.androidchess.view.ChessView;

import java.io.File;

import static com.example.justinchong.androidchess.view.ChessView.board;
import static com.example.justinchong.androidchess.view.ChessView.playerTurn;

public class GameActivity extends AppCompatActivity {


    private static final String TAG = GameActivity.class.getSimpleName();

    public static TextView whiteText;
    public static TextView blackText;


    public static Record replayGames = new Record();
    public static File file;
    public static File newFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.d(TAG, "crash1");
        final ChessView chessView = new ChessView(this);
        final View resultView = (View) findViewById(R.id.ChessView);
        final Button resign = (Button) findViewById(R.id.resignButton);
        final Button draw = (Button) findViewById(R.id.drawButton);
        final Button undo = (Button) findViewById(R.id.undoButton);
        final Button help = (Button) findViewById(R.id.helpButton);
        whiteText = (TextView) findViewById(R.id.whiteTextView);
        blackText = (TextView) findViewById(R.id.blackTextView);

        Log.d(TAG, "crash2");
        file = new File(resultView.getContext().getFilesDir()+File.separator+
                "storage");
        file.mkdir();
        newFile = new File(file,"record.dat");

        resign.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){


                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Resign from game?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Log.d(TAG, "here");
                        chessView.gameOver = true;
                        // Check which player resigned
                        if (playerTurn == true) {
                            //whiteText.setVisibility(View.INVISIBLE);
                            whiteText.setText("Black Wins!");
                        } else {
                            //blackText.setVisibility(View.GONE);
                            blackText.setText("White Wins! ");
                        }
                        draw.setVisibility(View.INVISIBLE);      // Hide buttons after game ends
                        resign.setVisibility(View.INVISIBLE);
                        undo.setVisibility(View.INVISIBLE);
                        help.setVisibility(View.INVISIBLE);

                        LinearLayout layout = new LinearLayout(resultView.getContext());
                        layout.setOrientation(LinearLayout.VERTICAL);
                        AlertDialog.Builder recordDialog = new AlertDialog.Builder(resultView.getContext());
                        recordDialog.setMessage("Would you like to save this game?");
                        final EditText gameTitle = new EditText(resultView.getContext());
                        layout.addView(gameTitle);
                        recordDialog.setView(layout);

                        recordDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (gameTitle.getText().toString().isEmpty()) {
                                    gameTitle.setText("");
                                }

                                Game dataInfo = new Game(gameTitle.getText().toString(), ChessView.movements);

                                replayGames.gameList.add(dataInfo);
                                Toast.makeText(resultView.getContext(), "Saved as " + gameTitle.getText().toString() + " in " + newFile.getPath(), Toast.LENGTH_LONG).show();

                                try {
                                    Log.d(TAG, "what is up");
                                    Record.write(replayGames);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                        recordDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert1 = recordDialog.create();
                        alert1.show();
                    }



                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                resultView.invalidate();
            }
        });


        //draw
        draw.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){

                if(chessView.askDraw == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle(R.string.app_name);
                    builder.setMessage("Accept Request for Draw?");
                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            Log.d(TAG, "ask for draw");
                            chessView.gameOver = true;
                            // Check which player resigned
                            if (playerTurn == true) {
                                //whiteText.setVisibility(View.INVISIBLE);
                                whiteText.setText("DRAW! ");
                            } else {
                                //blackText.setVisibility(View.GONE);
                                blackText.setText("DRAW! ");
                            }
                            draw.setVisibility(View.INVISIBLE);      // Hide buttons after game ends
                            resign.setVisibility(View.INVISIBLE);
                            undo.setVisibility(View.INVISIBLE);
                            help.setVisibility(View.INVISIBLE);

                            LinearLayout layout = new LinearLayout(resultView.getContext());
                            layout.setOrientation(LinearLayout.VERTICAL);
                            AlertDialog.Builder recordDialog = new AlertDialog.Builder(resultView.getContext());
                            recordDialog.setMessage("Would you like to save this game?");
                            final EditText gameTitle = new EditText(resultView.getContext());
                            layout.addView(gameTitle);
                            recordDialog.setView(layout);

                            recordDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    if (gameTitle.getText().toString().isEmpty()) {
                                        gameTitle.setText("");
                                    }
                                    
                                    Game dataInfo = new Game(gameTitle.getText().toString(), ChessView.movements);

                                    replayGames.gameList.add(dataInfo);
                                    Toast.makeText(resultView.getContext(), "Saved as " + gameTitle.getText().toString() + " in " + newFile.getPath(), Toast.LENGTH_LONG).show();

                                    try {
                                        Record.write(replayGames);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });

                            recordDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alert1 = recordDialog.create();
                            alert1.show();
                        }


                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    resultView.invalidate();
                }
            }
        });


        help.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){

                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Would you like help?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Log.d(TAG, "here");

                        for (int i = 0; i<=7; i++){
                            for (int j = 0; j<=7; j++){
                                if(!chessView.board[i][j].getPiece().getName().equals("") && board[i][j].getPiece().getPlayer() == playerTurn){
                                    for (int x = 0; x<=7; x++) {
                                        for (int y = 0; y <= 7; y++) {


                                            if(chessView.board[i][j].getPiece().getPlayer() != chessView.board[x][y].getPiece().getPlayer() && chessView.board[i][j].getPiece().validMove(j, i, y, x, chessView.canCapture(j, i, y, x))){
                                                Log.d(TAG, "valid move works");
                                                if(chessView.noObstacles(chessView.board[i][j].getPiece(), i, j, x, y)){
                                                    Log.d(TAG, "no obstacles move works");
                                                    chessView.board[x][y].setPiece(chessView.board[i][j].getPiece());
                                                    chessView.board[i][j].setPiece(new Placeholder());
                                                    if(chessView.board[x][y].getPiece().getName().equals("wp") || chessView.board[x][y].getPiece().getName().equals("bp")){
                                                        if(chessView.board[x][y].getPiece().getPlayer()==false && i==0){
                                                            chessView.board[x][y].setPiece(new Queen(playerTurn));
                                                        }
                                                        if(chessView.board[x][y].getPiece().getPlayer()==true && i==7){
                                                            chessView.board[x][y].setPiece(new Queen(playerTurn));

                                                        }
                                                    }

                                                    if(chessView.playerTurn == false) {
                                                        chessView.playerTurn = true;
                                                        whiteText.setVisibility(View.VISIBLE);
                                                        blackText.setVisibility(View.INVISIBLE);
                                                        whiteText.setText("White Move");
                                                    }
                                                    else if(chessView.playerTurn == true) {
                                                        whiteText.setVisibility(View.INVISIBLE);
                                                        blackText.setVisibility(View.VISIBLE);
                                                        blackText.setText("Black Move");
                                                        chessView.playerTurn = false;
                                                    }

                                                    resultView.invalidate();

                                                }

                                                else{
                                                    ;
                                                }
                                            }
                                        }
                                    }


                                }

                            }
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                resultView.invalidate();
            }
        });



    }
}
