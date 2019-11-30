package com.github.zawadamatt.demo.domain;

public class Customer {

    private int creditID;
    private String firstName;
    private String pesel;
    private String surname;

    public Customer(int creditID, String firstName, String pesel, String surname) {
        this.creditID = creditID;
        this.firstName = firstName;
        this.pesel = pesel;
        this.surname = surname;
    }

    public int getCreditID() {
        return creditID;
    }

    public void setCreditID(int creditID) {
        this.creditID = creditID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
