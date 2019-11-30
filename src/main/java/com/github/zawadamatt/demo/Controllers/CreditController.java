package com.github.zawadamatt.demo.Controllers;

import com.github.zawadamatt.demo.DAO.CreditDAO;
import com.github.zawadamatt.demo.DAO.CustomerDAO;
import com.github.zawadamatt.demo.DAO.ProductDAO;
import com.github.zawadamatt.demo.domain.Credit;
import com.github.zawadamatt.demo.domain.Customer;
import com.github.zawadamatt.demo.domain.Product;
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

    @Autowired
    public CreditController(CreditDAO creditDAO, ProductDAO productDAO, CustomerDAO customerDAO) {
        this.creditDAO = creditDAO;
        this.productDAO = productDAO;
        this.customerDAO = customerDAO;
    }

    @GetMapping("/add-credit")
    public String Credit() {
        return "Credit";
    }

    @GetMapping("/get-credit")
    public String getCredit(Model model) {
        model.addAttribute("creditList", creditDAO.getCredit());
        model.addAttribute("customerList", customerDAO.getCustomer());
        model.addAttribute("productList", productDAO.getProduct());
        return "CreditList";
    }

    @PostMapping("/add-credit")
    public String addCredit(String name, String surname, String pesel, String productName, String productValue, String creditName) {
        int nextIndex = creditDAO.lastIndex() + 1;
        Credit credit = new Credit(creditName, nextIndex);
        Product product = new Product(nextIndex, productName, Integer.parseInt(productValue));
        Customer customer = new Customer(nextIndex, name, pesel, surname);
        creditDAO.saveCredit(credit);
        productDAO.saveProduct(product);
        customerDAO.saveCustomer(customer);
        return "CreditList";
    }
}
