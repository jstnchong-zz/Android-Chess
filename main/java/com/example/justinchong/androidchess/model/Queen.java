package com.example.justinchong.androidchess.model;

import java.io.Serializable;

/**
 * Data Structure for queen piece
 * @author matthewreyes
 *
 */

public class Queen extends Piece implements Serializable{
	public Queen(boolean player){
		
		if(player==true){
			this.setPlayer(true);
			this.setName("wQ");
		}
		else{
			this.setPlayer(false);
			this.setName("bQ");
		}
	}
	/**
	 *  Checks if the Queen can move to the new location
	 */
	public boolean validMove(int srcX, int srcY, int destX, int destY, boolean capture){

		int movex;
		int movey;
		
		movex = Math.abs(destX - srcX);
		movey = Math.abs(destY - srcY);
		
		if(movey == 0 && movex != 0 || movey != 0 && movex == 0){
			return true;
		}
		else if(movey == movex){
			return true;
		}
		return false;
	}

}
