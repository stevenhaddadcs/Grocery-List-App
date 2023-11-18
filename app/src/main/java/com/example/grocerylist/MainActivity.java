package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.example.grocerylist.model.GroList;
import com.example.grocerylist.model.GroItem;
import com.example.grocerylist.model.GroItemMap;

import java.util.ArrayList;
import java.util.Locale;


//allows us to use new android features on older devices (extends fragment activity too)
//might be removed in the future
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //used to store the grocery list, will store items and their corresponding image file which will be displayed in the gridview
    GroList groList;

    //used for detecting shaking
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    float currentAcceleration, previousAcceleration;

    //used for text to speech
    private TextToSpeech tts;
    private String text = "";

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

        // to access device's sensor of accelerometer sensor type.
        // to register a SensorEventListener for the accelerometer sensor at a given sensor rate.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        //Earth's gravity = 9.81 m/s^2
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        previousAcceleration = SensorManager.GRAVITY_EARTH;

        Log.i("MYDEBUG", "" + currentAcceleration);


        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });

        int count = 0;
        for(GroItem list : this.groList.getItems()){
            text += list.getName();
            if(count < this.groList.length()-1){
                text += ", ";
            }
            count ++;
        }

    }

    public void addClick(View v) {
        startActivity(new Intent(MainActivity.this, AddActivity.class));
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
        // 20 is determined from trial and error for ideal shaking
        if (difference > 20) {
            Log.i("MYDEBUG", "Acceleration : " + difference);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
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
