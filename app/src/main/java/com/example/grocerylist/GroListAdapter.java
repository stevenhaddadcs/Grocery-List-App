package com.example.grocerylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.grocerylist.model.GroItem;
import com.example.grocerylist.model.GroList;
//gridview tutorial https://abhiandroid.com/ui/gridview#gsc.tab=0
//recycler view https://developer.android.com/develop/ui/views/layout/recyclerview

public class GroListAdapter extends BaseAdapter {

    private Context context;
    private GroList items;
    private LayoutInflater inflater;


    public GroListAdapter(Context context, GroList items){
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
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
        return -1;
    }
    //todo: I don't quite understand how this works, unsure if it works - steven
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            //LayoutInflater is used to create instances of the layout views that will be used for each item in the GridView.
            convertView = inflater.inflate(R.layout.groitem_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //find the item at the index
        GroItem groItem = items.getItemAtIndex(i);
        //set the text of the textview to the name of the item
        viewHolder.itemNameTextView.setText(groItem.getName());

        return convertView;
    }

    //ViewHolder Pattern: The ViewHolder pattern is used to store references to the child views of the item layout.
    // This avoids the need to call findViewById repeatedly for the same views:
    private static class ViewHolder {
        TextView itemNameTextView;
    }
}
