package com.example.grocerylist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//the activity that will be used to test whether or not the user is visually impaired so we have a quantifiable metric for our research method
public class ReadingTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test);
    }
}
