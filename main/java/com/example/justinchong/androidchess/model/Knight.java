package com.example.justinchong.androidchess.model;

import java.io.Serializable;

/**
 * Data Structure for Knight piece
 * @author justinchong
 *
 */

public class Knight extends Piece implements Serializable{
	
	public Knight(boolean player){
		
		if(player==true){
			this.setPlayer(true);
			this.setName("wN");
		}
		else{
			this.setPlayer(false);
			this.setName("bN");
		}
	}
	
	public boolean validMove(int srcX, int srcY, int destX, int destY, boolean capture){
		
		int movex;
		int movey;
		
		movex = Math.abs(destX - srcX);
		movey = Math.abs(destY - srcY);
		
		if(movey == 1 && movex == 2 || movey == 2 && movex ==1){
			return true;
		}
		return false;
	}

}


