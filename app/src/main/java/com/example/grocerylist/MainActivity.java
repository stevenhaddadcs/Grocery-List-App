package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.grocerylist.model.GroList;
import com.example.grocerylist.model.GroItem;
import com.example.grocerylist.model.GroItemMap;

import java.util.ArrayList;



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
        ArrayList<GroItem> g = new ArrayList<>();
        GroItemMap map = new GroItemMap();
        //adding items to the list just to test out how the gridview looks
        g.add(new GroItem("Yogurt", map.getImageName("Yogurt")));
        g.add(new GroItem("Bananas", map.getImageName("Bananas")));
        g.add(new GroItem("Broccoli", map.getImageName("Broccoli")));
        g.add(new GroItem("Carrots", map.getImageName("Carrots")));
        g.add(new GroItem("Cereal", map.getImageName("Cereal")));
        g.add(new GroItem("Chicken Breasts", map.getImageName("Chicken Breasts")));
        g.add(new GroItem("Chips", map.getImageName("Chips")));
        //instantiate the grocery list with the list of items
        groList = new GroList(g);

        // find the gridview and linearlayout in the layout
        GridView gridView = findViewById(R.id.gridView);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);

                //this segment is needed because the linearlayout is not drawn on the screen yet,
                // so we need to wait until it is drawn to get the height,
                // which will be used to set the height of the gridview items
                //https://stackoverflow.com/questions/18861585/get-content-view-size-in-oncreate
                // set up a global layout listener to wait until the linearlayout
                // (which contains the gridview) is drawn on the screen
                frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        // remove the listener to avoid multiple calls for EVERY layout pass
                        frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        // now we can get the height for the drawn gridview
                        int height = gridView.getHeight();
                        Log.i("MYDEBUG", "LinearLayout height: " + frameLayout.getHeight());
                        Log.i("MYDEBUG", "GridView height: " + gridView.getHeight());

                        // create the adapter for the gridview send the height of the gridview to the adapter
                        GroListAdapter adapter = new GroListAdapter(MainActivity.this, groList, height);
                        // set the adapter for the gridview
                        gridView.setAdapter(adapter);
                    }
                });
    }

    public void addClick(View v){
        startActivity(new Intent(MainActivity.this, AddActivity.class));
    }
}
