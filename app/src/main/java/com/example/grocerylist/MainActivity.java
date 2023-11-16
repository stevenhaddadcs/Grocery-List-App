package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.grocerylist.model.GroList;
import com.example.grocerylist.model.GroItem;

import java.util.ArrayList;
import java.util.List;


//allows us to use new android features on older devices (extends fragment activity too)
//might be removed in the future
public class MainActivity extends AppCompatActivity {

    //used to store the grocery list, will store items and their corresponding image file which will be displayed in the gridview
    GroList groList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a list of grocery items
        ArrayList<GroItem> g = new ArrayList<GroItem>();
        g.add(new GroItem("yogurt", "item_yogurt"));
        g.add(new GroItem("bananas", "item_bananas"));
        g.add(new GroItem("broccoli", "item_broccoli"));
        g.add(new GroItem("carrots", "item_carrots"));
        //instantiate the grocery list
        groList = new GroList(g);

        //find the gridview in the layout
        GridView gridView = findViewById(R.id.gridView);
        //create the adapter for the gridview
        GroListAdapter adapter = new GroListAdapter(this, groList);
        //set the adapter for the gridview
        gridView.setAdapter(adapter);
    }
}