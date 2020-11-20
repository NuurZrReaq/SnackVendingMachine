package com.freightosassignment;

public class Snack {
    private String name;
    private double price;
    private int quantity;


    public Snack(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    //Decrement the quantity when buying, returns the new quantity.
    public int decrementQuantity(){
        if(this.quantity == 0)
            return this.quantity;
        this.quantity = this.quantity -1;
        return this.quantity;
    }
}
