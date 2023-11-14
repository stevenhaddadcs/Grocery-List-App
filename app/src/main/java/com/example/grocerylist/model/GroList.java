package com.example.grocerylist.model;

import java.util.Collections;
import java.util.List;

public class GroList {
    private List<GroItem> items;
    private String name;

    public GroList(List<GroItem> items, String name){
        this.items = items;
        this.name = name;
    }

    public GroList(List<GroItem> items){
        this.items = items;
        //names it after the first item in the list
        this.name = items.get(0).getName();
    }
    public GroList() {
        this.items = null;
        this.name = "new list";
    }
    public List<GroItem> getItems(){
        return items;
    }

    public GroItem getItemAtIndex(int i){
        return items.get(i);
    }

    public void setItems(List<GroItem> items){
        this.items = items;
    }
    public void addItem(GroItem item){
        items.add(item);
    }

    public void addItems(List<GroItem> items){
        this.items.addAll(items);
    }
    public void removeItem(GroItem item){
        items.remove(item);
    }
    public int length(){
        return items.size();
    }

}
