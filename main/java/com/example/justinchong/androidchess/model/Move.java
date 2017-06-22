package com.example.justinchong.androidchess.model;

import java.io.Serializable;

/**
 * Created by justinchong on 5/2/17.
 */

public class Move implements Serializable {

    public Piece piece;

    public int srcRow;
    public int srcCol;
    public int destRow;
    public int destCol;


    public Move(Piece piece, int srcRow, int srcCol, int destRow, int destCol){
        this.piece = piece;
        this.srcRow = srcRow;
        this.srcCol = srcCol;
        this.destRow = destRow;
        this.destCol = destCol;
    }

}
