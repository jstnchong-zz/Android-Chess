package com.example.justinchong.androidchess.model;


import android.util.Log;

import java.io.Serializable;

/**
 * Data Structure for pawn piece
 * @author justinchong
 *
 */
public class Pawn extends Piece implements Serializable{

	private static final String TAG = Pawn.class.getSimpleName();
	
	public Pawn(boolean player){
		
		
		if(player==true){
			this.setPlayer(true);
			this.setName("wp");
		}
		else{
			this.setPlayer(false);
			this.setName("bp");
		}	
	}
	
	public boolean validMove(int srcX, int srcY, int destX, int destY, boolean capture){
		
		int dirX;
		int dirY;
	
		if(getPlayer()==false){
			dirY = srcY - destY;
			dirX = srcX - destX;

			
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
		
		if(getPlayer()==true){
			dirY = destY - srcY;
			dirX = destX - srcX;

			Log.d(TAG, "dirx: "+dirX+"dirY: "+dirY);

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

	
	/*if(getPlayer()==true){
			if(hasMoved==false && currCoordinate.getX()==newCoordinate.getX() && currCoordinate.getY()==newCoordinate.getY()+2){
				sethasMoved(true);
				return true;
			}
			
			if(hasMoved==false && currCoordinate.getX()==newCoordinate.getX() && currCoordinate.getY()==newCoordinate.getY()+1){
				sethasMoved(true);
				return true;
			}
			
			if(hasMoved==true && currCoordinate.getX()==newCoordinate.getX() && currCoordinate.getY()==newCoordinate.getY()+1){
				sethasMoved(true);
				return true;
			}
			
			
		}
		
		if(getPlayer()==false){
			if(hasMoved==false && currCoordinate.getX()==newCoordinate.getX() && currCoordinate.getY()==newCoordinate.getY()-2){
				
				sethasMoved(true);
				return true;
			}
			
			if(hasMoved==false && currCoordinate.getX()==newCoordinate.getX() && currCoordinate.getY()==newCoordinate.getY()-1){
				sethasMoved(true);
				return true;
			}
			
			if(hasMoved==true && currCoordinate.getX()==newCoordinate.getX() && currCoordinate.getY()==newCoordinate.getY()-1){
				sethasMoved(true);
				return true;
			}
			
			
		}
		*/
}
