package com.example.digicorbankingapplication.service;

import com.example.digicorbankingapplication.data.model.Account;
import com.example.digicorbankingapplication.data.model.AccountTransaction;
import com.example.digicorbankingapplication.dtos.requests.CreateAccountRequest;
import com.example.digicorbankingapplication.dtos.requests.DepositRequest;
import com.example.digicorbankingapplication.dtos.requests.WithdrawRequest;
import com.example.digicorbankingapplication.dtos.responses.AccountInformationResponse;
import com.example.digicorbankingapplication.dtos.responses.CreateAccountResponse;
import com.example.digicorbankingapplication.dtos.responses.TransactionResponse;
import com.example.digicorbankingapplication.exceptions.AccountDoesNotExistException;
import com.example.digicorbankingapplication.exceptions.ExcessWithdrawnAmountException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) throws Exception;

    List<Account>getAllAccounts();
    AccountInformationResponse getAccountInformation(String accountNumber,  String password );
    TransactionResponse withdraw(WithdrawRequest request) throws AccountDoesNotExistException, ExcessWithdrawnAmountException;
    TransactionResponse deposit(DepositRequest request);
    List<AccountTransaction> getAllAccountTransactions(String accountNumber);

}
