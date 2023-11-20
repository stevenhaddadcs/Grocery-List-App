package com.example.grocerylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grocerylist.model.GroItem;
import com.example.grocerylist.model.GroItemMap;

public class ItemActivity extends AppCompatActivity {
    private ImageView image;
    private TextView title;
    private Button increment;
    private Button decrement;
    private Button saveButton;
    private TextView item_quantity;
    private int quantity;
    private String name;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_layout);
        image = findViewById(R.id.item_image);
        title = findViewById(R.id.item_name);
        increment = findViewById(R.id.button_increment);
        decrement = findViewById(R.id.button_decrement);
        item_quantity = findViewById(R.id.item_quantity);
        saveButton = findViewById(R.id.button_save);

        //receive and store from MainActivity
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        quantity = intent.getIntExtra("quantity", 0);

        //set image id from drawable
        GroItemMap map = new GroItemMap();
        int imageId = getResources().getIdentifier(map.getImageName(name), "drawable", getPackageName());
        image.setImageResource(imageId);

        title.setText(name);
        item_quantity.setText(""+quantity);

        //onClick increment button (+) will increment the quantity
        increment.setOnClickListener(view -> {
            quantity++;
            item_quantity.setText("" + quantity);
        });

        //onClick decrement button (-) will decrement the quantity
        decrement.setOnClickListener(view -> {
            if(quantity == 1){
                //if decrement quantity at 1, exit and send info back to MainActivity
                quantity--;
                Bundle b = new Bundle();
                b.putString("name", name);
                b.putInt("quantity", quantity);
                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                resultIntent.putExtras(b);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }else{
                quantity--;
                item_quantity.setText("" + quantity);
            }

        });

        //onClick save button (Save) will exit and send info back to MainActivity
        saveButton.setOnClickListener(view -> {
            Bundle b = new Bundle();
            b.putString("name", name);
            b.putInt("quantity", quantity);
            Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
            resultIntent.putExtras(b);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

    }


}
