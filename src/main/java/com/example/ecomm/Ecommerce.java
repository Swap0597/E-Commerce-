package com.example.ecomm;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.header;

public class Ecommerce extends Application {

    ProductList productList = new ProductList();
    private final int width=450, height = 400, headerLine = 50;
    Pane bodyPane;
    GridPane footerBar;
    Order order = new Order();
    ObservableList<Product> cartItemList = FXCollections.observableArrayList();
    Button signUpButton2 = new Button("Sign Up");

    Button signInButton = new Button("Sign In ");
    Button signOutButton = new Button("Sign Out");
    Button placeOrderButton = new Button("Place Order");
    Button ordersButton = new Button("Orders");
    Button cartButton = new Button("   Cart   ");
    Button buyNowButton = new Button("Buy Now");
    Button addToCartButton = new Button("Add To Cart");

    Label welcomeLabel = new Label("Welcome Customer");
    Customer loggedInCustomer = null;
    Font fontForMessages = Font.font("Arial", FontWeight.BOLD, 15);
    Font fontForLabelField = Font.font("Arial", FontWeight.BOLD, 12);
    Font fontForTextField = Font.font("Arial", 11);
    Font fontForButton = Font.font("Arial", 12);
    private void addItemsToCart(Product product){
        if(cartItemList.contains(product)){
            return;
        }
        cartItemList.add(product);
        //System.out.println("product in cart "+cartItemList.stream().count());
    }
    private GridPane headderBar(){
        GridPane header = new GridPane();

        Button searchButton = new Button("Search");
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search");

        ordersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(order.getOrdersOfCustomer(loggedInCustomer));
            }
        });

//        searchButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                bodyPane.getChildren().clear();
//                bodyPane.getChildren().add(productList.getAllProducts());
//            }
//        });

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                signUpButton2.setVisible(true);

            }
        });

        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signUpPage());
                loggedInCustomer = null;
                signOutButton.setVisible(false);
                signInButton.setVisible(true);
                ordersButton.setVisible(false);
                buyNowButton.setVisible(false);
                cartButton.setVisible(false);
                placeOrderButton.setVisible(false);
                addToCartButton.setVisible(false);
                signUpButton2.setVisible(true);


            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productList.productsInCart(cartItemList));
                placeOrderButton.setVisible(false);
                buyNowButton.setVisible(false);
                addToCartButton.setVisible(false);
                placeOrderButton.setVisible(true);
            }
        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String searchItem = searchBar.getText();
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productList.getSearchedProduct(searchItem));
                if(loggedInCustomer != null){
                    addToCartButton.setVisible(true);
                    buyNowButton.setVisible(true);
                    placeOrderButton.setVisible(false);
                    signUpButton2.setVisible(false);
                }
                if(loggedInCustomer == null){
                    signUpButton2.setVisible(true);
                }
            }
        });
        signUpButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signUpPage());
            }
        });
        header.setHgap(10);
        header.setTranslateX(10);
        header.setTranslateY(5);
        header.add(searchBar, 0,2);
        searchBar.setFont(fontForTextField);
        header.add(searchButton, 1, 2);
        searchButton.setFont(fontForButton);

        header.add(signOutButton, 2,2);
        signOutButton.setFont(fontForButton);
        signOutButton.setVisible(false);
        header.add(signInButton,2,2);
        signInButton.setFont(fontForButton);
        header.add(cartButton,3, 2);
        cartButton.setFont(fontForButton);
        header.add(ordersButton, 4,2);
        cartButton.setVisible(false);
        ordersButton.setFont(fontForButton);
        ordersButton.setVisible(false);
        header.add(signUpButton2,3, 2);
        signUpButton2.setFont(fontForButton);
        signUpButton2.setVisible(false);
        //header.add(userName,6,2);

        return header;
    }
    private GridPane signUpPage(){
        Label nameLabel = new Label("Name");
        Label passLabel = new Label("Password");
        Label emailLabel = new Label("Email");
        Label mobileLabel = new Label("Mobile No.");
        Label addressLabel = new Label("Address");

        TextField userName = new TextField();
        userName.setPromptText("Enter User Name");
        PasswordField userPassword = new PasswordField();
        userPassword.setPromptText("Enter Password");
        TextField userEmail = new TextField();
        userEmail.setPromptText("Enter Email");
        TextField userMobile = new TextField();
        userMobile.setPromptText("Enter Mobile No.");
        TextField userAddress = new TextField();
        userAddress.setPromptText("Enter Address");

        Button signUpButton = new Button("Sign Up");


        GridPane signUpPane = new GridPane();
            signUpPane.setTranslateY(50);
            signUpPane.setTranslateX(50);
            signUpPane.setVgap(10);
            signUpPane.setHgap(10);
            signUpPane.add(nameLabel, 0, 0);
            nameLabel.setFont(fontForLabelField);
            signUpPane.add(userName, 1,0);
            userName.setFont(fontForTextField);
            signUpPane.add(passLabel, 0, 1);
            passLabel.setFont(fontForLabelField);
            signUpPane.add(userPassword,1,1);
            userPassword.setFont(fontForTextField);
            signUpPane.add(emailLabel, 0,2);
            emailLabel.setFont(fontForLabelField);
            signUpPane.add(userEmail, 1, 2);
            userEmail.setFont(fontForTextField);
            signUpPane.add(mobileLabel,0,3);
            mobileLabel.setFont(fontForLabelField);
            signUpPane.add(userMobile,1,3);
            userMobile.setFont(fontForTextField);
            signUpPane.add(addressLabel,0,4);
            addressLabel.setFont(fontForLabelField);
            signUpPane.add(userAddress,1,4);
            userAddress.setFont(fontForTextField);
            signUpPane.add(signUpButton,1,6);
            signUpButton.setFont(fontForButton);
            signUpButton2.setVisible(false);

            signUpButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String name = userName.getText();
                    String password = userPassword.getText();
                    String email = userEmail.getText();
                    int mobile = Integer.parseInt(userMobile.getText());
                    String address = userAddress.getText();

                    boolean signupSuccessful = Signup.customerSignUp(name, email, mobile, address, password);
                    if(signupSuccessful == true){
                        showDialogue("Signup Successful \n\n Please Login To Continue");
                    }
                }
            });


        return signUpPane;
    }


    private GridPane loginPage(){
        Label userLabel = new Label("User Name");
        Label passLabel = new Label("Password");
        TextField userName = new TextField();
        userName.setPromptText("Enter User Name");
        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");
        Button loginButton = new Button("Login");
        Label messageLable = new Label("");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userName.getText();
                String pass = password.getText();
                loggedInCustomer = Login.customerLogin(user,pass);

                if(loggedInCustomer != null){
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productList.getAllProducts());
                    addToCartButton.setVisible(true);
                    buyNowButton.setVisible(true);
                    signOutButton.setVisible(true);
                    ordersButton.setVisible(true);
                    cartButton.setVisible(true);
                    signInButton.setVisible(false);


//                    placeOrderButton.setVisible(false);
                    signUpButton2.setVisible(false);
                }
                else{
                    welcomeLabel.setText("Try Again");
                }

            }
        });

        GridPane loginPane = new GridPane();
        loginPane.setTranslateY (50);
        loginPane.setTranslateX(50);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.add(userLabel, 0, 2);
        loginPane.add(userName, 1, 2);
        loginPane.add(passLabel, 0, 3);
        loginPane.add(password, 1,3);
        loginPane.add(loginButton,0,4);
        loginPane.add(messageLable, 1,5);
        loginPane.add(welcomeLabel, 1,0);
        messageLable.setFont(fontForMessages);
        welcomeLabel.setFont(fontForMessages);
        userLabel.setFont(fontForLabelField);
        passLabel.setFont(fontForLabelField);
        userName.setFont(fontForTextField);
        password.setFont(fontForTextField);
        loginButton.setFont(fontForButton);



        return loginPane;
    }
    private void showDialogue(String message){
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Order Status");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(message);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);

            dialog.showAndWait();

    }
    private GridPane footerBar(){


        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                boolean orderStatus = false;
                if(product != null && loggedInCustomer != null){
                    orderStatus = order.placeOrder(loggedInCustomer, product);
                }
                if(orderStatus == true){
                    //
                     showDialogue("Order Successful");
                }
                else{
                    //
                    showDialogue("Order Failed");
                }
            }
        });

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                addItemsToCart(product);
                buyNowButton.setVisible(false);
                placeOrderButton.setVisible(true);
            }
        });

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int orderCount = 0;
                if(!cartItemList.isEmpty() && loggedInCustomer != null){
                    orderCount = order.placeOrderMultipleProducts(cartItemList, loggedInCustomer);
                }
                else{
                    showDialogue("Cart Is Empty");
                }
                if(orderCount > 0){
                    //
                    showDialogue("Order for "+ orderCount+ " Products Placed Successfully");
                    cartItemList.clear();

                }
                else{
                    //
                    showDialogue("Order Failed");
                }
            }
        });

        addToCartButton.setVisible(false);
        placeOrderButton.setVisible(false);
        buyNowButton.setVisible(false);

        GridPane footer = new GridPane();
        footer.setHgap(10);
        footer.setTranslateX(68);
        footer.setTranslateY(20);
        footer.setTranslateY(headerLine+height+10);
        footer.add(buyNowButton, 0, 0);
        footer.add(addToCartButton,1,0);
        footer.add(placeOrderButton, 2,0);

        return footer;
    }
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width, height + 2 * headerLine);

        bodyPane = new Pane();
        bodyPane.setPrefSize(width, height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.setTranslateX(10);

        bodyPane.getChildren().addAll(signUpPage());

        footerBar = footerBar();

        root.getChildren().addAll(headderBar(),
                //loginPage(),
               //  productList.getAllProducts(),
                bodyPane,
                //signUpPage(),
                footerBar()
        );
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());//fxmlLoader.load(), 320, 240
        stage.setTitle("E-Commerce");
//        GridPane pane = new GridPane();
//        pane.setStyle("-fx-background-image: url('https://media.istockphoto.com/id/1254508881/photo/woman-holding-sale-shopping-bags-consumerism-shopping-lifestyle-concept.jpg?s=612x612&w=0&k=20&c=wuS3z6nPQkMM3_wIoO67qQXP-hfXkxlBc2sedwh-hxc=')");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}