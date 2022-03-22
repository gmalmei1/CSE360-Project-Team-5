package com.restaurant;

import javafx.scene.image.Image;

public class MenuItem {
    private String name;
    private double price;
    private String type;
    private String description;
    private int timeToMake;
    private Image picture;

    public MenuItem(String name, double price, String type, String description, int timeToMake, Image picture){
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.timeToMake = timeToMake;
        this.picture = picture;
    }

    // getters
    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public String getType(){
        return type;
    }

    public String getDescription(){
        return description;
    }

    public int getTimeToMake() { return timeToMake; }

    public Image getPicture(){
        return picture;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setTimeToMake(int timeToMake) { this.timeToMake = timeToMake; }

    public void setPicture(Image picture){
        this.picture = picture;
    }



}
