package com.lzp.springbootlearn.jdbctemp.dao;

import com.lzp.springbootlearn.jdbctemp.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImp implements IAccountDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int add(Account account) {
        return jdbcTemplate.update("INSERT into account(name ,money) values (?,?)",account.getName(),account.getMoney());
    }

    @Override
    public int update(Account account) {
        return jdbcTemplate.update("UPDATE account SET NAME =?,money=?where id=?",account.getName(),account.getMoney(),account.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from TABLE account WHERE id=?",id);
    }

    @Override
    public Account findAccountById(int id) {
        List<Account> list = jdbcTemplate.query("SELECT * from account where id = ?",new Object[]{id},new BeanPropertyRowMapper(Account.class));

        if (list != null &&list.size()>0){
            Account account = list.get(0);
            return account;
        }else {
            return null;
        }
    }

    @Override
    public List<Account> findAccountList() {
        List<Account> list = jdbcTemplate.query("select * from account",new Object[]{},new BeanPropertyRowMapper(Account.class));
        return list;
    }
}
