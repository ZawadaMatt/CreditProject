package com.github.zawadamatt.demo.DAO;

import com.github.zawadamatt.demo.domain.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreditDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CreditDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void CreditInit() {
        String sql = "CREATE SCHEMA CreditDB CREATE TABLE Credit (CreditName varchar(255), ID int);";
        jdbcTemplate.update(sql);
    }

    public void addCredit(Credit credit) {
        String sql = "INSERT INTO Credit VALUES (?,?);";
        jdbcTemplate.update(sql, credit.getCreditName(), credit.getCreditID());
    }

    public List<Credit> getCredit() {
        String sql = "SELECT * FROM Credit;";
        List<Credit> creditList = jdbcTemplate.queryForList(sql, Credit.class);
        return creditList;
    }

    public int lastIndex() {
        String sql = "SELECT MAX(ID) from Credit;";
        int index = jdbcTemplate.queryForObject(sql, Integer.class);
        return index;
    }
}
