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
import javafx.scene.layout.BorderPane;
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
    private final int width = 450, height = 400, headerLine = 50;
    Pane bodyPane;
    GridPane footerBar;
    Pane headder;
    Pane footerPane;
    boolean flagForSingleOrMulOrder = false;
    Order order = new Order();
    RadioButton payNow = new RadioButton("Pay Now");
    RadioButton COD = new RadioButton("Cash On \n Delivery");
    ObservableList<Product> cartItemList = FXCollections.observableArrayList();
    Button signUpButton2 = new Button("Sign Up");

    Button signInButton = new Button("Sign In ");
    Button signOutButton = new Button("Sign Out");
    Button placeOrderButton = new Button("Place Order");
    Button ordersButton = new Button("Orders");
    Button cartButton = new Button("   Cart   ");
    Button buyNowButton = new Button("Buy Now");
    Button addToCartButton = new Button("Add To Cart");
    Button buyNowToOrder = new Button("Buy Now");
    Button placeOrderToOrderButton = new Button("Place Order");


    Label welcomeLabel = new Label("Welcome Customer");
    Customer loggedInCustomer = null;
    Font fontForMessages = Font.font("Arial", FontWeight.BOLD, 15);
    Font fontForLabelField = Font.font("Arial", FontWeight.BOLD, 12);
    Font fontForTextField = Font.font("Arial", 11);
    Font fontForButton = Font.font("Arial", 12);

    private void addItemsToCart(Product product) {
        if (cartItemList.contains(product)) {
            return;
        }
        cartItemList.add(product);
    }

    private GridPane headderBar() {
        GridPane header = new GridPane();

        Button searchButton = new Button("Search");
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search");

        ordersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(order.getOrdersOfCustomer(loggedInCustomer));
                placeOrderButton.setVisible(false);
                buyNowButton.setVisible(false);
                buyNowToOrder.setVisible(false);
                addToCartButton.setVisible(false);
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
                buyNowButton.setVisible(false);
                buyNowToOrder.setVisible(false);
                addToCartButton.setVisible(false);
//                placeOrderButton.setVisible(true);
                if (!cartItemList.isEmpty()) {
                    placeOrderToOrderButton.setVisible(true);
                }
            }
        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String searchItem = searchBar.getText();
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productList.getSearchedProduct(searchItem));
                if (loggedInCustomer != null) {
                    addToCartButton.setVisible(true);
                    buyNowToOrder.setVisible(true);
                    placeOrderButton.setVisible(false);
                    signUpButton2.setVisible(false);
                }
                if (loggedInCustomer == null) {
                    signUpButton2.setVisible(true);
                    buyNowToOrder.setVisible(false);
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
        header.setTranslateY(13);
        header.add(searchBar, 0, 2);
        searchBar.setFont(fontForTextField);
        header.add(searchButton, 1, 2);
        searchButton.setFont(fontForButton);

        header.add(signOutButton, 2, 2);
        signOutButton.setFont(fontForButton);
        signOutButton.setVisible(false);
        header.add(signInButton, 2, 2);
        signInButton.setFont(fontForButton);
        header.add(cartButton, 3, 2);
        cartButton.setFont(fontForButton);
        header.add(ordersButton, 4, 2);
        cartButton.setVisible(false);
        ordersButton.setFont(fontForButton);
        ordersButton.setVisible(false);
        header.add(signUpButton2, 3, 2);
        signUpButton2.setFont(fontForButton);
        signUpButton2.setVisible(false);
        //header.add(userName,6,2);

        return header;
    }

    private GridPane signUpPage() {
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

        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userName.getText();
                String password = userPassword.getText();
                String email = userEmail.getText();
                int mobile = Integer.parseInt(userMobile.getText());
                String address = userAddress.getText();

                boolean signupSuccessful = Signup.customerSignUp(name, email, mobile, address, password);
                if (signupSuccessful == true) {
                    showDialogue("Signup Successful \n\n Please Login To Continue");
                }
            }
        });

        GridPane signUpPane = new GridPane();

        signUpPane.setTranslateY(50);
        signUpPane.setTranslateX(50);
        signUpPane.setVgap(10);
        signUpPane.setHgap(10);
        signUpPane.add(nameLabel, 0, 0);
        nameLabel.setFont(fontForLabelField);
        signUpPane.add(userName, 1, 0);
        userName.setFont(fontForTextField);
        signUpPane.add(passLabel, 0, 1);
        passLabel.setFont(fontForLabelField);
        signUpPane.add(userPassword, 1, 1);
        userPassword.setFont(fontForTextField);
        signUpPane.add(emailLabel, 0, 2);
        emailLabel.setFont(fontForLabelField);
        signUpPane.add(userEmail, 1, 2);
        userEmail.setFont(fontForTextField);
        signUpPane.add(mobileLabel, 0, 3);
        mobileLabel.setFont(fontForLabelField);
        signUpPane.add(userMobile, 1, 3);
        userMobile.setFont(fontForTextField);
        signUpPane.add(addressLabel, 0, 4);
        addressLabel.setFont(fontForLabelField);
        signUpPane.add(userAddress, 1, 4);
        userAddress.setFont(fontForTextField);
        signUpPane.add(signUpButton, 1, 6);
        signUpButton.setFont(fontForButton);
        signUpButton2.setVisible(false);

        return signUpPane;
    }

    private GridPane loginPage() {
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
                loggedInCustomer = Login.customerLogin(user, pass);

                if (loggedInCustomer != null) {
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productList.getAllProducts());
                    addToCartButton.setVisible(true);
                    buyNowToOrder.setVisible(true);
                    signOutButton.setVisible(true);
                    ordersButton.setVisible(true);
                    cartButton.setVisible(true);
                    signInButton.setVisible(false);


                    signUpButton2.setVisible(false);
                } else {
                    welcomeLabel.setText("Try Again");
                }

            }
        });

        GridPane loginPane = new GridPane();
        loginPane.setTranslateY(50);
        loginPane.setTranslateX(50);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.add(userLabel, 0, 2);
        loginPane.add(userName, 1, 2);
        loginPane.add(passLabel, 0, 3);
        loginPane.add(password, 1, 3);
        loginPane.add(loginButton, 0, 4);
        loginPane.add(messageLable, 1, 5);
        loginPane.add(welcomeLabel, 1, 0);
        messageLable.setFont(fontForMessages);
        welcomeLabel.setFont(fontForMessages);
        userLabel.setFont(fontForLabelField);
        passLabel.setFont(fontForLabelField);
        userName.setFont(fontForTextField);
        password.setFont(fontForTextField);
        loginButton.setFont(fontForButton);


        return loginPane;
    }

    private GridPane orderPage() {
        Label askingPaymentMode = new Label("Select type of Payment Mode");

        Label updateAddressLabel = new Label("Update Address");
        TextField userAddressText = new TextField(loggedInCustomer.getAddress());
        Button updateAddButton = new Button("Update");
        Label bankNameLabel = new Label("Enter Bank Name");
        TextField bankNameText = new TextField();
        bankNameText.setPromptText("Enter Bank Name");
        Label enterBankAccNoLabel = new Label("Enter Account No");
        TextField userBankAccNo = new TextField();
        userBankAccNo.setPromptText("Enter Account No");
        Label enterCard = new Label("Enter Card No");
        TextField userCard = new TextField();
        userCard.setPromptText("Enter Card No");
        Label enterEXp = new Label("Enter Expiry Date");
        TextField cardExp = new TextField();
        cardExp.setPromptText("Enter card Expiry Date");
        Button saveCard = new Button("Save Card");
        Button updateCard = new Button("Update Card");

        payNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (loggedInCustomer != null) {
                    updateAddressLabel.setVisible(true);
                    userAddressText.setVisible(true);
                    updateAddButton.setVisible(true);
                    enterBankAccNoLabel.setVisible(true);
                    userBankAccNo.setVisible(true);
                    enterCard.setVisible(true);
                    userCard.setVisible(true);
                    cardExp.setVisible(true);
                    enterEXp.setVisible(true);
                    saveCard.setVisible(true);
                    saveCard.setVisible(true);
                    updateCard.setVisible(false);
                    buyNowButton.setVisible(false);
                    placeOrderButton.setVisible(false);
                }
            }
        });
        saveCard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int accNo = Integer.parseInt(userBankAccNo.getText());
                int cardNo = Integer.parseInt(userCard.getText());
                String expDate = cardExp.getText();
                boolean checkcardDetails = Payment.saveCard(loggedInCustomer, accNo, cardNo, expDate);
                if (checkcardDetails == true) {
                    showDialogue("Card Added");
                    if (!cartItemList.isEmpty()) {
                        placeOrderButton.setVisible(true);

                    } else {
                        buyNowButton.setVisible(true);
                    }
                } else {
                    showDialogue("Card Already Added");
                    saveCard.setVisible(false);
                    updateCard.setVisible(true);
                }
            }
        });
        updateCard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int accNo = Integer.parseInt(userBankAccNo.getText());
                int cardNo = Integer.parseInt(userCard.getText());
                String expDate = cardExp.getText();
                boolean updatecardDetails = Payment.updateCard(loggedInCustomer, accNo, cardNo, expDate);
                if (updatecardDetails == true) {
                    showDialogue("Card Updated");
                    if (!cartItemList.isEmpty()) {
                        placeOrderButton.setVisible(true);

                    } else {
                        buyNowButton.setVisible(true);
                    }
                } else {
                    showDialogue("Update Failed");
                    bodyPane.getChildren().clear();
                    //bodyPane.getChildren().add(g);
                }
            }
        });

        COD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateAddButton.setVisible(true);
                updateAddressLabel.setVisible(true);
                userAddressText.setVisible(true);
                enterBankAccNoLabel.setVisible(false);
                userBankAccNo.setVisible(false);
                enterCard.setVisible(false);
                userCard.setVisible(false);
                cardExp.setVisible(false);
                enterEXp.setVisible(false);
                saveCard.setVisible(false);
                updateCard.setVisible(false);

                if (!cartItemList.isEmpty()) {
                    placeOrderButton.setVisible(true);

                } else {
                    buyNowButton.setVisible(true);
                }
            }
        });
        updateAddButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String address = userAddressText.getText();
                boolean check = Payment.updateAddress(address, loggedInCustomer);
                if (check == true) {
                    showDialogue("Address Updated");
                } else {
                    showDialogue("Address Not Updated");
                }
            }
        });

        GridPane orderPane = new GridPane();
        orderPane.setTranslateY(20);
        ToggleGroup methodOfPaymentGroup = new ToggleGroup();
        updateAddButton.setVisible(false);
        updateAddButton.setFont(fontForButton);
        updateAddressLabel.setVisible(false);
        updateAddressLabel.setFont(fontForLabelField);
        userAddressText.setVisible(false);
        userAddressText.setFont(fontForTextField);
        enterBankAccNoLabel.setVisible(false);
        enterBankAccNoLabel.setFont(fontForLabelField);
        userBankAccNo.setVisible(false);
        userBankAccNo.setFont(fontForTextField);
        enterCard.setVisible(false);
        enterCard.setFont(fontForLabelField);
        userCard.setVisible(false);
        userCard.setFont(fontForTextField);
        cardExp.setVisible(false);
        cardExp.setFont(fontForTextField);
        enterEXp.setVisible(false);
        enterEXp.setFont(fontForLabelField);
        saveCard.setVisible(false);
        saveCard.setFont(fontForButton);
        updateCard.setVisible(false);
        updateCard.setFont(fontForButton);
        addToCartButton.setVisible(false);
        payNow.setToggleGroup(methodOfPaymentGroup);
        COD.setToggleGroup(methodOfPaymentGroup);
        orderPane.setHgap(10);
        orderPane.setVgap(10);
        orderPane.setTranslateX(50);
        orderPane.add(askingPaymentMode, 0, 0);
        askingPaymentMode.setFont(fontForLabelField);
        orderPane.add(COD, 0, 1);
        COD.setFont(fontForLabelField);
        orderPane.add(payNow, 1, 1);
        payNow.setFont(fontForLabelField);
        orderPane.add(updateAddressLabel, 0, 2);
        orderPane.add(userAddressText, 1, 2);
        orderPane.add(updateAddButton, 1, 3);

        orderPane.add(enterBankAccNoLabel, 0, 4);
        orderPane.add(userBankAccNo, 1, 4);
        orderPane.add(enterCard, 0, 5);
        orderPane.add(userCard, 1, 5);
        orderPane.add(enterEXp, 0, 6);
        orderPane.add(cardExp, 1, 6);
        orderPane.add(saveCard, 1, 7);
        orderPane.add(updateCard, 1, 7);

        return orderPane;
    }

    private void showDialogue(String message) {
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

    private GridPane footerBar() {

        placeOrderToOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                flagForSingleOrMulOrder = true;
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(orderPage());
                buyNowButton.setVisible(false);
                buyNowToOrder.setVisible(false);
                placeOrderToOrderButton.setVisible(false);
                placeOrderButton.setVisible(false);
            }
        });
        buyNowToOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(orderPage());
                placeOrderButton.setVisible(false);
                placeOrderToOrderButton.setVisible(false);
                buyNowToOrder.setVisible(false);
                buyNowButton.setVisible(false);
            }
        });
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                boolean orderStatus = false;
                if (product != null && loggedInCustomer != null) {
                    orderStatus = order.placeOrder(loggedInCustomer, product);
                }
                if (orderStatus == true) {
                    //
                    showDialogue("Order Successful");
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productList.getAllProducts());
                    addToCartButton.setVisible(true);
                    buyNowToOrder.setVisible(true);
                    signOutButton.setVisible(true);
                    ordersButton.setVisible(true);
                    cartButton.setVisible(true);
                    signInButton.setVisible(false);
                } else {
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
                buyNowToOrder.setVisible(false);
                placeOrderToOrderButton.setVisible(true);
            }
        });

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(orderPage());

                int orderCount = 0;
                if (!cartItemList.isEmpty() && loggedInCustomer != null) {
                    orderCount = order.placeOrderMultipleProducts(cartItemList, loggedInCustomer);
                } else {
                    showDialogue("Cart Is Empty");
                }
                if (orderCount > 0) {
                    //
                    showDialogue("Order for " + orderCount + " Products Placed Successfully");
                    placeOrderButton.setVisible(false);
                    cartItemList.clear();
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productList.getAllProducts());
                    addToCartButton.setVisible(true);
                    buyNowToOrder.setVisible(true);
                    signOutButton.setVisible(true);
                    ordersButton.setVisible(true);
                    cartButton.setVisible(true);
                    signInButton.setVisible(false);

                } else {
                    //
                    showDialogue("Order Failed");
                }
            }
        });
        placeOrderToOrderButton.setVisible(false);
        buyNowToOrder.setVisible(false);
        addToCartButton.setVisible(false);
        placeOrderButton.setVisible(false);
        buyNowButton.setVisible(false);

        GridPane footer = new GridPane();
        footer.setHgap(10);
        footer.setTranslateX(68);
        footer.setTranslateY(20);
        footer.setTranslateY(headerLine + height + 10);
        footer.add(buyNowToOrder, 0, 0);
        footer.add(buyNowButton, 0, 0);
        footer.add(addToCartButton, 1, 0);
        footer.add(placeOrderToOrderButton, 2, 0);
        footer.add(placeOrderButton, 2, 0);

        return footer;
    }

    private Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(width, height + 2 * headerLine);

        bodyPane = new Pane();
        headder = new Pane();
        footerPane = new Pane();
        bodyPane.setPrefSize(width, height);
        headder.setPrefSize(width, 50);
        footerPane.setPrefSize(width, 50);
        bodyPane.setTranslateY(headerLine);
        footerPane.setTranslateY(height + headerLine);
        bodyPane.getChildren().addAll(signUpPage());
        footerBar = footerBar();

        root.getChildren().addAll(
                headder,
                headderBar(),
                bodyPane,
                footerPane,
                footerBar()
        );
        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());//fxmlLoader.load(), 320, 240
        stage.setTitle("E-Commerce");
        bodyPane.setStyle("-fx-background-color: rgb(255,178,102)");
        headder.setStyle("-fx-background-color: rgb(255,153,51)");
        footerPane.setStyle("-fx-background-color: rgb(255,153,51)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}