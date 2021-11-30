//package com.example.digicorbankingapplication.service;
//
//import com.example.digicorbankingapplication.data.model.Account;
//import com.example.digicorbankingapplication.data.repositories.AccountRepository;
//
//public class AccountDetailsService {
//    private final AccountRepository accountRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
//        Account account = accountRepository.findByAccountNumber(accountNumber);
//
//        return new User(account.getAccountNumber(), account.getAccountPassword(), new ArrayList<>());
//    }
//}
