package com.example.justinchong.androidchess.model;

import java.io.Serializable;

/**
 * Data Structure for rook piece
 * @author matthewreyes
 *
 */

public class Rook extends Piece implements Serializable{
	
	public Rook(boolean player){
		
		if(player==true){
			this.setPlayer(true);
			this.setName("wR");
		}
		else{
			this.setPlayer(false);
			this.setName("bR");
		}	
	}
	/**
	 *  Checks if the Rook can move to the new location
	 */
	public boolean validMove(int srcX, int srcY, int destX, int destY, boolean capture){
		
		int movex;
		int movey;
		
		movex = Math.abs(destX - srcX);
		movey = Math.abs(destY - srcY);
		
		if(movey == 0 && movex != 0 || movey != 0 && movex == 0){
			sethasMoved(true);
			return true;
			
		}
		return false;
	}

}

