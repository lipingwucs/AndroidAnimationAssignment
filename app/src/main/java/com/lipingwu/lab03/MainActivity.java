package com.lipingwu.lab03;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    String[] exercises = new String[]{"exercise1", "exercise2", "exercise3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exercises);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent newActivity =null;
        switch(position){
            case 0:
                newActivity = new Intent(this, Ex01Activity.class);
                break;
            case 1:
                newActivity = new Intent(this, Ex02Activity.class);
                break;
            case 2:
                newActivity = new Intent(this, Ex03Activity.class);
            }
        startActivity(newActivity);
        }



}