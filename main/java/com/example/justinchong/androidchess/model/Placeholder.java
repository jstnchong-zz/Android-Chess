package com.example.justinchong.androidchess.model;


import java.io.Serializable;

/**
 * Data Structure for pawn piece
 * @author justinchong
 *
 */
public class Placeholder extends Piece implements Serializable{

    public Placeholder(){
        this.setName("");
    }

    public boolean validMove(int srcX, int srcY, int destX, int destY, boolean capture){

        int dirX;
        int dirY;

        if(getPlayer()==true){
            dirY = srcY - destY;
            dirX = srcX - destY;

            if(dirY == 1 && dirX == 0){

                this.sethasMoved(true);
                return true;
            }
            if(dirY == 2 && dirX==0 && gethasMoved()==false){
                this.sethasMoved(true);
                return true;
            }
            if(dirY == 1 && Math.abs(dirX) == 1 && capture==true){
                this.sethasMoved(true);
                return true;
            }



        }

        if(getPlayer()==false){
            dirY = destY - srcY;
            dirX = destX - srcY;

            if(dirY == 1 && dirX == 0){
                this.sethasMoved(true);
                return true;
            }
            else if(dirY == 2 && dirX==0 && gethasMoved()==false){
                this.sethasMoved(true);
                return true;
            }
            if(dirY == 1 && Math.abs(dirX) == 1 && capture==true){
                this.sethasMoved(true);
                return true;
            }

        }

        return false;


    }

}