package com.example.justinchong.androidchess.model;

import android.util.Log;

import com.example.justinchong.androidchess.GameActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by justinchong on 5/2/17.
 */

public class Record implements Serializable {

    public ArrayList<Game> gameList;


    public Record() {
        gameList = new ArrayList<Game>();
    }


    public static void write() throws IOException {


    }

    /**
     * @param
     * @throws IOException
     */
    public static void write(Record list) throws IOException {
        try {
            Log.d(TAG, "write method");
            FileOutputStream fileOut = new FileOutputStream(GameActivity.newFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }


    }


    public static Record read() throws IOException, ClassNotFoundException {


        try {
            FileInputStream fileIn = new FileInputStream(GameActivity.newFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Record list = (Record) in.readObject();
            in.close();
            fileIn.close();
            return list;
        } catch (IOException i) {
            i.printStackTrace();

        }

        return null;
    }


}
