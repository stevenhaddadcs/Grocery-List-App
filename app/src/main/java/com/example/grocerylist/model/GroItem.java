package com.example.grocerylist.model;

//Grocery item class used to keep track of the items in the grocery list
public class GroItem {
    //display name of the item
    private String name;
    //imageName file name
    private String imageName;
    private int quantity;
    private boolean check;

    //If the user does not specify how many of the same item
    public GroItem(String name, String image){
        this.name = name;
        this.imageName = image;
        this.quantity = 0;
        this.check = false;
    }

    //If the user specifies how many of the same item
    public GroItem(String name, String image, int quantity){
        this.name = name;
        this.imageName = image;
        this.quantity = quantity;
        this.check = false;
    }

    public String getName(){
        return name;
    }

    public String getImageName(){
        return imageName;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getQuantityString(){
        return Integer.toString(quantity);
    }
    public String toString(){
        return quantity + "" +name;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int decrementQuantity(){
        if(quantity > 0){
            quantity--;
        }
        return quantity;
    }

    public int incrementQuantity(){
        quantity++;
        return quantity;
    }

    public void setChecked(boolean b) {
        this.check = b;
    }

    public boolean getChecked(){
        return this.check;
    }
}
