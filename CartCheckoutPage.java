package com.restaurant;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class CartCheckoutPage extends BorderPane {

    public CartCheckoutPage(){

        Label page = new Label("Welcome to Cart/Checkout Page");
        this.setTop(page);

        // load an image
        String filePath = "C:/Users/ianwo/IdeaProjects/restaurant/pictures/vbuck.jpg";

        Image defaultSkin = new Image(filePath);

        ImageView imageView = new ImageView(defaultSkin);

        //imageView.setX(50);
        //imageView.setY(25);

        imageView.setFitHeight(300);
        imageView.setFitWidth(500);

        imageView.setPreserveRatio(true);

        this.setCenter(imageView);

    }
}
