package com.github.zawadamatt.demo.DAO;

import com.github.zawadamatt.demo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ProductInit() {
        String sql = "CREATE SCHEMA if not exists ProductDB;";
        String sqlTable = "CREATE TABLE if not exists ProductDB.Product (CreditID int, ProductName varchar(255), Value int);";
        jdbcTemplate.update(sql);
        jdbcTemplate.update(sqlTable);
    }

    public void saveProduct(Product product) {
        String sql = "INSERT INTO ProductDB.Product VALUES (?,?,?);";
        jdbcTemplate.update(sql, product.getCreditID(), product.getProductName(), product.getValue());
    }

    public List<Product> getProduct() {
        String sql = "SELECT * FROM ProductDB.Product;";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        return resultList.stream()
                .map(stringObjectMap ->
                        new Product((Integer) stringObjectMap.get("creditID"),
                                String.valueOf(stringObjectMap.get("productName")),
                                (Integer) stringObjectMap.get("value"))).collect(Collectors.toList());
    }

}
