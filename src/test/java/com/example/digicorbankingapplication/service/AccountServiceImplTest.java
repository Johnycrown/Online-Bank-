package com.example.digicorbankingapplication.service;

import com.example.digicorbankingapplication.data.model.Account;
import com.example.digicorbankingapplication.data.repositories.AccountRepository;
import com.example.digicorbankingapplication.data.repositories.AccountRepositoryImpl;
import com.example.digicorbankingapplication.dtos.requests.CreateAccountRequest;
import com.example.digicorbankingapplication.dtos.responses.CreateAccountResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    AccountRepository accountRepository = new AccountRepositoryImpl();
    private AccountService accountService = new AccountServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createAccount() throws Exception {
        //Account myaccount  = new Account();
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setAccountName("john ajibade");
        createAccountRequest.setAccountNumber(createAccountRequest.getAccountNumber());
        //System.out.println(createAccountRequest.getAccountNumber());
        createAccountRequest.setInitialDeposit(300000.00);
        createAccountRequest.setAccountPassword("456ugc");


        CreateAccountResponse savedAccount = accountService.createAccount(createAccountRequest);

        assertEquals(1, accountService.getAllAccounts().size());

    }
}