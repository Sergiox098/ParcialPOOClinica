package com.example.gestordetiendas.ui.home;

public class DBTestProduct {
    private String storeName;
    private String address;
    private String productName;
    private String cost;
    public DBTestProduct(){}
    public DBTestProduct(String Store, String Address, String Product, String Cost ){
        this.storeName = Store;
        this.address = Address;
        this.productName = Product;
        this.cost = Cost;
    }
    public String getStoreName() {
        return storeName;
    }
    public String getAddress() {return address;}
    public String getProductName() {
        return productName;
    }
    public String getCost() {return cost;}
}