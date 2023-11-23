package com.example.grocerylist;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocerylist.model.GroList;
import com.example.grocerylist.model.GroItem;
import com.example.grocerylist.model.GroItemMap;

import java.util.ArrayList;
import java.util.Locale;


//allows us to use new android features on older devices (extends fragment activity too)
//might be removed in the future
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    final static int CLEAR = 0;


    //used to store the grocery list, will store items and their corresponding image file which will be displayed in the gridview
    GroList groList;

    //used for detecting shaking
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    float currentAcceleration, previousAcceleration;

    //used for text to speech
    private TextToSpeech tts;

    //List of all item's names in grocery list
    private ArrayList<String> listNames = new ArrayList<>();

    private ActivityResultLauncher<Intent> launcher;
    private ActivityResultLauncher<Intent> launcher2;

    private GridView gridView;

    private TextView listEmpty;
    private GroListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a list of grocery items
        ArrayList<GroItem> g = new ArrayList<>();
        GroItemMap map = new GroItemMap();
        g.add(new GroItem("Yogurt", map.getImageName("Yogurt"),5));
        g.add(new GroItem("Bananas", map.getImageName("Bananas")));
        g.add(new GroItem("Broccoli", map.getImageName("Broccoli"),2));
        g.add(new GroItem("Apples", map.getImageName("Apples"),6));
        g.add(new GroItem("Cereal", map.getImageName("Cereal")));
        g.add(new GroItem("Chicken Breasts", map.getImageName("Chicken Breasts")));
        g.add(new GroItem("Chips", map.getImageName("Chips"),2));

        //Populate ArrayList with names of all items in current grocery list
        for(int i = 0; i <= g.size() - 1; i++){
            listNames.add(g.get(i).getName());
        }
        //instantiate the grocery list
        groList = new GroList(g);
        Log.i("MYDEBUG", "Grocery List size: "+ this.groList.length());
        for(GroItem i : groList.getItems()){
            i.incrementQuantity();
        }

        // find the gridview and framelayout in the layout
        gridView = findViewById(R.id.gridView);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        listEmpty = findViewById(R.id.list_empty);
        listEmpty.setVisibility(View.INVISIBLE);

        if(groList.length() == 0){
            listEmpty.setVisibility(View.VISIBLE);
        }

        //this segment is needed because the framelayout is not drawn on the screen yet,
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
                adapter = new GroListAdapter(MainActivity.this, groList, height);
                // set the adapter for the gridview
                gridView.setAdapter(adapter);

                Log.i("MYDEBUG", adapter.getItem(0).getName());
            }
        });

        gridView.setOnItemClickListener(this::onItemClick);
        gridView.setOnItemLongClickListener(this::onItemLongClick);

        //Receives results back and adds new item to the grocery list.
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //checks to see if returned results are val
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            //Transfers received arraylist into addNames
                            ArrayList<String> addNames = data.getStringArrayListExtra("items");
                            boolean flag;

                            if(groList.length() == 0){
                                listEmpty.setVisibility(View.INVISIBLE);
                            }
                            //iterates through addNames to check if item(s) are already in the current grocery list
                            for(int i = 0; i <= addNames.size() -1; i++) {
                                flag = false;
                                //if item is already in the grocery list, increments the quantity of item
                                for (GroItem j : groList.getItems()) {
                                    if (j.getName().equals(addNames.get(i))) {
                                        j.incrementQuantity();
                                        flag = true;
                                    }
                                }
                                //if item is new, adds to grocery list.
                                if (!flag) {
                                    GroItem temp = new GroItem(addNames.get(i), map.getImageName(addNames.get(i)));
                                    temp.incrementQuantity();
                                    groList.addItem(temp);
                                    listNames.add(addNames.get(i));
                                    flag = false;
                                }
                            }
                            //For adding new item image onto grocery list.
                            int height = gridView.getHeight();
                            adapter = new GroListAdapter(MainActivity.this, groList, height);
                            gridView.setAdapter(adapter);
                            //updated current grocery list
                            Log.i("MYDEBUG", "Current Grocery List:");
                            for (int i = 0; i <= groList.length() - 1; i++) {
                                Log.i("MYDEBUG", groList.getItemAtIndex(i).getName());
                            }
                        }
                    }
                });

        //receive results back and set the quantity of the item
        //if quantity is 0, item is deleted from GroList and toast will appear
        launcher2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            String name = data.getStringExtra("name");
                            int quantity = data.getIntExtra("quantity", 0);
                            boolean flag = false;
                            int num = 0;

                            for(GroItem i: groList.getItems()){
                                if(i.getName().equals(name)){
                                    if(quantity != 0){
                                        i.setQuantity(quantity);
                                    }else{
                                        flag = true;

                                    }
                                }
                            }

                            if(flag){
                                for(GroItem i:groList.getItems()) {
                                    if (i.getName().equals(name)){
                                        groList.removeItem(i);
                                        Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                }
                            }
                            //For adding new item image onto grocery list.
                            int height = gridView.getHeight();
                            adapter = new GroListAdapter(MainActivity.this, groList, height);
                            gridView.setAdapter(adapter);
                            if(groList.length() == 0){
                                listEmpty.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });


        // to access device's sensor of accelerometer sensor type.
        // to register a SensorEventListener for the accelerometer sensor at a given sensor rate.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        //Earth's gravity = 9.81 m/s^2
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        previousAcceleration = SensorManager.GRAVITY_EARTH;

        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });


        //OnClickListner to start AddActivity
        //Sends an arraylist of Strings with names of items in current grocery list.
        com.google.android.material.floatingactionbutton.FloatingActionButton addBtn = findViewById(R.id.add_item);
        addBtn.setOnClickListener(view -> {
            Intent myIntent = new Intent(getApplicationContext(), AddActivity.class);
            myIntent.putStringArrayListExtra("listNames", listNames);
            launcher.launch(myIntent);
        });

    }




    /*
       currentAcceleration calculates total acceleration with 3 axes from the formula below
           https://physics.stackexchange.com/questions/41653/how-do-i-get-the-total-acceleration-from-3-axes
       if acceleration (difference) reads 9.81 m/s^2, this means no shaking occurs
    */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0]; //acceleration - Gx on the x-axis
        float y = sensorEvent.values[1]; //acceleration - Gy on the y-axis
        float z = sensorEvent.values[2]; //acceleration - Gz on the z-axis

        double ax = x * x;
        double ay = y * y;
        double az = z * z;

        currentAcceleration = (float) Math.sqrt(ax + ay + az);

        float difference = currentAcceleration - previousAcceleration;

        // if shake is detected
        // speak the text provided and QUEUE_FLUSH to replace all the entries in the playback queue with a new entry
        // 20 is determined from trial and error for to prevent unintended shake from activating speech
        if (difference > 20) {
            Log.i("MYDEBUG", "Acceleration : " + difference);
            tts.speak(groList.toString(), TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    /*
       On click of item in MainActivity, item's name and quantity is sent to ItemActivity
     */
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent myIntent = new Intent(getApplicationContext(), ItemActivity.class);
        myIntent.putExtra("name", groList.getItemAtIndex(position).getName());
        myIntent.putExtra("quantity", groList.getItemAtIndex(position).getQuantity());
        launcher2.launch(myIntent);
    }

    private boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l){
        if(groList.getItemAtIndex(position).getChecked()){
            groList.getItemAtIndex(position).setChecked(false);
        }else{
            GroItem item = groList.getItemAtIndex(position);
            item.setChecked(true);

            for(GroItem i: groList.getItems()){
                if(i.getName() == item.getName()){
                    groList.removeItem(i);
                    break;
                }
            }

            groList.addItem(item);
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    // setup an Options menu
    //from Demo_Settings
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(CLEAR, CLEAR, CLEAR, "Clear");
        return true;
    }

    // handle a "Clear" selection in the options menu by calling showClearAlertDialog method
    // from Demo_Settings
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case CLEAR:
                showClearAlertDialog();

                return true;
        }
        return false;
    }

    /*
        using AlertDialog to set confirmation on whether to clear the items on grocery list
        setPositiveButton to clear items
        setNegativeButton to cancel and do nothing
     */
    private void showClearAlertDialog() {
        AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle("Are you sure you want to clear?")
                .setPositiveButton("ok", (dialog, which) -> {
                    groList.clear();
                    int height = gridView.getHeight();
                    GroListAdapter adapter = new GroListAdapter(MainActivity.this, groList, height);
                    gridView.setAdapter(adapter);
                    listEmpty.setVisibility(View.VISIBLE);
                } )
                .setNegativeButton("cancel", (dialog, which) -> {} )
                .create();
        alert.show();
    }

    /*
            this method must be declared from SensorEventListener
         */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        // text to speech stop when open different view
        if(tts != null){
            tts.stop();
        }

        //unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //register the Sensor Manager onResume
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //text to speech must be shutdown when done using it
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
    }
}
