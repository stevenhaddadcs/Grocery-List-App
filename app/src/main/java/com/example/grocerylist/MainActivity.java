package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.grocerylist.model.GroList;
import com.example.grocerylist.model.GroItem;


//allows us to use new android features on older devices (extends fragment activity too)
//might be removed in the future
public class MainActivity extends AppCompatActivity {

    //used to store the grocery list, will store items and their corresponding image file which will be displayed in the gridview
    GroList groList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instantiate the grocery list
        groList = new GroList();
        //find the gridview in the layout
        GridView gridView = findViewById(R.id.gridView);
        //create the adapter for the gridview
        GroListAdapter adapter = new GroListAdapter(this, groList);
        //set the adapter for the gridview
        gridView.setAdapter(adapter);
        setContentView(R.layout.activity_main);
    }
}