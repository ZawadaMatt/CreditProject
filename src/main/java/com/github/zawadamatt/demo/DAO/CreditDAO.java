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
        String sql = "CREATE SCHEMA if not exists CreditDB;";
        String sqlTable = "CREATE TABLE if not exists CreditDB.Credit (CreditName varchar(255), ID int);";
        jdbcTemplate.update(sql);
        jdbcTemplate.update(sqlTable);
    }

    public void saveCredit(Credit credit) {
        String sql = "INSERT INTO CreditDB.Credit VALUES (?,?);";
        jdbcTemplate.update(sql, credit.getCreditName(), credit.getID());
    }

    public List<Credit> getCredit() {
        String sql = "SELECT * FROM CreditDB.Credit;";
        List<Credit> creditList = jdbcTemplate.queryForList(sql, Credit.class);
        return creditList;
    }

    public int lastIndex() {
        String sql = "SELECT MAX(ID) from CreditDB.Credit;";
        Integer index = jdbcTemplate.queryForObject(sql, Integer.class);
        if (index == null) {
            return 0;
        }
        return index;
    }
}
