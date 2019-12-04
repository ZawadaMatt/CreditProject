package com.github.zawadamatt.demo.dto;

import com.github.zawadamatt.demo.dao.CreditDAO;
import com.github.zawadamatt.demo.dao.CustomerDAO;
import com.github.zawadamatt.demo.dao.ProductDAO;
import com.github.zawadamatt.demo.domain.Credit;
import com.github.zawadamatt.demo.domain.Customer;
import com.github.zawadamatt.demo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditViewDTO {

    private CreditDAO creditDAO;
    private CustomerDAO customerDAO;
    private ProductDAO productDAO;

    private String clientName;
    private String clientLastName;
    private String clientPesel;
    private String productName;
    private int productValue;
    private String creditName;


    @Autowired
    public CreditViewDTO(CreditDAO creditDAO, CustomerDAO customerDAO, ProductDAO productDAO) {
        this.creditDAO = creditDAO;
        this.customerDAO = customerDAO;
        this.productDAO = productDAO;
    }

    public CreditViewDTO(String clientName, String clientLastName, String clientPesel, String productName, int productValue, String creditName) {
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.clientPesel = clientPesel;
        this.productName = productName;
        this.productValue = productValue;
        this.creditName = creditName;
    }

    public List<CreditViewDTO> viewToModel() {
        List<Credit> creditList = creditDAO.getCredit();
        List<CreditViewDTO> creditViewDTOS = new ArrayList<>();

        for (Credit credit : creditList) {
            Customer customer = searchForCustomerByID(credit.getID());
            Product product = searchForProductByID(credit.getID());

            creditViewDTOS.add(new CreditViewDTO(customer.getFirstName(),
                    customer.getSurname(),
                    customer.getPesel(),
                    product.getProductName(),
                    product.getValue(),
                    credit.getCreditName()));
        }
        return creditViewDTOS;
    }

    public Customer searchForCustomerByID(int id) {
        List<Customer> customerList = customerDAO.getCustomer();
        for (Customer customer : customerList) {
            if (customer.getCreditID() == id) {
                return customer;
            }
        }
        return null;
    }

    public Product searchForProductByID(int id) {
        List<Product> productList = productDAO.getProduct();
        for (Product product : productList) {
            if (product.getCreditID() == id) {
                return product;
            }
        }
        return null;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientPesel() {
        return clientPesel;
    }

    public void setClientPesel(String clientPesel) {
        this.clientPesel = clientPesel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductValue() {
        return productValue;
    }

    public void setProductValue(int productValue) {
        this.productValue = productValue;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }
}



