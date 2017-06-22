package com.example.justinchong.androidchess.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by justinchong on 5/2/17.
 */

public class Game implements Serializable {

    private String gameTitle;
    private String date;
    private ArrayList<Move> moveList;
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public Game(String title, ArrayList<Move> moveList){
        this.gameTitle = title;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        this.date = dateFormat.format(cal.getTime());
        this.moveList = moveList;
    }

    public String getGameTitle(){
        return this.gameTitle;
    }
    public String getDate(){
        return this.date;
    }
    public ArrayList<Move> getTileList(){
        return this.moveList;
    }
}
