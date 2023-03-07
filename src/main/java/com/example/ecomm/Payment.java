package com.example.ecomm;

public class Payment {
    public static boolean updateAddress(String address, Customer customer) {
        try {
            String query = "update customer set address = '" + address + "' where cid = " + customer.getId();
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveCard(Customer customer, int accNo, int cardNo, String expDate) {
        try {
            String query = "insert into carddetails (custid, AccountNo, CardNo, Expiry) values (" + customer.getId() + ","
                    + accNo + "," + cardNo + ",'" + expDate + "')";
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateCard(Customer customer, int accNo, int cardNo, String expDate) {
        try {
            String update = "update carddetails SET cardno = " + cardNo + ", expiry = '" + expDate + "', accountno = " + accNo + " where " + " custid = " + customer.getId();
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(update);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
