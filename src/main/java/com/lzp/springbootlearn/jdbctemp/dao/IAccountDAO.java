package com.lzp.springbootlearn.jdbctemp.dao;

import com.lzp.springbootlearn.jdbctemp.entity.Account;

import java.util.List;

public interface IAccountDAO {
    int add(Account account);
    int update(Account account);
    int delete(int id);
    Account findAccountById(int id);
    List<Account> findAccountList();
}
