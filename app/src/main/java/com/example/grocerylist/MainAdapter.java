package com.example.grocerylist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
//gridview tutorial https://abhiandroid.com/ui/gridview#gsc.tab=0

public class MainAdapter extends BaseAdapter {

    Context context;
    int items[];

    //layout inflater could be added here if necessary
    public MainAdapter(Context context, int items[]){
        this.context = context;
        this.items = items;
    }

    //todo
    @Override
    public int getCount() {
        return 0;
    }
    //todo
    @Override
    public Object getItem(int i) {
        return null;
    }
    //todo
    @Override
    public long getItemId(int i) {
        return 0;
    }
    //todo
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
