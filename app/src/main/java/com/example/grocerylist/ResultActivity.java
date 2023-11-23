package com.example.grocerylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grocerylist.model.GroItem;
import com.example.grocerylist.model.GroItemMap;
import com.example.grocerylist.model.GroList;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private ArrayList<String> resultList;
    private GroList itemColl;
    private GridView gridView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MYDEBUG", "onCreate ResultActivity");
        setContentView(R.layout.activity_result_layout);
        TextView resultText = findViewById(R.id.resultText);

        // receive a string array of the names of the items searched
        Intent intent = getIntent();
        resultList = intent.getStringArrayListExtra("result");

        // add the names of the items into GroList
        ArrayList<GroItem> g = new ArrayList<>();
        GroItemMap map = new GroItemMap();
        if(resultList != null && resultList.size() != 0){
            for(String i: resultList){
                g.add(new GroItem(i, map.getImageName(i)));
            }
        }
        // find the gridview and linearlayout in the layout
        gridView = findViewById(R.id.gridView);
        linearLayout = findViewById(R.id.linearLayout);

        if(g == null || g.isEmpty()){
            resultText.setText("Item not found");
        }else{
            resultText.setText("Item found: " + g.size());

            itemColl = new GroList(g);        //create a list of all available items

            //this segment is needed because the framelayout is not drawn on the screen yet,
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
                    GroListAdapter adapter = new GroListAdapter(ResultActivity.this, itemColl, height);
                    // set the adapter for the gridview
                    gridView.setAdapter(adapter);
                }
            });

            //When item is selected, OnClickListener is triggered.
            //onItemClick() is invoked.
            gridView.setOnItemClickListener(this::onItemClick);
        }
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // return to AddActivity class with selected item
        Bundle b = new Bundle();
        b.putString("item", itemColl.getItemAtIndex(position).getName());
        Intent i = new Intent(getApplicationContext(), AddActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //all of the other activities on top of it will be closed
        i.putExtras(b);
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}
