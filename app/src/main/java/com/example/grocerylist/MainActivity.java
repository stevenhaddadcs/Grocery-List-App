package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

        // find the gridview and linearlayout in the layout
        GridView gridView = findViewById(R.id.gridView);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);

        //this segment is needed because the linearlayout is not drawn on the screen yet,
        // so we need to wait until it is drawn to get the height,
        // which will be used to set the height of the gridview items
        //https://stackoverflow.com/questions/18861585/get-content-view-size-in-oncreate
        // set up a global layout listener to wait until the linearlayout
        // (which contains the gridview) is drawn on the screen
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // remove the listener to avoid multiple calls for EVERY layout pass
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // now we can get the height for the drawn gridview
                int height = gridView.getHeight();
                Log.i("MYDEBUG", "LinearLayout height: " + linearLayout.getHeight());
                Log.i("MYDEBUG", "GridView height: " + gridView.getHeight());

                // create the adapter for the gridview send the height of the gridview to the adapter
                GroListAdapter adapter = new GroListAdapter(MainActivity.this, groList, height);
                // set the adapter for the gridview
                gridView.setAdapter(adapter);
            }
        });
    }
}