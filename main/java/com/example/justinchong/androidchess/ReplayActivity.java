package com.example.justinchong.androidchess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ReplayActivity extends AppCompatActivity {


    private ListView list;
    public static ArrayList<String> replayList;
    private ArrayList<String> dates;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);

        View view = new View(this);

        final Button sortTitle = (Button) findViewById(R.id.titleButton);
        final Button sortDate = (Button) findViewById(R.id.datebutton);
        list = (ListView)findViewById(R.id.gameListView);

        replayList = new ArrayList<String>();
        dates = new ArrayList<String>();

        for(int i = 0; i<GameActivity.replayGames.gameList.size(); i++){
            String info = "Title: "+GameActivity.replayGames.gameList.get(i).getGameTitle()+ "\n"+"Date: " +GameActivity.replayGames.gameList.get(i).getDate();
            replayList.add(info);
            dates.add(GameActivity.replayGames.gameList.get(i).getDate());
        }


        final ArrayAdapter<String> replayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,replayList);

        list.setAdapter(replayAdapter);

        if(GameActivity.replayGames.gameList.isEmpty()){
            Toast.makeText(view.getContext(), "There are no games recorded", Toast.LENGTH_SHORT).show();
        }



        //list by title
        sortTitle.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                if(!replayList.isEmpty()){
                    Collections.sort(replayList, String.CASE_INSENSITIVE_ORDER);
                    replayAdapter.notifyDataSetChanged();
                }


            }
        });

        //list by date
        sortDate.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                if(!replayList.isEmpty()){
                    Collections.sort(dates);
                    ArrayList<String> listByDate = new ArrayList<String>();
                    for(int i=0;i<dates.size();i++){
                        for(int j=0; j<replayList.size();j++){
                            if(replayList.get(j).contains(dates.get(i))){
                                listByDate.add(replayList.get(j));
                            }
                        }
                    }

                    replayList.clear();
                    for(int i=0; i<listByDate.size();i++){
                        replayList.add(listByDate.get(i));
                    }
                    replayAdapter.notifyDataSetChanged();
                }


            }
        });


    }







}
