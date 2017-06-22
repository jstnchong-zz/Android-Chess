package com.example.justinchong.androidchess.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justinchong.androidchess.R;
import com.example.justinchong.androidchess.model.Bishop;
import com.example.justinchong.androidchess.model.Coordinate;
import com.example.justinchong.androidchess.model.King;
import com.example.justinchong.androidchess.model.Knight;
import com.example.justinchong.androidchess.model.Move;
import com.example.justinchong.androidchess.model.Pawn;
import com.example.justinchong.androidchess.model.Piece;
import com.example.justinchong.androidchess.model.Placeholder;
import com.example.justinchong.androidchess.model.Queen;
import com.example.justinchong.androidchess.model.Rook;
import com.example.justinchong.androidchess.model.Square;

import java.util.ArrayList;


/**
 * Created by justinchong on 4/30/17.
 */

public class ChessView extends View {

    private static final String TAG = ChessView.class.getSimpleName();

    public static Square[][] board;
    private Canvas canvas = new Canvas();
    private Rect rect;
    public int sideLength;
    public int initialLeft;
    public int initialTop;
    public int leftPos;
    public int topPos;
    public int rightPos;
    public int bottomPos;
    Bitmap pieceIcon;


    public static ArrayList<Move> movements = new ArrayList<Move>();
    public static boolean gameStart = true;
    public static boolean gameOver = true;
    public static boolean playerTurn;
    public boolean askDraw = false;
    public boolean selected = false;

    int srcX;
    int srcY;

    int srcRow;
    int srcCol;

    int destX;
    int destY;

    int destRow;
    int destCol;

    public ChessView(final Context context) {
        super(context);
        this.board = new Square[8][8];
        initializeSquares();
    }

    public ChessView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.board = new Square[8][8];
        initializeSquares();
    }

    private void initializeSquares() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j, new Placeholder());
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas){
        if(gameStart == true) {
            this.canvas = canvas;
            initializeBoard();
            gameStart = false;
        }
        else{
            Log.d(TAG, "onDraw: ");
            this.canvas = canvas;
            drawBoard();
        }
    }


    public void initializeBoard(){

        final int width = getWidth();
        final int height = getHeight();

        if(width>=height){
            this.sideLength = height/8;
        }
        else{
            this.sideLength = width/8;
        }

        this.initialLeft = (width - this.sideLength * 8) / 2;
        this.initialTop = (height- this.sideLength * 8) / 2;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.leftPos = this.initialLeft + (i * this.sideLength);
                this.rightPos = this.leftPos + this.sideLength;
                this.topPos = this.initialTop + (j * this.sideLength);
                this.bottomPos = this.topPos + this.sideLength;

                rect = new Rect(leftPos, topPos, rightPos, bottomPos);

                board[j][i].setRectangle(rect);
                board[j][i].drawSquare(canvas);
            }
        }

        for(int j = 0; j<8; j++){

            Bitmap wp = BitmapFactory.decodeResource(getResources(), R.drawable.wpawn);
            pieceIcon = Bitmap.createScaledBitmap(wp,74,74,true);
            board[1][j].drawSquare(canvas,board[1][j].rectangle, pieceIcon);

            board[1][j].setPiece(new Pawn(true));
        }

        for(int j = 0; j<8; j++){

            Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.bpawn);
            pieceIcon = Bitmap.createScaledBitmap(bp,74,74,true);
            board[6][j].drawSquare(canvas,board[6][j].rectangle, pieceIcon);

            board[6][j].setPiece(new Pawn(false));
        }

        //white rook
        Bitmap wr = BitmapFactory.decodeResource(getResources(), R.drawable.wrook);
        pieceIcon = Bitmap.createScaledBitmap(wr,74,74,true);
        board[0][0].drawSquare(canvas,board[0][0].rectangle, pieceIcon);
        board[0][7].drawSquare(canvas,board[0][7].rectangle, pieceIcon);


        board[0][0].setPiece(new Rook(true));
        board[0][7].setPiece(new Rook(true));


        //black rook
        Bitmap br = BitmapFactory.decodeResource(getResources(), R.drawable.brook);
        pieceIcon = Bitmap.createScaledBitmap(br,74,74,true);
        board[7][0].drawSquare(canvas,board[7][0].rectangle, pieceIcon);
        board[7][7].drawSquare(canvas,board[7][7].rectangle, pieceIcon);


        board[7][0].setPiece(new Rook(false));
        board[7][7].setPiece(new Rook(false));


        //white knight
        Bitmap wknight = BitmapFactory.decodeResource(getResources(), R.drawable.wknight);
        pieceIcon = Bitmap.createScaledBitmap(wknight,74,74,true);
        board[0][1].drawSquare(canvas,board[0][1].rectangle, pieceIcon);
        board[0][6].drawSquare(canvas,board[0][6].rectangle, pieceIcon);


        board[0][1].setPiece(new Knight(true));
        board[0][6].setPiece(new Knight(true));


        //black knight
        Bitmap bknight = BitmapFactory.decodeResource(getResources(), R.drawable.bknight);
        pieceIcon = Bitmap.createScaledBitmap(bknight,74,74,true);
        board[7][1].drawSquare(canvas,board[7][1].rectangle, pieceIcon);
        board[7][6].drawSquare(canvas,board[7][6].rectangle, pieceIcon);


        board[7][1].setPiece(new Knight(false));
        board[7][6].setPiece(new Knight(false));



        //white bishop
        Bitmap wb = BitmapFactory.decodeResource(getResources(), R.drawable.wbishop);
        pieceIcon = Bitmap.createScaledBitmap(wb,74,74,true);
        board[0][2].drawSquare(canvas,board[0][2].rectangle, pieceIcon);
        board[0][5].drawSquare(canvas,board[0][5].rectangle, pieceIcon);


        board[0][2].setPiece(new Bishop(true));
        board[0][5].setPiece(new Bishop(true));


        //black bishop
        Bitmap bb = BitmapFactory.decodeResource(getResources(), R.drawable.bbishop);
        pieceIcon = Bitmap.createScaledBitmap(bb,74,74,true);
        board[7][2].drawSquare(canvas,board[7][2].rectangle, pieceIcon);
        board[7][5].drawSquare(canvas,board[7][5].rectangle, pieceIcon);


        board[7][2].setPiece(new Bishop(false));
        board[7][5].setPiece(new Bishop(false));


        //white Queen
        Bitmap wQ = BitmapFactory.decodeResource(getResources(), R.drawable.wqueen);
        pieceIcon = Bitmap.createScaledBitmap(wQ,74,74,true);
        board[0][3].drawSquare(canvas,board[0][4].rectangle, pieceIcon);


        board[0][3].setPiece(new Queen(true));


        //black Queen
        Bitmap bQ = BitmapFactory.decodeResource(getResources(), R.drawable.bqueen);
        pieceIcon = Bitmap.createScaledBitmap(bQ,74,74,true);
        board[7][3].drawSquare(canvas,board[7][4].rectangle, pieceIcon);

        board[7][3].setPiece(new Queen(false));


        //white kING
        Bitmap wK = BitmapFactory.decodeResource(getResources(), R.drawable.wking);
        pieceIcon = Bitmap.createScaledBitmap(wK,74,74,true);
        board[0][4].drawSquare(canvas,board[0][3].rectangle, pieceIcon);


        board[0][4].setPiece(new King(true));

        //black kING
        Bitmap bK = BitmapFactory.decodeResource(getResources(), R.drawable.bking);
        pieceIcon = Bitmap.createScaledBitmap(bK,74,74,true);
        board[7][4].drawSquare(canvas,board[7][3].rectangle, pieceIcon);


        board[7][4].setPiece(new King(false));

    }


    public void drawBoard(){


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {



                if (board[i][j].getPiece().getName().equals("")) {

                    board[i][j].drawSquare(canvas);

                }


                if (board[i][j].getPiece().getName().equals("wp")) {
                    Bitmap wp = BitmapFactory.decodeResource(getResources(), R.drawable.wpawn);
                    pieceIcon = Bitmap.createScaledBitmap(wp, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);

                }

                if (board[i][j].getPiece().getName().equals("bp")) {
                    Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.bpawn);
                    pieceIcon = Bitmap.createScaledBitmap(bp, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }
                if (board[i][j].getPiece().getName().equals("wR")) {
                    Bitmap wr = BitmapFactory.decodeResource(getResources(), R.drawable.wrook);
                    pieceIcon = Bitmap.createScaledBitmap(wr, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }
                if (board[i][j].getPiece().getName().equals("bR")) {
                    Bitmap br = BitmapFactory.decodeResource(getResources(), R.drawable.brook);
                    pieceIcon = Bitmap.createScaledBitmap(br, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }
                if (board[i][j].getPiece().getName().equals("wN")) {
                    Bitmap wn = BitmapFactory.decodeResource(getResources(), R.drawable.wknight);
                    pieceIcon = Bitmap.createScaledBitmap(wn, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }
                if (board[i][j].getPiece().getName().equals("bN")) {
                    Bitmap bn = BitmapFactory.decodeResource(getResources(), R.drawable.bknight);
                    pieceIcon = Bitmap.createScaledBitmap(bn, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }
                if (board[i][j].getPiece().getName().equals("wB")) {
                    Bitmap wb = BitmapFactory.decodeResource(getResources(), R.drawable.wbishop);
                    pieceIcon = Bitmap.createScaledBitmap(wb, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }
                if (board[i][j].getPiece().getName().equals("bB")) {
                    Bitmap bb = BitmapFactory.decodeResource(getResources(), R.drawable.bbishop);
                    pieceIcon = Bitmap.createScaledBitmap(bb, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }
                if (board[i][j].getPiece().getName().equals("wQ")) {
                    Bitmap wq = BitmapFactory.decodeResource(getResources(), R.drawable.wqueen);
                    pieceIcon = Bitmap.createScaledBitmap(wq, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }
                if (board[i][j].getPiece().getName().equals("bQ")) {
                    Bitmap bq = BitmapFactory.decodeResource(getResources(), R.drawable.bqueen);
                    pieceIcon = Bitmap.createScaledBitmap(bq, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }
                if (board[i][j].getPiece().getName().equals("wK")) {
                    Bitmap wk = BitmapFactory.decodeResource(getResources(), R.drawable.wking);
                    pieceIcon = Bitmap.createScaledBitmap(wk, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }
                if (board[i][j].getPiece().getName().equals("bK")) {
                    Bitmap bk = BitmapFactory.decodeResource(getResources(), R.drawable.bking);
                    pieceIcon = Bitmap.createScaledBitmap(bk, 74, 74, true);
                    board[i][j].drawSquare(canvas, board[i][j].rectangle, pieceIcon);
                }


            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {


        if(gameOver == true){
            return true;
        }
        Canvas canvas = new Canvas();

        if(e.getAction() == MotionEvent.ACTION_DOWN){


            if(selected == false){


                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {

                        //Log.d(TAG, "wtf");

                        srcX = (int)e.getX();
                        srcY = (int)e.getY();

                        if (board[i][j].touch(srcX, srcY)){
                            if(board[i][j].piece.getPlayer() != playerTurn){
                                Log.d(TAG, "wtf");
                            }
                            else if(board[i][j].piece.getName().equals("")){
                                Log.d(TAG, "matt");
                            }
                            else{

                                board[i][j].selection = true;
                                srcRow = i;
                                srcCol = j;
                                selected = true;

                            }

                        }
                    }
                }
            }

            else if(selected == true){

                selected = false;

                destX = (int)e.getX();
                destY = (int)e.getY();

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {

                        if (board[i][j].touch(destX, destY)){



                            if(!board[i][j].getPiece().getName().equals("") && board[srcRow][srcCol].getPiece().getPlayer() == board[i][j].getPiece().getPlayer()){
                                board[srcRow][srcCol].selection = false;
                                invalidate();
                                return true;
                            }

                            if(board[srcRow][srcCol].getPiece().validMove(srcCol, srcRow, j, i, canCapture(srcCol, srcRow, j, i))){
                                //Log.d(TAG, "valid move works");
                                if(noObstacles(board[srcRow][srcCol].getPiece(), srcRow, srcCol, i, j)){
                                    //Log.d(TAG, "no obstacles move works");
                                    board[i][j].setPiece(board[srcRow][srcCol].getPiece());
                                    board[srcRow][srcCol].setPiece(new Placeholder());
                                    board[srcRow][srcCol].selection = false;
                                    Log.d(TAG, "problem here");
                                    Move newMove = new Move(board[srcRow][srcCol].getPiece(), srcRow, srcCol, i, j);
                                    //getData(srcRow, srcCol, i, j);
                                    movements.add(newMove);
                                    if(board[i][j].getPiece().getName().equals("wp") || board[i][j].getPiece().getName().equals("bp")){
                                        if(board[i][j].getPiece().getPlayer()==false && i==0){
                                            board[i][j].setPiece(new Queen(playerTurn));
                                        }
                                        if(board[i][j].getPiece().getPlayer()==true && i==7){
                                            board[i][j].setPiece(new Queen(playerTurn));

                                        }
                                    }
                                    if(check(!playerTurn)) {
                                        if(playerTurn == true) {
                                            Toast.makeText(getContext(), "Black King is in Check!", Toast.LENGTH_SHORT).show();
                                        }
                                        else if(playerTurn == false) {
                                            Toast.makeText(getContext(), "White King is in Check!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    changeTurn();
                                    changeText();

                                    if(check(!playerTurn)) {
                                        if (playerTurn == true) {
                                            Toast.makeText(getContext(), "CheckMate!", Toast.LENGTH_SHORT).show();
                                            getRootView().findViewById(R.id.whiteTextView).setVisibility(VISIBLE);
                                            getRootView().findViewById(R.id.blackTextView).setVisibility(INVISIBLE);
                                            TextView whiteWins = (TextView) getRootView().findViewById(R.id.whiteTextView);
                                            whiteWins.setText("White Wins! ");
                                            gameOver = true;

                                        } else if (playerTurn == false) {
                                            Toast.makeText(getContext(), "CheckMate!", Toast.LENGTH_SHORT).show();
                                            getRootView().findViewById(R.id.whiteTextView).setVisibility(INVISIBLE);
                                            getRootView().findViewById(R.id.blackTextView).setVisibility(VISIBLE);
                                            TextView blackWins = (TextView) getRootView().findViewById(R.id.blackTextView);
                                            blackWins.setText("Black Wins! ");
                                            gameOver=true;
                                        }
                                    }



                                    invalidate();
                                    return true;
                                }

                                else{
                                    ;
                                }
                            }
                            //Log.d(TAG, "vgets here 2");
                            if(castling(board[srcRow][srcCol].getPiece(), srcCol, srcRow, j, i)){
                                board[srcRow][srcCol].selection = false;
                                if(check(!playerTurn)){
                                    if(playerTurn == true) {
                                        Toast.makeText(getContext(), "Black King is in Check!", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(playerTurn == false) {
                                        Toast.makeText(getContext(), "White King is in Check!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                getData(srcRow, srcCol, i, j);
                                changeTurn();
                                changeText();
                                invalidate();
                                return true;
                            }

                            else{
                                //Log.d(TAG, "gets here");
                                board[srcRow][srcCol].selection = false;
                                invalidate();
                                return true;
                            }


                        }

                    }
                }

            }

            invalidate();

        }

        return true;
    }

    public static void changeTurn(){
        if(playerTurn==true){
            playerTurn=false;
            return;
        }
        if(playerTurn==false){
            playerTurn=true;
            return;
        }

    }

    public void changeText(){
        if(playerTurn==true){
            getRootView().findViewById(R.id.whiteTextView).setVisibility(VISIBLE);
            getRootView().findViewById(R.id.blackTextView).setVisibility(INVISIBLE);
            return;
        }
        if(playerTurn==false){
            getRootView().findViewById(R.id.whiteTextView).setVisibility(INVISIBLE);
            getRootView().findViewById(R.id.blackTextView).setVisibility(VISIBLE);
            return;
        }
    }


    public static boolean canCapture(int currX, int currY, int newX, int newY){
        if(board[newY][newX].getPiece().getName().equals("")){
            return false;
        }
        if(board[currY][currX].getPiece().getPlayer() == board[newY][newX].getPiece().getPlayer()){
            return false;
        }
        return true;
    }


    public static boolean noObstacles(Piece piece, int currX, int currY, int newX, int newY){
        //Log.d(TAG, "no obstacles");
        //Pawn
        if(piece.getName().equals("wp") || piece.getName().equals("bp")){
            int dirX = newX>currX ? 1 : -1;
            int dirY = newY>currY ? 1 : -1;
            if(Math.abs(newY-currY) != 0){
                return true;
            }
            for (int i=1;i<=Math.abs(newX-currX);i++) {
                if (!board[currX+i*dirX][currY].getPiece().getName().equals("")) {
                    return false;

                }
            }

            return true;
        }


        //Rook
        if(piece.getName().equals("wR") || piece.getName().equals("bR") || piece.getName().equals("bK") || piece.getName().equals("wK") ){
            int dirX = newX>currX ? 1 : -1;
            int dirY = newX>currY ? 1 : -1;
            if(Math.abs(newY-currY) != 0){
                for (int i=1;i <= Math.abs(newY-currY)-1;i++) {
                    if (!board[currX][currY+i*dirY].getPiece().getName().equals("")) {
                        return false;

                    }
                }
            }
            if(Math.abs(newX-currX) != 0){
                for (int i=1;i<=Math.abs(newX-currX)-1;i++) {
                    if (!board[currX+i*dirX][currY].getPiece().getName().equals("")) {
                        return false;

                    }
                }
                return true;
            }

            return true;

        }


        //Bishop
        if(piece.getName().equals("wB") || piece.getName().equals("bB")){
            int dirX = newX>currX ? 1 : -1;
            int dirY = newY>currY ? 1 : -1;
            for (int i=1;i <= Math.abs(newY-currY)-1;i++) {
                if (!board[currX+i*dirX][currY+i*dirY].getPiece().getName().equals("")) {
                    return false;
                }
            }
            return true;
        }

        //Queen
        if(piece.getName().equals("wQ") || piece.getName().equals("bQ")){
            int dirX = newX>currX ? 1 : -1;
            int dirY = newY>currY ? 1 : -1;
            //vertical movement
            if(Math.abs(newX-currX) == 0){
                for (int i=1;i <= Math.abs(newY-currY)-1;i++) {
                    if (!board[currX][currY+i*dirY].getPiece().getName().equals("")) {
                        return false;

                    }
                }
                return true;
            }

            //horizontal movement
            if(Math.abs(newY-currY) == 0){
                for (int i=1;i <= Math.abs(newX-currX)-1;i++) {
                    if (!board[currX+i*dirX][currY].getPiece().getName().equals("")) {
                        return false;

                    }
                }
                return true;
            }

            //diagonal movement
            for (int i=1;i <= Math.abs(newY-currY)-1;i++) {
                if (!board[currX+i*dirX][currY+i*dirY].getPiece().getName().equals("")) {
                    return false;
                }

            }

            return true;
        }



        //this is the end
        return true;
    }

    public static boolean castling(Piece piece, int currX, int currY, int newX, int newY){

        int dirX = newX>currX ? 1 : -1;
        int distance = Math.abs(newX-currX);
        //Log.d(TAG, "dirx "+dirX);
        if(distance!=2){
            return false;
        }

        if(newY-currY!=0){
            return false;
        }

        if(piece.getName().equals("wK") || piece.getName().equals("bK")){
            ;
        }
        else{
            return false;
        }


        if(piece.gethasMoved() == true){
            return false;
        }

        //if(!playerTurn){
          //  return false;
        //}



        if(playerTurn == false){
            if(distance*dirX == 2){
                //Log.d(TAG, "here2 ");
                if( !board[7][7].getPiece().getName().equals("") && board[7][7].getPiece().getName().equals("bR") && board[7][7].getPiece().gethasMoved()==false){
                    if(noObstacles(board[7][7].getPiece(), 7, 7, 7, 4)){
                        board[newY][newX].setPiece(board[currY][currX].getPiece());
                        board[currY][currX].setPiece(new Placeholder());
                        board[7][5].setPiece(board[7][7].getPiece());
                        board[7][7].setPiece(new Placeholder());
                        return true;
                    }
                }
            }

            if(distance*dirX == -2){
                //Log.d(TAG, "here");
                if( !board[7][0].getPiece().getName().equals("") && board[7][0].getPiece().getName().equals("bR") && board[7][0].getPiece().gethasMoved()==false){
                    if(noObstacles(board[7][0].getPiece(), 7, 0, 7, 4)){
                        Log.d(TAG, "hello");
                        board[newY][newX].setPiece(board[currY][currX].getPiece());
                        board[currY][currX].setPiece(new Placeholder());
                        board[7][3].setPiece(board[7][0].getPiece());
                        board[7][0].setPiece(new Placeholder());
                        return true;
                    }
                }
            }

        }

        if(playerTurn == true){
            if(distance*dirX == 2){
                //Log.d(TAG, "here2 ");
                if( !board[0][7].getPiece().getName().equals("") && board[0][7].getPiece().getName().equals("wR") && board[0][7].getPiece().gethasMoved()==false){
                    if(noObstacles(board[0][7].getPiece(), 0, 7, 0, 4)){
                        board[newY][newX].setPiece(board[currY][currX].getPiece());
                        board[currY][currX].setPiece(new Placeholder());
                        board[0][5].setPiece(board[0][7].getPiece());
                        board[0][7].setPiece(new Placeholder());
                        return true;
                    }
                }
            }

            if(distance*dirX == -2){
                Log.d(TAG, "here");
                if( !board[0][0].getPiece().getName().equals("") && board[0][0].getPiece().getName().equals("wR") && board[0][0].getPiece().gethasMoved()==false){
                    if(noObstacles(board[0][0].getPiece(), 0, 0, 0, 4)){
                        Log.d(TAG, "hello");
                        board[newY][newX].setPiece(board[currY][currX].getPiece());
                        board[currY][currX].setPiece(new Placeholder());
                        board[0][3].setPiece(board[0][0].getPiece());
                        board[0][0].setPiece(new Placeholder());
                        return true;
                    }
                }
            }

        }


        return false;
    }

    public static boolean check(boolean player){


        if(player==false){

            Coordinate blkKing = getKingLocation(false);


            for (int i = 0; i<8;i++){
                for (int j = 0; j<8;j++){
                    Coordinate piece = new Coordinate(i,j);
                    if(!board[i][j].getPiece().getName().equals("") && board[i][j].getPiece().getPlayer()==true){
                        if(board[i][j].getPiece().validMove(j, i, blkKing.getY(), blkKing.getX(), canCapture(j, i, blkKing.getY(), blkKing.getX()))){
                            if(noObstacles(board[i][j].getPiece(), i, j, blkKing.getX(),blkKing.getY())){

                                return true;
                            }
                        }
                    }
                }
            }

            return false;
        }

        if(player==true){

            Coordinate whtKing = getKingLocation(true);


            for (int i = 0; i<8;i++){
                for (int j = 0; j<8;j++){
                    Coordinate piece = new Coordinate(i,j);
                    if(!board[i][j].getPiece().getName().equals("") && board[i][j].getPiece().getPlayer()==false){
                        if(board[i][j].getPiece().validMove(j, i, whtKing.getY(), whtKing.getX(), canCapture(j, i, whtKing.getY(), whtKing.getX()))){
                            if(noObstacles(board[i][j].getPiece(), i, j, whtKing.getX(),whtKing.getY())){

                                return true;
                            }
                        }
                    }
                }
            }

            return false;
        }

        return false;

    }



    public static Coordinate getKingLocation(boolean player){

        Coordinate location = new Coordinate(0,0);

        if(player==true){
            for (int i = 0; i<8;i++){
                for (int j = 0; j<8;j++){
                    if(board[i][j]!= null && board[i][j].getPiece().getName().equals("wK")){
                        location = new Coordinate(i,j);
                        return location;

                    }
                }
            }
        }
        else{
            for (int i = 0; i<8;i++){
                for (int j = 0; j<8;j++){
                    if(board[i][j]!= null && board[i][j].getPiece().getName().equals("bK")){
                        location = new Coordinate(i,j);
                        return location;
                    }
                }
            }
        }

        return location;

    }


    public void getData(int currX, int currY, int newX, int newY){
        Log.d(TAG, "added"+currX+""+currY+""+newX+""+newY);
        Move data = new Move(board[currX][currY].getPiece(), currX, currY, newX, newY);

        movements.add(data);

    }





}
