package com.example.justinchong.androidchess.model;

import java.io.Serializable;

/**
 * Data Structure for queen piece
 * @author matthewreyes
 *
 */

public class Bishop extends Piece implements Serializable {
	
	public Bishop(boolean player){
		
		if(player==true){
			this.setPlayer(true);
			this.setName("wB");
		}
		else{
			this.setPlayer(false);
			this.setName("bB");
		}
	}
	/**
	 *  Checks if the Bishop can move to the new location
	 */
	public boolean validMove(int srcX, int srcY, int destX, int destY, boolean capture){
		
		int movex;
		int movey;
		
		movex = Math.abs(destX - srcX);
		movey = Math.abs(destY - srcY);
		
		if(movey == movex){
			return true;
		}
		return false;
	}
}
