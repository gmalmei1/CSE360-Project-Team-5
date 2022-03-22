package com.restaurant;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;


// class Restaurant_Website extends the JavaFX Application and is the page manager

public class Restaurant_Website extends Application {

    // declare application level data structures

    public Menu theMenu;
    public Line line;

    public ArrayList<Coupon> couponList;
    public ArrayList<User> userList;

    public ArrayList<String> typeList;
    public ArrayList<String> allergenList;

    public ArrayList<String> securityQuestions;

    public User currentUser;

    // setup a single admin user function

    private Admin createAdmin(){
        String name = "William Afton";
        String email = "purpleGuy@gmail.com";
        String password = "1987";
        return new Admin(name, email, password);
    }

    // setup menu function

    private void addDefaultMenuItems(){

        theMenu.addToMenu("Fortnite Burger",
                            7.99,
                            "Dishes",
                            "",
                            5,
                new Image("C:/Users/ianwo/IdeaProjects/restaurant/pictures/fortniteburger.jpg")
                );

        theMenu.addToMenu("Sussy Shake", 2.99, "Drinks", "", 2, null);
    }


    public static void main(String[] args) {
        launch();
    }

    // BEGIN APPLICATION, SETUP EACH DATA STRUCTURE AND PAGE //

    @Override
    public void start(Stage primaryStage) {
        try {

            //init constants
            double width = 900, height = 750, navSpace = 50;
            double bannerHeight = 150;
            double vbox_margin = 20;
            int pageCount = 5;
            int home = 0, login = 1, menu = 2, cart = 3, confirm = 4;

            //setup basic strings for website "URL", pages, types of food, and allergens, etc
            String website_title = "www.fortnite@freddys.com";

            String homeStr = "Home";
            String loginStr = "Login/Register";
            String menuStr = "View Menu";
            String cartStr = "View Cart/Checkout";
            String confirmStr = "Confirm Order";

            String food = "Dishes";
            String drink = "Drinks";
            String sides = "Sides";
            String combo = "Specials";

            String nuts = "Nuts";
            String gluten = "Gluten";
            String dairy = "Dairy";

            String q1 = "What is your mothers maiden name?";
            String q2 = "What is your first pet's name?";
            String q3 = "Have you ever been called sus?";

            // INIT JAVA Classes //

            typeList = new ArrayList<>();
            typeList.add(food);
            typeList.add(drink);
            typeList.add(sides);
            typeList.add(combo);

            allergenList = new ArrayList<>();
            allergenList.add(nuts);
            allergenList.add(gluten);
            allergenList.add(dairy);

            securityQuestions = new ArrayList<>();
            securityQuestions.add(q1);
            securityQuestions.add(q2);
            securityQuestions.add(q3);

            theMenu = new Menu(typeList, allergenList);
            addDefaultMenuItems();

            line = new Line();

            userList = new ArrayList<>();
            userList.add(createAdmin());

            couponList = new ArrayList<>();

            currentUser = null;

            // INIT JAVAFX Scenes //

            //init other lists for javaFX
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
                banner.setFitWidth(width);
                banners.add(banner);
            }

            //declare home page UI and nav buttons at top (0)
            HomePage home_gui = new HomePage();

            //add tab Strings to the home tab list
            ArrayList<String> homeTabs = new ArrayList<>();
            homeTabs.add(loginStr);
            homeTabs.add(menuStr);
            homeTabs.add(cartStr);

            //setup navigation buttons with input tab Strings
            FlowPane home_nav = getNavBar(homeTabs, navSpace);
            navs.add(home_nav);

            //setup home layout with banner at top, navigation bar in the middle, and the home gui at bottom
            VBox home_layout = new VBox();
            home_layout.setSpacing(vbox_margin);
            home_layout.getChildren().addAll(banners.get(home), home_nav, home_gui);
            layouts.add(home_layout);


            // similar setup for login page (1)
            LoginPage login_gui = new LoginPage(securityQuestions);

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

            // similar setup for menu page (2)
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

            // similar setup for cart/checkout page (3)
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

            // similar setup for order confirmation page (4)
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

            // SETUP NAVIGATION EVENT HANDLERS //

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
                    }
                    else if (Objects.equals(button.getText(), loginStr)) {
                        button.setOnAction(e -> primaryStage.setScene(scenes.get(login)));
                    }
                    else if (Objects.equals(button.getText(), menuStr)) {
                        button.setOnAction(e -> primaryStage.setScene(scenes.get(menu)));
                    }
                    else if (Objects.equals(button.getText(), cartStr)) {
                        button.setOnAction(e -> primaryStage.setScene(scenes.get(cart)));
                    }
                    else if (Objects.equals(button.getText(), confirmStr)) {
                        button.setOnAction(e -> primaryStage.setScene(scenes.get(confirm)));
                    }
                }
            }

            // DISPLAY HOME PAGE ON STARTUP //

            primaryStage.setTitle(website_title);
            primaryStage.setScene(scenes.get(home));
            primaryStage.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // HELPER FUNCTIONS //

    //
    private FlowPane getNavBar(ArrayList<String> tabs, double navSpace) {

        //create arraylist of buttons based on the tab strings given
        ArrayList<Button> navButtons = new ArrayList<>();
        for (String t : tabs) {
            Button b = new Button(t);
            b.setFont(Font.font(Font.getFontNames().get(0), FontWeight.BOLD, 15));
            b.setStyle("-fx-background-color: #AD7FCD; -fx-text-fill: black;");
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

    // function setupGrid returns a gridPane w/ default values inside it
    private GridPane setupGrid(){
        GridPane grid = new GridPane();
        grid.setMinSize(200, 100);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(15);
        return grid;
    }

    //function setupVBox returns a vbox w/ default values in it
    private VBox setupVBox(){
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        return vbox;
    }


    // PAGE SUB-CLASSES //

    class HomePage extends BorderPane {

        //pre-declare all GUI elements (needs to be done to add event handling)
        public Text menuItemsPreview;
        public VBox comingSoon;

        public ImageView map;
        public ImageView location;

        public Text specialsPane;
        public Text eventsPane;
        public Text locationsPane;
        public Text contactInfoPane;

        public Text hiringText;

        // home page constructor (TODO: re-arrange GUI elements, add functionally)
        public HomePage(){

            // setup coming soon Vertical Box with custom font green text and image
            comingSoon = setupVBox();
            Text comingSoonText = new Text("Coming Soon: The X Thing");
            comingSoonText.setFill(Color.GREEN);
            comingSoonText.setFont(Font.font(Font.getFontNames().get(0), FontWeight.MEDIUM, 15));

            ImageView theThing = new ImageView(new Image("C:/Users/ianwo/IdeaProjects/restaurant/pictures/sus.jpg"));
            theThing.setFitWidth(400);
            theThing.setFitHeight(150);
            theThing.setPreserveRatio(true);

            comingSoon.getChildren().addAll(comingSoonText, theThing);

            // TODO: setup events as a dynamic set of events outputted in this pane
            eventsPane = new Text();
            eventsPane.setText("""
                    Events:
                    (4/13) Free V-Buck Giveaway: come in person to get free VBucks!
                    (4/16) Fazbear and Friends Concert: enjoy the music and fun!
                    (4/25) Store closed for regular maintenance for store hours
                    (5/1)  Jonesy and the Rock visit: come to get signed T-Shirts
                    """);
            eventsPane.setFont(Font.font(Font.getFontNames().get(2), FontWeight.MEDIUM, 15));

            //TODO: setup specials as list of menu items that are type specials
            specialsPane = new Text();
            specialsPane.setText("""
                    Specials:
                    Default Meal w/ Sussy Shake
                    Chica's Chicken Basket
                    Foxy Fries (Specially Seasoned)
                    """);
            specialsPane.setFont(Font.font(Font.getFontNames().get(2), FontWeight.MEDIUM, 15));

            //TODO: setup locations as dynamic list of locations
            locationsPane = new Text();
            locationsPane.setText("""
                    Locations:
                    Fortnite @ Freddy's: Salty Springs, 45012
                    Fazbears Family Diner: Tilted Towers, 45123
                    """);
            locationsPane.setFont(Font.font(Font.getFontNames().get(2), FontWeight.MEDIUM, 15));

            //setup preview of menu items by printing string that contains the name of each menu item
            //in the menu
            menuItemsPreview = new Text();
            StringBuilder itemText = new StringBuilder("Menu Items:\n");
            for (int i = 1; i <= theMenu.menuSize; i++){
                itemText.append(i).append(": ").append(theMenu.items.get(i-1).getName()).append("\n");
            }
            menuItemsPreview.setText(String.valueOf(itemText));
            menuItemsPreview.setFont(Font.font(Font.getFontNames().get(2), FontWeight.MEDIUM, 15));
            this.setTop(menuItemsPreview);

            //setup map and location images and combine them into horizontal flowpane
            map = new ImageView(new Image("C:/Users/ianwo/IdeaProjects/restaurant/pictures/mapV3.jpg"));
            map.setPreserveRatio(false);
            map.setFitWidth(200);
            map.setFitHeight(100);

            location = new ImageView(new Image("C:/Users/ianwo/IdeaProjects/restaurant/pictures/location1.jpg"));
            location.setPreserveRatio(true);
            location.setFitWidth(200);
            location.setFitHeight(100);

            FlowPane mapLocation = new FlowPane();
            mapLocation.setHgap(10);
            mapLocation.setAlignment(Pos.TOP_CENTER);
            mapLocation.getChildren().addAll(map, location);

            //TODO: make contact info dynamic
            contactInfoPane = new Text();
            contactInfoPane.setText("""
                    Contact Info:
                    Phone Number: (499) 672-1984
                    Business Email: freddyfazbear@yahoo.com
                    """);
            contactInfoPane.setFont(Font.font(Font.getFontNames().get(2), FontWeight.MEDIUM, 15));

            //setup left side of home page
            VBox left = setupVBox();
            left.getChildren().addAll(comingSoon, specialsPane, locationsPane, contactInfoPane);

            //setup right side of home page
            VBox right = setupVBox();
            right.getChildren().addAll(menuItemsPreview, eventsPane, mapLocation);

            //combine the left and right side in a flow pane
            FlowPane bottom = new FlowPane();
            bottom.setAlignment(Pos.TOP_CENTER);
            bottom.setHgap(10);
            bottom.getChildren().addAll(left, right);

            this.setCenter(bottom);

            //add "Hiring Now" text
            hiringText = new Text("We're Hiring. Apply Now!");
            hiringText.setFont(Font.font(Font.getFontNames().get(3), FontWeight.MEDIUM, 15));
            hiringText.setFill(Color.BLUEVIOLET);
            FlowPane hiring = new FlowPane();
            hiring.setAlignment(Pos.TOP_CENTER);
            hiring.getChildren().add(hiringText);

            this.setBottom(hiring);

        }

    }

    class LoginPage extends BorderPane {

        //pre-declare all GUI elements (needs to be done to add event handling)

        public FlowPane loginPageSections;

        //(1)
        public VBox createAccount;

        public GridPane registerGrid;

        public TextField nameField, regEmailField;
        public PasswordField regPasswordField, regConfirmPasswordField;

        public ChoiceBox<String> questions;
        public TextField securityAnswer;
        public Button confirmAccount;
        private Text accountErrorMsg;

        public GridPane securityPane;

        //(2)
        public VBox basicInfo;

        public GridPane registerGrid2, contactPaymentInfo;

        public TextField phoneField, paymentField;

        //(3)
        public VBox createLogin;

        public TextField userEmailField;
        public PasswordField userPasswordField;
        public Button signInAccount;
        public Text loginErrorMsg;

        public TextField forgotEmailField, securityAnswer2;
        public ChoiceBox<String> questions2;
        public Button getPasswordButton;
        public Text forgotErrorMsg;

        public GridPane signInGrid, updatePasswordGrid;
        public VBox updateSecurity;




        // construct the login page
        public LoginPage(ArrayList<String> q){

            //get security questions from main
            String q1 = q.get(0);
            String q2 = q.get(1);
            String q3 = q.get(2);

            // (1) make the create account section
            createAccount = setupVBox();

            Text createAccountText = new Text("Create Account");
            createAccountText.setFont(Font.font(Font.getFontNames().get(0), FontWeight.MEDIUM, 20));
            createAccountText.setFill(Color.GREEN);
            createAccount.getChildren().add(createAccountText);

            registerGrid = setupGrid();

            Text nameText = new Text("Full Name");
            nameField = new TextField();
            registerGrid.add(nameText, 0, 0);
            registerGrid.add(nameField, 1, 0);

            Text regEmailText = new Text("Email");
            regEmailField = new TextField();
            registerGrid.add(regEmailText, 0, 1);
            registerGrid.add(regEmailField, 1, 1);

            Text regPasswordText = new Text("Password");
            regPasswordField = new PasswordField();
            registerGrid.add(regPasswordText, 0, 2);
            registerGrid.add(regPasswordField, 1, 2);

            Text regConfirmPasswordText = new Text("Confirm Password");
            regConfirmPasswordField = new PasswordField();
            registerGrid.add(regConfirmPasswordText, 0, 3);
            registerGrid.add(regConfirmPasswordField, 1, 3);

            createAccount.getChildren().add(registerGrid);

            securityPane = setupGrid();

            Text securityText = new Text("Security");
            securityText.setFont(Font.font(Font.getFontNames().get(1), FontWeight.MEDIUM, 15));
            securityText.setFill(Color.GREEN);
            securityPane.add(securityText, 0, 0);

            Text securityQ = new Text("Choose a security question to \nanswer from selection below: ");
            securityPane.add(securityQ, 0, 1);

            questions = new ChoiceBox<>();
            questions.getItems().addAll(q1, q2, q3);
            securityPane.add(questions, 0, 2);

            securityAnswer = new TextField();
            securityPane.add(securityAnswer, 0, 3);

            confirmAccount = new Button("Create Account");
            confirmAccount.setAlignment(Pos.CENTER);
            confirmAccount.setOnAction(new ButtonHandler());
            confirmAccount.setStyle("-fx-background-color: #34748D; -fx-text-fill: white;");

            securityPane.add(confirmAccount, 0, 4);

            accountErrorMsg = new Text();
            accountErrorMsg.setFill(Color.RED);
            accountErrorMsg.setFont(Font.font(Font.getFontNames().get(2), FontWeight.MEDIUM, 15));
            securityPane.add(accountErrorMsg, 0, 5);

            createAccount.getChildren().add(securityPane);

            // (2) Create Account (Payment/Contact Info Section)

            basicInfo = setupVBox();

            registerGrid2 = setupGrid();

            Text contactPayment = new Text("Contact/Payment");
            contactPayment.setFont(Font.font(Font.getFontNames().get(1), FontWeight.MEDIUM, 15));
            contactPayment.setFill(Color.GREEN);
            registerGrid2.add(contactPayment, 0, 0);

            Text contactPaymentDirections = new Text("""
                Enter phone number in contact box
                 in format XXX-XXX-XXXX:
                For example, 623-532-4510 is in valid number
                format
                Enter credit card number in card box
                For example, 0000-0000-0000-0000 is in valid
                card format""");
            registerGrid2.add(contactPaymentDirections, 0, 1);

            basicInfo.getChildren().add(registerGrid2);

            contactPaymentInfo = setupGrid();

            Text phoneText = new Text("Phone Number");
            phoneField = new TextField();
            contactPaymentInfo.add(phoneText, 0, 0);
            contactPaymentInfo.add(phoneField, 1, 0);

            Text paymentText = new Text("Credit Card");
            paymentField = new TextField();
            contactPaymentInfo.add(paymentText, 0, 1);
            contactPaymentInfo.add(paymentField, 1, 1);

            basicInfo.getChildren().add(contactPaymentInfo);

            // (3) Create Login Section

            createLogin = setupVBox();

            Text createLoginText = new Text("Account Login");
            createLoginText.setFont(Font.font(Font.getFontNames().get(0), FontWeight.MEDIUM, 20));
            createLoginText.setFill(Color.GREEN);
            createLogin.getChildren().add(createLoginText);

            signInGrid = setupGrid();

            Text userEmailText = new Text("Email");
            userEmailField = new TextField();
            signInGrid.add(userEmailText, 0, 0);
            signInGrid.add(userEmailField, 1, 0);

            Text userPasswordText = new Text("Password");
            userPasswordField = new PasswordField();
            signInGrid.add(userPasswordText, 0, 1);
            signInGrid.add(userPasswordField, 1, 1);

            signInAccount = new Button("Login");
            signInAccount.setOnAction(new ButtonHandler());
            signInAccount.setStyle("-fx-background-color: #34748D; -fx-text-fill: white;");

            loginErrorMsg = new Text("");
            loginErrorMsg.setFill(Color.RED);
            loginErrorMsg.setFont(Font.font(Font.getFontNames().get(2), FontWeight.MEDIUM, 15));
            signInGrid.add(signInAccount, 0, 2);
            signInGrid.add(loginErrorMsg, 1, 2);

            createLogin.getChildren().add(signInGrid);

            Text forgotText = new Text("Forgot Password?");
            securityText.setFont(Font.font(Font.getFontNames().get(1), FontWeight.MEDIUM, 15));
            securityText.setFill(Color.GREEN);
            createLogin.getChildren().add(forgotText);

            updatePasswordGrid = setupGrid();

            Text forgotEmailText = new Text("Email");
            forgotEmailField = new TextField();
            updatePasswordGrid.add(forgotEmailText, 0, 0);
            updatePasswordGrid.add(forgotEmailField, 1, 0);
            createLogin.getChildren().add(updatePasswordGrid);

            updateSecurity = setupVBox();

            Text chooseQ = new Text("""
                Choose your security question to
                answer from selection below
                to get new password:""");
            updateSecurity.getChildren().add(chooseQ);

            questions2 = new ChoiceBox<>();
            questions2.getItems().addAll(q1, q2, q3);
            updateSecurity.getChildren().add(questions2);

            securityAnswer2 = new TextField();
            updateSecurity.getChildren().add(securityAnswer2);

            getPasswordButton = new Button("Get Password");
            getPasswordButton.setOnAction(new ButtonHandler());
            getPasswordButton.setStyle("-fx-background-color: #34748D; -fx-text-fill: white;");
            updateSecurity.getChildren().add(getPasswordButton);

            forgotErrorMsg = new Text("");
            forgotErrorMsg.setFill(Color.RED);
            forgotErrorMsg.setFont(Font.font(Font.getFontNames().get(2), FontWeight.MEDIUM, 15));
            updateSecurity.getChildren().add(forgotErrorMsg);

            createLogin.getChildren().add(updateSecurity);


            // (4) put the sections left to right
            loginPageSections = new FlowPane();
            loginPageSections.setHgap(30);
            loginPageSections.setAlignment(Pos.TOP_CENTER);

            loginPageSections.getChildren().addAll(createAccount, basicInfo, createLogin);

            this.setCenter(loginPageSections);

        }

        //event handlers for login page
        private class ButtonHandler implements EventHandler<ActionEvent>{

            @Override
            public void handle(ActionEvent event) {
                //if the viewer wants to register their account
                if (event.getSource() == confirmAccount){
                    checkRegistration();
                }
                //if the viewer wants to login to their account
                if (event.getSource() == signInAccount){
                    checkSignIn();
                }
                //if the viewer forgot their login info
                if (event.getSource() == getPasswordButton){
                    manageForgotPassword();
                }
            }

            private void manageForgotPassword() {
                //get values required to reset password
                String inputEmail = forgotEmailField.getText();
                String securityQ = questions2.getValue();
                String securityA = securityAnswer2.getText();

                // if anything is missing, alert user
                forgotErrorMsg.setFill(Color.RED);
                if (inputEmail.isEmpty() || securityA.isEmpty() || securityQ == null){
                    forgotErrorMsg.setText("Invalid Recovery: either email, question\n" +
                            "or answer is not inputted");
                    return;
                }
                // if everything is there: first see if the email is valid

                for (User u : userList) {
                    if (u.getEmail().equals(inputEmail) && u.getSecurityQ().equals(securityQ)
                            && u.getSecurityA().equals(securityA)) {
                        forgotErrorMsg.setFill(Color.GREEN);
                        forgotErrorMsg.setText("Password Successfully Recovered: \npassword in email field" +
                                "\nwhich you should clear for your own safety");

                        forgotEmailField.setText(u.getPassword());
                        return;
                    }
                }
                forgotErrorMsg.setText("Invalid Recovery: fields aren't correct");
            }

            private void checkSignIn() {
                // get the values at fields required to login
                String inputEmail = userEmailField.getText();
                String password = userPasswordField.getText();

                loginErrorMsg.setFill(Color.RED);
                if (inputEmail.isEmpty()){
                    loginErrorMsg.setText("Missing Email");
                    return;
                }
                if (password.isEmpty()){
                    loginErrorMsg.setText("Missing Password");
                    return;
                }

                // search through all accounts
                boolean userFound = false;
                for (User u : userList){
                    // if email and password inputted equal that of a customer in the user list
                    if (u.getEmail().equals(inputEmail) && u.getPassword().equals(password)){
                        //set the currentUser as the one with the matching login values
                        currentUser = u;
                        userFound = true;
                    }
                }
                if (!userFound) {
                    loginErrorMsg.setText("Invalid Login");
                }
                else {
                    loginErrorMsg.setFill(Color.GREEN);
                    loginErrorMsg.setText(currentUser.getFullName() + " Successfully Logged In");

                    userEmailField.clear();
                    userPasswordField.clear();

                }

            }

            public void checkRegistration(){
                // get the values at fields required to create account
                String inputName = nameField.getText();
                String inputEmail = regEmailField.getText();

                String password = regPasswordField.getText();
                String confirmPassword = regConfirmPasswordField.getText();

                String securityQ = questions.getValue();
                String securityA = securityAnswer.getText();

                String phoneNumber = phoneField.getText();
                String cardNumber = paymentField.getText();

                // if the input is invalid, set the error text red with the error message
                accountErrorMsg.setFill(Color.RED);
                if (inputName.isEmpty()){
                    accountErrorMsg.setText("Enter Valid Name"); return;
                }
                if (inputEmail.isEmpty()){
                    accountErrorMsg.setText("Enter Valid Email"); return;
                }
                if (password.isEmpty()){
                    accountErrorMsg.setText("Missing Password"); return;
                }
                if (confirmPassword.isEmpty()){
                    accountErrorMsg.setText("Confirm Password"); return;
                }
                if (!password.equals(confirmPassword)){
                    accountErrorMsg.setText("Passwords Don't Match"); return;
                }
                if (securityQ == null){
                    accountErrorMsg.setText("Choose a Security Question"); return;
                }
                if (securityA.isEmpty()){
                    accountErrorMsg.setText("Answer the security question"); return;
                }
                if (phoneNumber.isEmpty()){
                    accountErrorMsg.setText("Enter Phone Number"); return;
                }
                if (cardNumber.isEmpty()){
                    accountErrorMsg.setText("Enter Credit Card Number"); return;
                }

                //if all the information is valid, check if the account was already created

                // search through all accounts
                for (User u : userList){
                    // if the email is already in use
                    if (u.getEmail().equals(inputEmail)) {
                        //alert the GUI error box
                        accountErrorMsg.setText("Account Already Created with this" +
                                "\nInformation: Login on the right section instead");

                        nameField.clear();
                        regEmailField.clear();
                        regPasswordField.clear();
                        regConfirmPasswordField.clear();
                        securityAnswer.clear();
                        questions.setValue("");
                        phoneField.clear();
                        paymentField.clear();
                        return;
                    }
                }

                // create a unique customer class from this info
                // add the customer to the users list and notify the new customer of their
                // new account being created

                Customer newCustomer = new Customer(inputName, inputEmail, password,
                        securityQ, securityA, cardNumber, phoneNumber);

                userList.add(newCustomer);

                for (User u : userList){
                    System.out.println(u.getEmail());
                }

                accountErrorMsg.setFill(Color.GREEN);
                accountErrorMsg.setText(newCustomer.getFullName() + "'s Account Created:\nLogin on the Right");

                nameField.clear();
                regEmailField.clear();
                regPasswordField.clear();
                regConfirmPasswordField.clear();
                securityAnswer.clear();
                questions.setValue("");
                phoneField.clear();
                paymentField.clear();



            }
        }


    }

    class MenuPage extends BorderPane {

        public MenuPage(){

            Label page = new Label("Welcome to Menu Page");
            this.setTop(page);

            // load an image
            String filePath = "C:/Users/ianwo/IdeaProjects/restaurant/pictures/freddyfortnite.jpg";

            Image defaultSkin = new Image(filePath);

            ImageView imageView = new ImageView(defaultSkin);

            imageView.setFitHeight(300);
            imageView.setFitWidth(500);

            imageView.setPreserveRatio(true);

            this.setCenter(imageView);

        }

    }

    class CartCheckoutPage extends BorderPane {

        public CartCheckoutPage(){

            Label page = new Label("Welcome to Cart/Checkout Page");
            this.setTop(page);

            // load an image
            String filePath = "C:/Users/ianwo/IdeaProjects/restaurant/pictures/vbuck.jpg";

            Image defaultSkin = new Image(filePath);
            ImageView imageView = new ImageView(defaultSkin);

            imageView.setFitHeight(300);
            imageView.setFitWidth(500);

            imageView.setPreserveRatio(true);

            this.setCenter(imageView);

        }
    }

    class ConfirmationPage extends BorderPane {

        public ConfirmationPage(){

            //VBox orderSection = new VBox();

            // load an image
            String filePath = "C:/Users/ianwo/IdeaProjects/restaurant/pictures/victoryroyale.jpg";

            Image defaultSkin = new Image(filePath);
            ImageView imageView = new ImageView(defaultSkin);

            imageView.setFitHeight(300);
            imageView.setFitWidth(500);

            imageView.setPreserveRatio(true);

            this.setCenter(imageView);



        }
    }




}