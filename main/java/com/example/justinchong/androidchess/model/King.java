package com.example.justinchong.androidchess.model;

import java.io.Serializable;

/**
 * Data Structure for king piece
 * @author justinchong
 *
 */
public class King extends Piece implements Serializable{
	
	public King(boolean player){
			
		if(player==true){
			this.setPlayer(true);
			this.setName("wK");
		}
		else{
			this.setPlayer(false);
			this.setName("bK");
		}
	}
		
	public boolean validMove(int srcX, int srcY, int destX, int destY, boolean capture){
		
		int movex;
		int movey;
		
		movex = Math.abs(destX - srcX);
		movey = Math.abs(destY - srcY);
		
		if(movey == 0 && movex == 1 || movey == 1 && movex == 0){
		 sethasMoved(true);
		return true;
		}
		else if(movey == movex){
			if(movey == 1 && movex == 1){
				sethasMoved(true);
				return true;
			}
		}
		return false;
	}
}
