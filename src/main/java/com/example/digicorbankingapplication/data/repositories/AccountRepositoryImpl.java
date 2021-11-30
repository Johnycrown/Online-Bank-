package com.example.digicorbankingapplication.data.repositories;

import com.example.digicorbankingapplication.data.model.Account;
import org.springframework.stereotype.Repository;

import java.util.*;


public class AccountRepositoryImpl implements AccountRepository{

    private static final Map<String , Account> accounts = new HashMap();

    @Override
    public Account save(Account account) {
        String accountNumber = account.getAccountNumber();

        accounts.put(accountNumber,account);
        return accounts.get(account.getAccountNumber());
    }

    @Override
    public Account  findAccountByAccountNumber(String accountNumber) {

        return accounts.get(accountNumber);
    }

    @Override
    public void deleteAccountById(String id) {
        accounts.remove(id);

    }

    @Override
    public void deleteAccount(Account account) {
        accounts.remove(account.getAccountNumber());

    }

    @Override
    public List<Account> findAll() {
        List<Account> allAccount = new ArrayList<>();
        Set<String> keysIndb = accounts.keySet();
        for(String key : keysIndb){
            allAccount.add(accounts.get(key));
        }
        return allAccount;
    }

    @Override
    public String findAccountNumberByName(String accountName) {
        for (Account account : accounts.values()){
            if (account.getAccountName().equalsIgnoreCase(accountName)){
                return account.getAccountName();
            }
        }
        return null;

    }
}
