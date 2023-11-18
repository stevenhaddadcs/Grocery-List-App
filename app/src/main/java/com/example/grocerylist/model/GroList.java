package com.example.grocerylist.model;

//Parcelable tutorial https://code.tutsplus.com/how-to-pass-data-between-activities-with-android-parcelable--cms-29559t
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;

//Implements Parcelable to transfer Lists between MainActivity and AddActivity
public class GroList implements Parcelable {
    private ArrayList<GroItem> items;

    private final String name;

    public GroList(ArrayList<GroItem> items, String name){
        this.items = items;
        this.name = name;
    }

    public GroList(ArrayList<GroItem> items){
        this.items = items;
        //names it after the first item in the list
        this.name = items.get(0).getName();
    }
    public GroList() {
        this.items = new ArrayList<GroItem>();
        this.name = "new list";
    }

    /* constructor is used to read the transferred data back from the serialized Parcel.
     * This method will be called on the receiving activity to collect the data.
     */
    protected GroList(Parcel in) {
        name = in.readString();
    }

    /* The Parcelable API will look for this field when it needs to deserialize
     * an instance of your class that has been passed to another component.
     */
    //It's job is to generate instances of your Parcelable class from a Parcel using the parcel data provided.
    //The creator calls the constructor you defined above, passing it a Parcel object, and the constructor initializes the class attributes.
    public static final Creator<GroList> CREATOR = new Creator<GroList>() {
        @Override
        public GroList createFromParcel(Parcel in) {
            return new GroList(in);
        }

        @Override
        public GroList[] newArray(int size) {
            return new GroList[size];
        }
    };

    public List<GroItem> getItems(){
        return items;
    }

    public GroItem getItemAtIndex(int i){
        return items.get(i);
    }

    public void setItems(ArrayList<GroItem> items){
        this.items = items;
    }
    public void addItem(GroItem item){
        items.add(item);
    }

    public void addItems(ArrayList<GroItem> items){
        this.items.addAll(items);
    }
    public void removeItem(GroItem item){
        items.remove(item);
    }
    public int length(){
        return items.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // This method will be passed a Parcel instance which has a number of write methods that you can use to add each field to the parcel.
    //only need to pass name to compare current item list to GroItemMap in order to see what is already on the list.
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
