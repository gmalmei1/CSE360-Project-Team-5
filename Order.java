package com.restaurant;

public class Order {

    public Customer user;
    public int waitTime;
    public int position;
    public boolean completed;

    public Order(Customer user, int position, int waitTime){
        this.user = user;
        this.position = position;
        this.waitTime = waitTime;
        completed = false;
    }

    //returns the waitTime in minutes based on the given order

    public static int calcWaitTime(Order order){
        //if the order's user or cart is invalid, return 0;
        if (order.user == null || order.user.cart == null) return 0;

        //for each item in the cart, add the quantity * timeToMake one item to the time
        int time = 0;
        for (CartItem currentItem : order.user.cart.menuItems){
            time += currentItem.getItem().getTimeToMake() * currentItem.getQuantity();
        }
        return time;
    }

    public void resetCart(){
        if (completed){
            this.user.cart = new Cart();
        }
    }

}
