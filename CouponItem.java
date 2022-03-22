package com.restaurant;

public class CouponItem {

    private Coupon item;
    private int quantity;

    public CouponItem(Coupon item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public Coupon getItem(){
        return item;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setItem(Coupon item){
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

}
