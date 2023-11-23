package com.example.grocerylist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grocerylist.model.GroItem;
import com.example.grocerylist.model.GroList;
//gridview tutorial https://abhiandroid.com/ui/gridview#gsc.tab=0
//recycler view https://developer.android.com/develop/ui/views/layout/recyclerview

public class GroListAdapter extends BaseAdapter {

    private Context context;
    private GroList items;
    private LayoutInflater inflater;
    private int height;


    public GroListAdapter(Context context, GroList items, int height){
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(this.context);
        this.height = height;
    }

    @Override
    public int getCount() {
        return items.length();
    }
    @Override
    public GroItem getItem(int i) {
        return items.getItemAtIndex(i);
    }
    // TODO: not sure if necessary at all
    @Override
    public long getItemId(int i) {
        return i;
    }


    public void setItems(GroList items) {
        this.items = items;
    }

    //changed name to itemView from convertView
    @Override
    public View getView(int i, View itemView, ViewGroup parent) {
        if (itemView == null) {
            // If itemView is null, inflate the layout
            itemView = inflater.inflate(R.layout.groitem_layout, parent, false);
        }
        //sets the height of the gridview items to half the height of the gridview so that we can get two items per row, 4 per screen
        itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height/2));
        // Find the TextView in the inflated layout and set its text
        TextView itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
        TextView itemQuantityTextView = itemView.findViewById(R.id.itemQuantityTextView);
        ImageView itemImageView = itemView.findViewById(R.id.itemImageView);

        // Find the item at the index
        GroItem groItem = getItem(i);

        // Set the text of the TextView to the name of the item
        itemNameTextView.setText(groItem.getName());
        itemQuantityTextView.setText(groItem.getQuantityString());
        //gets the id of the drawable resource that corresponds to the image name
        int drawableResourceId = context.getResources().getIdentifier(groItem.getImageName(), "drawable", context.getPackageName());
        //itemImageView.setImageResource(R.drawable.item_yogurt);
        itemImageView.setImageResource(drawableResourceId);

        if(groItem.getChecked()){
            itemView.setBackgroundColor(Color.GRAY);
        }else{
            itemView.setBackgroundColor(Color.TRANSPARENT);

        }

        return itemView;
    }
}
