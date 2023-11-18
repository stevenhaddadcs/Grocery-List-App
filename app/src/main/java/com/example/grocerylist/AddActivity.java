package com.example.grocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.GridView;

import com.example.grocerylist.model.GroList;
import com.example.grocerylist.model.GroItem;
import com.example.grocerylist.model.GroItemMap;
import com.example.grocerylist.model.GroItem;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    GroList grolist = new GroList();
    GroList itemColl = new GroList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_layout);

        //Receiving list from MainActivity
        Intent intent = getIntent();
        grolist = intent.getParcelableExtra("grolist");

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
        GridView gridView = findViewById(R.id.gridView);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);


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
                GroListAdapter adapter = new GroListAdapter(AddActivity.this, itemColl, height);
                // set the adapter for the gridview
                gridView.setAdapter(adapter);
            }
        });



    }

}
