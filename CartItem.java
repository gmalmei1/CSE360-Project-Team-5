package com.restaurant;

public class CartItem {

    private MenuItem item;
    private int quantity;

    public CartItem(MenuItem item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem(){
        return item;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setItem(MenuItem item){
        this.item = item;
    }

    public void addQuantity(){
        quantity++;
    }

    public boolean removeQuantity(){
        if (quantity > 0){
            quantity--;
            return true;
        }
        return false;
    }

    public void appendQuantity(int quantity){
        this.quantity += quantity;
    }

    public void reduceQuantity(int quantity){
        double pendingQuantity = this.quantity - quantity;
        if (pendingQuantity > 0){
            this.quantity -= quantity;
        }
    }

}
