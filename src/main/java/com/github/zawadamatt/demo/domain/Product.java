package com.github.zawadamatt.demo.domain;

public class Product {

    private int creditID;
    private String productName;
    private int value;

    public Product(int creditID, String productName, int value) {
        this.creditID = creditID;
        this.productName = productName;
        this.value = value;
    }

    public int getCreditID() {
        return creditID;
    }

    public void setCreditID(int creditID) {
        this.creditID = creditID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
