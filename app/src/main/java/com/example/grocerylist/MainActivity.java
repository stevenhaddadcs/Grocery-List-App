package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;


//allows us to use new android features on older devices (extends fragment activity too)
//might be removed in the future
public class MainActivity extends AppCompatActivity {

    //used to store the grocery list, will store items and their corresponding image file which will be displayed in the gridview
    GroList groList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groList = new GroList(this);
        setContentView(R.layout.activity_main);
    }
}