package com.example.grocerylist;

import android.content.Context;
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
    //todo: I don't quite understand how this works, unsure if it works - steven
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // If convertView is null, inflate the layout
            convertView = inflater.inflate(R.layout.groitem_layout, parent, false);
        }
        //sets the height of the gridview items to half the height of the gridview so that we can get two items per row, 4 per screen
        convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height/2));
        // Find the TextView in the inflated layout and set its text
        TextView itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
        ImageView itemImageView = convertView.findViewById(R.id.itemImageView);

        // Find the item at the index
        GroItem groItem = getItem(i);

        // Set the text of the TextView to the name of the item
        itemNameTextView.setText(groItem.getName());
        //gets the id of the drawable resource that corresponds to the image name
        int drawableResourceId = context.getResources().getIdentifier(groItem.getImageName(), "drawable", context.getPackageName());
        //itemImageView.setImageResource(R.drawable.item_yogurt);
        itemImageView.setImageResource(drawableResourceId);


        return convertView;
    }
}
