package com.github.zawadamatt.demo.controllers;

import com.github.zawadamatt.demo.dao.CreditDAO;
import com.github.zawadamatt.demo.dao.CustomerDAO;
import com.github.zawadamatt.demo.dao.ProductDAO;
import com.github.zawadamatt.demo.domain.Credit;
import com.github.zawadamatt.demo.domain.Customer;
import com.github.zawadamatt.demo.domain.Product;
import com.github.zawadamatt.demo.dto.CreditViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreditController {

    private CreditDAO creditDAO;
    private ProductDAO productDAO;
    private CustomerDAO customerDAO;
    private CreditViewDTO creditViewDTO;

    @Autowired
    public CreditController(CreditDAO creditDAO, ProductDAO productDAO, CustomerDAO customerDAO, CreditViewDTO creditViewDTO) {
        this.creditDAO = creditDAO;
        this.productDAO = productDAO;
        this.customerDAO = customerDAO;
        this.creditViewDTO = creditViewDTO;
    }

    @GetMapping("/CreateCredit")
    public String Credit() {
        return "Credit";
    }

    @GetMapping("/GetCredit")
    public String getCredit(Model model) {
        model.addAttribute("listToView", creditViewDTO.viewToModel());
        return "CreditList";
    }

    @PostMapping("/CreateCredit")
    public String addCredit(String name, String surname, String pesel, String productName, String productValue, String creditName) {
        int nextIndex = creditDAO.lastIndex() + 1;
        Credit credit = new Credit(creditName, nextIndex);
        Product product = new Product(nextIndex, productName, Integer.parseInt(productValue));
        Customer customer = new Customer(nextIndex, name, pesel, surname);
        creditDAO.saveCredit(credit);
        productDAO.saveProduct(product);
        customerDAO.saveCustomer(customer);
        return "Credit";
    }
}
