package com.restaurant;

import java.util.ArrayList;

public class Coupons {

    public ArrayList<Coupon> list;

    public Coupons(){
        list = new ArrayList<>();
    }

    public void addCoupon(String name, double discount){
        Coupon deal = new Coupon(name, discount);
        list.add(deal);
    }
    public void removeCoupon(String name, double discount){
        list.removeIf(c -> c.getName().equalsIgnoreCase(name) && c.getDiscount() == discount);

    }

}
