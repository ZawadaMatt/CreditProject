package com.github.zawadamatt.demo.domain;

public class Credit {

    private String creditName;
    private int ID;


    public Credit(String creditName, int ID) {
        this.creditName = creditName;
        this.ID = ID;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
