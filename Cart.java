package com.restaurant;

import java.util.ArrayList;

public class Cart {

    public ArrayList<CartItem> menuItems;
    public ArrayList<Coupon> coupons;

    private double tip;
    private double totalPrice;

    private boolean validSelection;

    public Cart(){

        menuItems = new ArrayList<>();
        coupons = new ArrayList<>();

        tip = 0;
        totalPrice = 0;

        validSelection = false;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public double getTip(){
        return tip;
    }

    public boolean checkCartValidity(){
        return validSelection;
    }

    public void updateTotalPrice(){

        //if there are no items in the cart, then the customer cannot order:

        //NOTE: it's designed in the removeFromCart() method that an item is
        //completely removed from the cart if the resulting quantity after
        //reduction == 0. Therefore, this function only needs to check if the
        //cart has at least 1 unique item inside to be a valid selection

        validSelection = !(menuItems.isEmpty());


        //reset totalPrice count
        totalPrice = 0;

        // for each menu item in cart, if it has a quantity greater than 0, add price to total
        for (CartItem myItem: menuItems){
            if (myItem.getQuantity() > 0){
                totalPrice += myItem.getItem().getPrice() * myItem.getQuantity();
            }
        }

        //for each coupon in cart, subtract discount from total
        for (Coupon myItem: coupons){
            totalPrice -= myItem.getDiscount();
        }

        //can't pay negative money (in case the discounts applied go over the actual items)
        if (totalPrice <= 0){
            totalPrice = 0;
        }
    }

    public void setTip(double tip){
        if (tip <= 0){
            tip = 0;
        }
        this.tip = tip;
    }

    public void addToCart(MenuItem chosenItem, int chosenQuantity){

        //error checking before updating cart
        if (chosenQuantity <= 0 || chosenItem == null) return;

        // before creating a new cart item, check if the item is already in the cart
        // and if so, then simply update the quantity of the existing item. If not,
        // then create a new CartItem with the menuItem and respective quantity

        boolean found = false;
        for (CartItem myItem: menuItems){
            if (!found && myItem.getItem().getName().equalsIgnoreCase(chosenItem.getName())){
                myItem.appendQuantity(chosenQuantity);
                found = true;
            }
        }

        if (!found) menuItems.add(new CartItem(chosenItem, chosenQuantity));

    }

    public void removeFromCart(MenuItem chosenItem, int chosenQuantity){

        //error checking before updating cart
        if (chosenQuantity <= 0 || chosenItem == null) return;

        // check if the chosen item to remove exists in the cart. Once found by
        // comparing names, safely reduce the quantity unless the user got rid of
        // all instances of the item, in which the item is removed entirely

        for (CartItem myItem : menuItems){
            if (myItem.getItem().getName().equalsIgnoreCase(chosenItem.getName())) {
                myItem.reduceQuantity(chosenQuantity);
                menuItems.removeIf(m -> myItem.getQuantity() == 0);
                break;
            }
        }
    }

    public void addDiscount(Coupon chosenCoupon){

        if (chosenCoupon == null) return;

        boolean found = false;
        for (Coupon myCoupon: coupons){
            if (myCoupon.getName().equalsIgnoreCase(chosenCoupon.getName())) {
                found = true;
                break;
            }
        }
        if (!found) coupons.add(chosenCoupon);

    }

    public void removeDiscount(Coupon chosenCoupon){

        //error checking before updating cart
        if (chosenCoupon == null) return;

        //remove discount if the names match
        coupons.removeIf(myItem -> myItem.getName().equalsIgnoreCase(chosenCoupon.getName()));
    }

}
