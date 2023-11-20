package com.example.grocerylist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.example.grocerylist.model.GroList;
import com.example.grocerylist.model.GroItem;
import com.example.grocerylist.model.GroItemMap;

import java.util.ArrayList;
import java.util.regex.Pattern;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AddActivity extends AppCompatActivity {

    //list to contain names of all items in current grocery list.
    ArrayList<String> listnames = new ArrayList<>();

    ArrayList<String> addNames = new ArrayList<>();

    //list to contain all items available for user to add.
    GroList itemColl = new GroList();

    Button doneButton;

    GridView gridView;
    FrameLayout frameLayout;

    //used for speech-to-text
    private SpeechRecognizer speechRecognizer;
    private RecognitionListener r;
    private Intent iSpeechRecognizer;
    private ArrayList<String> voiceResults;
    private ArrayList<String> storeSearchResult;
    private ImageButton searchButton;
    private EditText searchText;

    ActivityResultLauncher<Intent> launcher;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_layout);

        //Receiving list from MainActivity
        Intent intent = getIntent();
        listnames = intent.getStringArrayListExtra("listNames");
        if(listnames == null){
            Log.i("MYDEBUG", "empty");
        }
        else{
            for (int i = 0; i <= listnames.size() - 1; i++) {
                Log.i("MYDEBUG", listnames.get(i));
            }
        }

        //Displaying list of available items to add
        ArrayList<GroItem> g = new ArrayList<>();
        GroItemMap map = new GroItemMap();
        g.add(new GroItem("All-Purpose Cleaner", map.getImageName("All-Purpose Cleaner")));
        g.add(new GroItem("Almond Milk", map.getImageName("Almond Milk")));
        g.add(new GroItem("Aluminum Foil", map.getImageName("Aluminum Foil")));
        g.add(new GroItem("Apple Juice", map.getImageName("Apple Juice")));
        g.add(new GroItem("Apples", map.getImageName("Apples")));
        g.add(new GroItem("Avocados", map.getImageName("Avocados")));
        g.add(new GroItem("Baby Food", map.getImageName("Baby Food")));
        g.add(new GroItem("Baby Formula", map.getImageName("Baby Formula")));
        g.add(new GroItem("Baby Wipes", map.getImageName("Baby Wipes")));
        g.add(new GroItem("Bacon", map.getImageName("Bacon")));
        g.add(new GroItem("Bagels", map.getImageName("Bagels")));
        g.add(new GroItem("Baking Powder", map.getImageName("Baking Powder")));
        g.add(new GroItem("Baking Soda", map.getImageName("Baking Soda")));
        g.add(new GroItem("Bananas", map.getImageName("Bananas")));
        g.add(new GroItem("Band-Aids", map.getImageName("Band-Aids")));
        g.add(new GroItem("Basil", map.getImageName("Basil")));
        g.add(new GroItem("BBQ Sauce", map.getImageName("BBQ Sauce")));
        g.add(new GroItem("Beer", map.getImageName("Beer")));
        g.add(new GroItem("Bell Peppers", map.getImageName("Bell Peppers")));
        g.add(new GroItem("Black Pepper", map.getImageName("Black Pepper")));
        g.add(new GroItem("Body Wash", map.getImageName("Body Wash")));
        g.add(new GroItem("Bread", map.getImageName("Bread")));
        g.add(new GroItem("Bread Crumbs", map.getImageName("Bread Crumbs")));
        g.add(new GroItem("Broccoli", map.getImageName("Broccoli")));
        g.add(new GroItem("Broth", map.getImageName("Broth")));
        g.add(new GroItem("Butter", map.getImageName("Butter")));
        g.add(new GroItem("Caesar Salad Dressing", map.getImageName("Caesar Salad Dressing")));
        g.add(new GroItem("Canned Beans", map.getImageName("Canned Beans")));
        g.add(new GroItem("Canned Corn", map.getImageName("Canned Corn")));
        g.add(new GroItem("Canned Soup", map.getImageName("Canned Soup")));
        g.add(new GroItem("Canned Tomatoes", map.getImageName("Canned Tomatoes")));
        g.add(new GroItem("Canned Tuna", map.getImageName("Canned Tuna")));
        g.add(new GroItem("Canned Vegetables", map.getImageName("Canned Vegetables")));
        g.add(new GroItem("Carrots", map.getImageName("Carrots")));
        g.add(new GroItem("Cat Food", map.getImageName("Cat Food")));
        g.add(new GroItem("Cat Litter", map.getImageName("Cat Litter")));
        g.add(new GroItem("Cereal", map.getImageName("Cereal")));
        g.add(new GroItem("Cheese Slices", map.getImageName("Cheese Slices")));
        g.add(new GroItem("Chicken Breasts", map.getImageName("Chicken Breasts")));
        g.add(new GroItem("Chips", map.getImageName("Chips")));
        g.add(new GroItem("Cinnamon", map.getImageName("Cinnamon")));
        g.add(new GroItem("Coconut Milk", map.getImageName("Coconut Milk")));
        g.add(new GroItem("Coffee", map.getImageName("Coffee")));
        g.add(new GroItem("Cold Medicine", map.getImageName("Cold Medicine")));
        g.add(new GroItem("Conditioner", map.getImageName("Conditioner")));
        g.add(new GroItem("Cottage Cheese", map.getImageName("Cottage Cheese")));
        g.add(new GroItem("Crackers", map.getImageName("Crackers")));
        g.add(new GroItem("Croissants", map.getImageName("Croissants")));
        g.add(new GroItem("Cucumbers", map.getImageName("Cucumbers")));
        g.add(new GroItem("Cupcakes", map.getImageName("Cupcakes")));
        g.add(new GroItem("Curry Paste", map.getImageName("Curry Paste")));
        g.add(new GroItem("Deli Meats", map.getImageName("Deli Meats")));
        g.add(new GroItem("Diapers", map.getImageName("Diapers")));
        g.add(new GroItem("Dish Soap", map.getImageName("Dish Soap")));
        g.add(new GroItem("Dog Food", map.getImageName("Dog Food")));
        g.add(new GroItem("Dog Treats", map.getImageName("Dog Treats")));
        g.add(new GroItem("Eggs", map.getImageName("Eggs")));
        g.add(new GroItem("Feta Cheese", map.getImageName("Feta Cheese")));
        g.add(new GroItem("Flour", map.getImageName("Flour")));
        g.add(new GroItem("Frozen Chicken Nuggets", map.getImageName("Frozen Chicken Nuggets")));
        g.add(new GroItem("Frozen Dinner", map.getImageName("Frozen Dinner")));
        g.add(new GroItem("Frozen Fruits", map.getImageName("Frozen Fruits")));
        g.add(new GroItem("Frozen Pizza", map.getImageName("Frozen Pizza")));
        g.add(new GroItem("Frozen Veggies", map.getImageName("Frozen Veggies")));
        g.add(new GroItem("Garlic Powder", map.getImageName("Garlic Powder")));
        g.add(new GroItem("Granola Bars", map.getImageName("Granola Bars")));
        g.add(new GroItem("Grapes", map.getImageName("Grapes")));
        g.add(new GroItem("Ground Beef", map.getImageName("Ground Beef")));
        g.add(new GroItem("Hand Sanitizer", map.getImageName("Hand Sanitizer")));
        g.add(new GroItem("Hummus", map.getImageName("Hummus")));
        g.add(new GroItem("Ice Cream", map.getImageName("Ice Cream")));
        g.add(new GroItem("Italian Salad Dressing", map.getImageName("Italian Salad Dressing")));
        g.add(new GroItem("Jam", map.getImageName("Jam")));
        g.add(new GroItem("Ketchup", map.getImageName("Ketchup")));
        g.add(new GroItem("Laundry Detergent", map.getImageName("Laundry Detergent")));
        g.add(new GroItem("Lettuce", map.getImageName("Lettuce")));
        g.add(new GroItem("Maple Syrup", map.getImageName("Maple Syrup")));
        g.add(new GroItem("Mayonnaise", map.getImageName("Mayonnaise")));
        g.add(new GroItem("Milk", map.getImageName("Milk")));
        g.add(new GroItem("Mozzarella Cheese", map.getImageName("Mozzarella Cheese")));
        g.add(new GroItem("Muffins", map.getImageName("Muffins")));
        g.add(new GroItem("Mustard", map.getImageName("Mustard")));
        g.add(new GroItem("Nuts", map.getImageName("Nuts")));
        g.add(new GroItem("Oatmeal", map.getImageName("Oatmeal")));
        g.add(new GroItem("Oats", map.getImageName("Oats")));
        g.add(new GroItem("Olive Oil", map.getImageName("Olive Oil")));
        g.add(new GroItem("Olives", map.getImageName("Olives")));
        g.add(new GroItem("Onion Powder", map.getImageName("Onion Powder")));
        g.add(new GroItem("Orange Juice", map.getImageName("Orange Juice")));
        g.add(new GroItem("Oranges", map.getImageName("Oranges")));
        g.add(new GroItem("Oregano", map.getImageName("Oregano")));
        g.add(new GroItem("Pain Relievers", map.getImageName("Pain Relievers")));
        g.add(new GroItem("Pancake Mix", map.getImageName("Pancake Mix")));
        g.add(new GroItem("Paper Towels", map.getImageName("Paper Towels")));
        g.add(new GroItem("Paprika", map.getImageName("Paprika")));
        g.add(new GroItem("Pasta", map.getImageName("Pasta")));
        g.add(new GroItem("Pasta Sauce", map.getImageName("Pasta Sauce")));
        g.add(new GroItem("Pickles", map.getImageName("Pickles")));
        g.add(new GroItem("Plastic Wrap", map.getImageName("Plastic Wrap")));
        g.add(new GroItem("Popcorn", map.getImageName("Popcorn")));
        g.add(new GroItem("Pork Chops", map.getImageName("Pork Chops")));
        g.add(new GroItem("Potatoes", map.getImageName("Potatoes")));
        g.add(new GroItem("Pretzels", map.getImageName("Pretzels")));
        g.add(new GroItem("Quinoa", map.getImageName("Quinoa")));
        g.add(new GroItem("Red Wine", map.getImageName("Red Wine")));
        g.add(new GroItem("Rice", map.getImageName("Rice")));
        g.add(new GroItem("Rice Noodles", map.getImageName("Rice Noodles")));
        g.add(new GroItem("Salmon", map.getImageName("Salmon")));
        g.add(new GroItem("Salsa", map.getImageName("Salsa")));
        g.add(new GroItem("Salt", map.getImageName("Salt")));
        g.add(new GroItem("Sausages", map.getImageName("Sausages")));
        g.add(new GroItem("Shampoo", map.getImageName("Shampoo")));
        g.add(new GroItem("Shrimp", map.getImageName("Shrimp")));
        g.add(new GroItem("Soda", map.getImageName("Soda")));
        g.add(new GroItem("Sour Cream", map.getImageName("Sour Cream")));
        g.add(new GroItem("Soy Milk", map.getImageName("Soy Milk")));
        g.add(new GroItem("Soy Sauce", map.getImageName("Soy Sauce")));
        g.add(new GroItem("Spinach", map.getImageName("Spinach")));
        g.add(new GroItem("Spirits", map.getImageName("Spirits")));
        g.add(new GroItem("Sriracha Sauce", map.getImageName("Sriracha Sauce")));
        g.add(new GroItem("Strawberries", map.getImageName("Strawberries")));
        g.add(new GroItem("Sugar", map.getImageName("Sugar")));
        g.add(new GroItem("Tea", map.getImageName("Tea")));
        g.add(new GroItem("Toilet Paper", map.getImageName("Toilet Paper")));
        g.add(new GroItem("Tomato Sauce", map.getImageName("Tomato Sauce")));
        g.add(new GroItem("Tomatoes", map.getImageName("Tomatoes")));
        g.add(new GroItem("Toothpaste", map.getImageName("Toothpaste")));
        g.add(new GroItem("Tortillas", map.getImageName("Tortillas")));
        g.add(new GroItem("Trash Bags", map.getImageName("Trash Bags")));
        g.add(new GroItem("Turkey", map.getImageName("Turkey")));
        g.add(new GroItem("Vanilla Extract", map.getImageName("Vanilla Extract")));
        g.add(new GroItem("Vegetable Oil", map.getImageName("Vegetable Oil")));
        g.add(new GroItem("Vinegar", map.getImageName("Vinegar")));
        g.add(new GroItem("Vitamins", map.getImageName("Vitamins")));
        g.add(new GroItem("Water", map.getImageName("Water")));
        g.add(new GroItem("White Wine", map.getImageName("White Wine")));
        g.add(new GroItem("Yogurt", map.getImageName("Yogurt")));
        g.add(new GroItem("Ziplock Bags", map.getImageName("Ziplock Bags")));

        //instantiate the collection list
        itemColl = new GroList(g);        //create a list of all available items
        
        // find the gridview and linearlayout in the layout
        gridView = findViewById(R.id.gridView);
        frameLayout = findViewById(R.id.frameLayout);
        
        //this segment is needed because the framelayout is not drawn on the screen yet,
        // so we need to wait until it is drawn to get the height,
        // which will be used to set the height of the gridview items
        //https://stackoverflow.com/questions/18861585/get-content-view-size-in-oncreate
        // set up a global layout listener to wait until the framelayout
        // (which contains the gridview) is drawn on the screen
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // remove the listener to avoid multiple calls for EVERY layout pass
                frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // now we can get the height for the drawn gridview
                int height = gridView.getHeight();
                Log.i("MYDEBUG", "FrameLayout height: " + frameLayout.getHeight());
                Log.i("MYDEBUG", "GridView height: " + gridView.getHeight());

                // create the adapter for the gridview send the height of the gridview to the adapter
                GroListAdapter adapter = new GroListAdapter(AddActivity.this, itemColl, height);
                // set the adapter for the gridview
                gridView.setAdapter(adapter);
            }
        });
        //When item is selected, OnClickListener is triggered.
        //onItemClick() is invoked.
        gridView.setOnItemClickListener(this::onItemClick);

        //Receive result back from ResultActivity class on the item selected
        //and send it to MainActivity
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            String item = data.getStringExtra("item");
                            addNames.add(item);
                        }
                    }
                });

        //initialize Buttons and EditText
        searchButton = findViewById(R.id.search_button);
        searchText = findViewById(R.id.searchbar);
        doneButton = findViewById(R.id.done_button);

        //to store items searched with voice-to-text
        voiceResults = new ArrayList<>();

        // check if the phone allow permission to access microphone
        // https://stackoverflow.com/questions/43464678/why-record-audio-is-returning-permission-granted-everytime-in-marshmallow
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkAudioPermission();
        }

        //create speech recognizer and recognizer intent
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        iSpeechRecognizer = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        iSpeechRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en"); //language english as preference
        iSpeechRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        iSpeechRecognizer.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5); //allow max of 5 results that matches the voice

        //initialize and set recognition listener
        //used for receiving notifications from the SpeechRecognizer when the recognition related events occur
        r = new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
            }
            @Override
            public void onBeginningOfSpeech() {
            }
            @Override
            public void onRmsChanged(float v) {
            }
            @Override
            public void onBufferReceived(byte[] bytes) {
            }
            @Override
            public void onEndOfSpeech() {
            }
            @Override
            public void onError(int i) {
                Log.i("MYDEBUG", "Error listening for speech: " + i);
            }

            @Override
            public void onResults(Bundle bundle) {
                //get results from the speech with max number of 5
                voiceResults = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                searchItems(); //search for the items found in speech-to-text
            }

            @Override
            public void onPartialResults(Bundle bundle) {
            }
            @Override
            public void onEvent(int i, Bundle bundle) {
            }
        };

        speechRecognizer.setRecognitionListener(r);

        // set on touch listener for the microphone button
        // when pressed gesture starts, speech recognizer starts listening
        // when pressed gesture finishes, speech recognizer stop listening
        searchButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                switch(e.getAction()){
                    case MotionEvent.ACTION_UP:
                        speechRecognizer.stopListening();
                        searchItems();
                        break;

                    case MotionEvent.ACTION_DOWN:
                        speechRecognizer.startListening(iSpeechRecognizer);
                        searchText.setHint("Listening...");
                }
                return false;
            }
        });

        //called when search action is performed
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(v);
                    return true;
                }
                return false;
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(addNames == null){
                    finish();
                }
                Bundle b = new Bundle();
                b.putStringArrayList("items", addNames);
                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                for(int i = 0; i <= addNames.size() -1; i++){
                    Log.i("MYDEBUG", addNames.get(i));
                }
                resultIntent.putExtras(b);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

    }

    /*
    when search action is performed, a set of items that matches the name searched will be sent
     */
    private void performSearch(TextView v){
        storeSearchResult = new ArrayList<>();

        for(GroItem item: itemColl.getItems()){
            if(Pattern.compile(Pattern.quote(v.getText().toString().replaceAll("[-+.^:,]"," ")), Pattern.CASE_INSENSITIVE).matcher(item.getName().replaceAll("[-+.^:,]"," ")).find()){
                if(!storeSearchResult.contains(item.getName()))
                    storeSearchResult.add(item.getName());
            }
        }

        getResult(storeSearchResult);
    }



    /*
        try to match the 5 results generated from speech recognition with itemColl that contains all the items that is available for the user to add
     */
    private void searchItems(){
        storeSearchResult = new ArrayList<>();

        if(voiceResults != null && itemColl != null){
            for(String s : voiceResults){
                if(s.length()>2){
                for(GroItem item: itemColl.getItems()){
                    if(Pattern.compile(Pattern.quote(s.substring(0, Math.min(s.length(), 7))), Pattern.CASE_INSENSITIVE).matcher(item.getName().replaceAll("[-+.^:,]"," ")).find()){
                        if(!storeSearchResult.contains(item.getName()))
                            storeSearchResult.add(item.getName());}}}}
        }

        if(storeSearchResult.isEmpty()){
            Log.i("MYDEBUG", "Item not found ");
            searchText.setHint("Item not found");
        }else{
            for(String i : storeSearchResult){
                Log.i("MYDEBUG", "Search item: " + i);
            }

            if(storeSearchResult.size() <= 1){
                searchText.setHint("Item found: " + storeSearchResult.size());
            }else{
                searchText.setHint("Items found: " + storeSearchResult.size());
            }

           getResult(storeSearchResult);
        }
    }

    /*
        Sends an arraylist of Strings with names of items that matches the search list
        and receive from that ResultActivity class
     */
    private void getResult(ArrayList<String> result) {
        Intent myIntent = new Intent(getApplicationContext(), ResultActivity.class);
        myIntent.putStringArrayListExtra("result", result);
        launcher.launch(myIntent);
    }

    /*
        request for permissions to record audio
    */
    private void checkAudioPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);

    }

    /*
        to check if all the permissions are granted, toast will appear to show permission is denied or granted
        according to stackoverflow: https://stackoverflow.com/questions/38260175/android-onrequestpermissionsresult-grantresults-size-1
        it is not recommended to check the first permission only as user might allow first permission and denied the rest
    */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0 ) {
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED)
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

        Toast.makeText(this,"Permission Granted", Toast.LENGTH_SHORT).show();
    }


    //On click of item in gridView, item is bundled and sent to grocery list in MainActivity
    // Fixed: quantity increased when add item
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        addNames.add(itemColl.getItemAtIndex(position).getName());
        v.setBackgroundColor(Color.GRAY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        speechRecognizer.setRecognitionListener(r);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //destroy when not in use
        if(speechRecognizer != null){
            speechRecognizer.destroy();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //destroy when not in use
        if(speechRecognizer != null){
            speechRecognizer.destroy();
        }
    }
}
