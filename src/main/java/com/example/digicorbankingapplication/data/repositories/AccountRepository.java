package com.example.digicorbankingapplication.data.repositories;

import com.example.digicorbankingapplication.data.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AccountRepository {

    Account save(Account account);
    Account findAccountByAccountNumber(String name);
    void deleteAccountById(String id);
    void deleteAccount(Account account);
    List<Account> findAll();
    String findAccountNumberByName(String accountName);
}
