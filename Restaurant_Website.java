package com.restaurant;

import javafx.application.Application;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Restaurant_Website extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            //init constants and declare scene variables
            double width = 900, height = 800, navSpace = 50;
            double bannerWidth = width, bannerHeight = 150;
            double vbox_margin = 20;
            int pageCount = 5;
            int home = 0, login = 1, menu = 2, cart = 3, confirm = 4;

            String homeStr = "Home";
            String loginStr = "Login/Register";
            String menuStr = "View Menu";
            String cartStr = "View Cart/Checkout";
            String confirmStr = "Confirm Order";

            String website_title = "www.fortnite@freddys.com";

            ArrayList<Scene> scenes = new ArrayList<>();
            ArrayList<VBox> layouts = new ArrayList<>();
            ArrayList<FlowPane> navs = new ArrayList<>();
            ArrayList<ImageView> banners = new ArrayList<>();

            //create 5 banners with imageView:
            String bannerFilePath = "C:/Users/ianwo/IdeaProjects/restaurant/pictures/banner.jpg";
            Image defaultSkin = new Image(bannerFilePath);

            for (int page = 0; page < pageCount; page++) {

                ImageView banner = new ImageView(defaultSkin);
                banner.setFitHeight(bannerHeight);
                banner.setFitWidth(bannerWidth);
                banners.add(banner);
            }

            //declare home page UI and nav buttons at top (0)
            HomePage home_gui = new HomePage();

            ArrayList<String> homeTabs = new ArrayList<>();
            homeTabs.add(loginStr);
            homeTabs.add(menuStr);
            homeTabs.add(cartStr);

            FlowPane home_nav = getNavBar(homeTabs, navSpace);
            navs.add(home_nav);

            VBox home_layout = new VBox();
            home_layout.setSpacing(vbox_margin);
            home_layout.getChildren().addAll(banners.get(home), home_nav, home_gui);
            layouts.add(home_layout);


            // "" for login page (1)
            LoginPage login_gui = new LoginPage();

            ArrayList<String> loginTabs = new ArrayList<>();
            loginTabs.add(homeStr);
            loginTabs.add(menuStr);
            loginTabs.add(cartStr);

            FlowPane login_nav = getNavBar(loginTabs, navSpace);
            navs.add(login_nav);

            VBox login_layout = new VBox();
            login_layout.setSpacing(vbox_margin);
            login_layout.getChildren().addAll(banners.get(login), login_nav, login_gui);
            layouts.add(login_layout);

            // "" for menu page (2)
            MenuPage menu_gui = new MenuPage();

            ArrayList<String> menuTabs = new ArrayList<>();
            menuTabs.add(homeStr);
            menuTabs.add(loginStr);
            menuTabs.add(cartStr);

            FlowPane menu_nav = getNavBar(menuTabs, navSpace);
            navs.add(menu_nav);

            VBox menu_layout = new VBox();
            menu_layout.setSpacing(vbox_margin);
            menu_layout.getChildren().addAll(banners.get(menu), menu_nav, menu_gui);
            layouts.add(menu_layout);

            // "" for cart/checkout page (3)
            CartCheckoutPage cart_gui = new CartCheckoutPage();

            ArrayList<String> cartTabs = new ArrayList<>();
            cartTabs.add(homeStr);
            cartTabs.add(menuStr);
            cartTabs.add(confirmStr);

            FlowPane cart_nav = getNavBar(cartTabs, navSpace);
            navs.add(cart_nav);

            VBox cart_layout = new VBox();
            cart_layout.setSpacing(vbox_margin);
            cart_layout.getChildren().addAll(banners.get(cart), cart_nav, cart_gui);
            layouts.add(cart_layout);

            // "" for order confirmation page (4)
            ConfirmationPage confirm_gui = new ConfirmationPage();

            ArrayList<String> confirmTabs = new ArrayList<>();
            confirmTabs.add(homeStr);

            FlowPane confirm_nav = getNavBar(confirmTabs, navSpace);
            navs.add(confirm_nav);

            VBox confirm_layout = new VBox();
            confirm_layout.setSpacing(vbox_margin);
            confirm_layout.getChildren().addAll(banners.get(confirm), confirm_nav, confirm_gui);
            layouts.add(confirm_layout);


            //init each scene with same constants but different layouts
            for (VBox layout : layouts){
                scenes.add(new Scene(layout, width, height));
            }

            // for each page in the website application
            for (int page = 0; page < pageCount; page++) {

                // for each tab on the page, setup the event handler to get to the correct page
                // when pressed
                int tabCount = navs.get(page).getChildren().size();
                for (int tab = 0; tab < tabCount; tab++) {

                    // get reference to current button in page tabs
                    Button button = (Button) navs.get(page).getChildren().get(tab);

                    // if the button text == any page string, go to that page when pressed
                    if (Objects.equals(button.getText(), homeStr)) {
                        button.setOnAction(e -> primaryStage.setScene(scenes.get(home)));
                    } else if (Objects.equals(button.getText(), loginStr)) {
                        button.setOnAction(e -> primaryStage.setScene(scenes.get(login)));
                    } else if (Objects.equals(button.getText(), menuStr)) {
                        button.setOnAction(e -> primaryStage.setScene(scenes.get(menu)));
                    } else if (Objects.equals(button.getText(), cartStr)) {
                        button.setOnAction(e -> primaryStage.setScene(scenes.get(cart)));
                    } else if (Objects.equals(button.getText(), confirmStr)) {
                        button.setOnAction(e -> primaryStage.setScene(scenes.get(confirm)));
                    }
                }
            }
            //display home page on startup
            primaryStage.setTitle(website_title);
            primaryStage.setScene(scenes.get(home));
            primaryStage.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FlowPane getNavBar(ArrayList<String> tabs, double navSpace) {

        //create arraylist of buttons based on the tab strings given
        ArrayList<Button> navButtons = new ArrayList<>();
        for (String t : tabs) {
            Button b = new Button(t);
            b.setFont(Font.font(Font.getFontNames().get(0), FontWeight.MEDIUM, 15));
            navButtons.add(b);
        }

        //setup flowpane with parameters of space between buttons and relative position
        FlowPane nav = new FlowPane();
        nav.setHgap(navSpace);
        nav.setAlignment(Pos.TOP_CENTER);

        //add each button into the flowpane
        for (Button b : navButtons){
            nav.getChildren().add(b);
        }

        return nav;
    }


}