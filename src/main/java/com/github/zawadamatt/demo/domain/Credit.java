package com.github.zawadamatt.demo.domain;

public class Credit {

    private String creditName;
    private int creditID;


    public Credit(String creditName, int creditID) {
        this.creditName = creditName;
        this.creditID = creditID;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public int getCreditID() {
        return creditID;
    }

    public void setCreditID(int creditID) {
        this.creditID = creditID;
    }
}
