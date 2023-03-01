package com.example.ecomm;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class Order {
     TableView<Product> orderTable;
    public  boolean placeOrder(Customer customer, Product product){
        try{
            //"INSERT INTO orders(customer_id, product_id, status) VALUES(1,1,'Ordered');"
            String placeOrder = "INSERT INTO orders(customer_id, product_id, status) VALUES(" + customer.getId() + "," + product.getId() + ",'Ordered')";
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(placeOrder);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public  int placeOrderMultipleProducts(ObservableList<Product> productObservableList, Customer customer){
        int count = 0;

        for(Product product : productObservableList){
            if(placeOrder(customer, product)){
                count++;
            }
        }
        return count;
    }
    public  Pane getOrders(){

        ObservableList<Product> productList = Product.getAllProducts();
        return createTableFromList(productList);
    }
    public  Pane createTableFromList(ObservableList<Product> orderList){
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        orderTable = new TableView<>();
        orderTable.setItems(orderList);
        orderTable.getColumns().addAll(id, name, price);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(orderTable);
        tablePane.setTranslateX(50);
        orderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tablePane;
    }

    public  Pane getOrdersOfCustomer(Customer customer){
        String order = "select orders.oid, products.name, products.price from products inner join orders  on orders.product_id = products.pid where customer_id = "+customer.getId();
        ObservableList<Product> orderList = Product.getProductsOfOrder(order);

        return createTableFromList(orderList);
    }
}
