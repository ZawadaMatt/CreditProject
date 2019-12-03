package com.github.zawadamatt.demo.DAO;

import com.github.zawadamatt.demo.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CustomerDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void CustomerInit() {
        String sql = "CREATE SCHEMA if not exists CustomerDB;";
        String sqlTable = "CREATE TABLE if not exists CustomerDB.Customer (CreditID int , FirstName varchar(255), Pesel varchar(255), Surname varchar(255));";
        jdbcTemplate.update(sql);
        jdbcTemplate.update(sqlTable);
    }

    public void saveCustomer(Customer customer) {
        String sql = "INSERT INTO CustomerDB.Customer VALUES (?,?,?,?);";
        jdbcTemplate.update(sql, customer.getCreditID(), customer.getFirstName(), customer.getPesel(), customer.getSurname());
    }

    public List<Customer> getCustomer() {
        String sql = "SELECT * FROM CustomerDB.Customer;";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        return resultList.stream()
                .map(stringObjectMap ->
                        new Customer((Integer) stringObjectMap.get("creditID"),
                                String.valueOf(stringObjectMap.get("firstName")),
                                String.valueOf(stringObjectMap.get("pesel")),
                                String.valueOf(stringObjectMap.get("surname")))).collect(Collectors.toList());
    }

}
