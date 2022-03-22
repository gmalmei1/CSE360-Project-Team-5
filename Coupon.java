package com.restaurant;

public class Coupon {

    private String name;
    private double discount;

    public Coupon(String name, double discount){
        this.name = name;
        this.discount = discount;
    }

    public String getName(){
        return name;
    }

    public double getDiscount(){
        return discount;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

}
