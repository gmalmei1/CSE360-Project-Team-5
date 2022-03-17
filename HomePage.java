package com.restaurant;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class HomePage extends BorderPane {

    // declare variables

    public Label primary;
    public Font secondaryFont;
    public Text secondary;

    public Button press;

    public Rectangle box;
    public double boxWidth, boxHeight, boxEdgeWidth;
    public Color boxBackgroundColor, boxEdgeColor;

    public VBox pane, pane2;

    // home page constructor

    public HomePage(){  // has a top, left, right, middle and bottom since it's a borderPane

        // create a label
        String primaryText = "Welcome to Fortnite!";
        primary = new Label(primaryText);

        // create colored text
        secondary = new Text("You Should Kill Yourself");
        secondary.setFill(Color.GREEN);
        secondaryFont = Font.font(Font.getFontNames().get(0), FontWeight.MEDIUM, 10);
        secondary.setFont(secondaryFont);

        // create simple button
        String pressText = "Click Now to Get Free V-Bucks:";
        press = new Button(pressText);


        // create blue rectangle
        boxWidth = 300;
        boxHeight = 200;
        boxBackgroundColor = Color.BLUE;
        boxEdgeColor = Color.BLACK;
        boxEdgeWidth = 1;

        box = new Rectangle(boxWidth, boxHeight, boxBackgroundColor);
        box.setStrokeWidth(boxEdgeWidth);
        box.setStroke(boxEdgeColor);

        this.setLeft(box);


        // create a VBox: lays out its children in form of vertical columns.
        // If the vbox has a border and/or padding set, then the contents
        // will be layed out within those insets.
        pane = new VBox();
        pane.setSpacing(15);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().addAll(primary, secondary);

        this.setTop(pane);


        pane2 = new VBox();
        pane2.setSpacing(15);
        pane2.setPadding(new Insets(10, 10, 10, 10));
        pane2.getChildren().addAll(press);

        this.setCenter(pane2);

    }


    // event listeners for home page

}
