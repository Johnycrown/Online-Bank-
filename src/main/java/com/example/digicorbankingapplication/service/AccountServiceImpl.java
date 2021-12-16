package com.example.digicorbankingapplication.service;

import com.example.digicorbankingapplication.data.model.Account;
import com.example.digicorbankingapplication.data.model.AccountTransaction;
import com.example.digicorbankingapplication.data.model.TransactionType;
import com.example.digicorbankingapplication.data.repositories.AccountRepository;
import com.example.digicorbankingapplication.data.repositories.AccountRepositoryImpl;
import com.example.digicorbankingapplication.dtos.requests.CreateAccountRequest;
import com.example.digicorbankingapplication.dtos.requests.DepositRequest;
import com.example.digicorbankingapplication.dtos.requests.WithdrawRequest;
import com.example.digicorbankingapplication.dtos.responses.AccountInformationResponse;
import com.example.digicorbankingapplication.dtos.responses.CreateAccountResponse;
import com.example.digicorbankingapplication.dtos.responses.TransactionResponse;
import com.example.digicorbankingapplication.exceptions.AccountDoesNotExistException;
import com.example.digicorbankingapplication.exceptions.BadRequestException;
import com.example.digicorbankingapplication.exceptions.DuplicateAccountNameException;
import com.example.digicorbankingapplication.exceptions.ExcessWithdrawnAmountException;
import com.example.digicorbankingapplication.utils.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {



    AccountRepository accountRepository = new AccountRepositoryImpl();

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest)  {

        Optional<Account>optionalAccount = Optional.ofNullable(accountRepository.findAccountByAccountNumber(createAccountRequest.getAccountName()));
        if(optionalAccount.isPresent()) throw new DuplicateAccountNameException(createAccountRequest.getAccountName() + "already exist");
        Account account = ModelMapper.map(createAccountRequest);

        String accountNumber = String.valueOf(UUID.randomUUID().getMostSignificantBits());
        accountNumber = accountNumber.substring(1,11);
        System.out.println(accountNumber);

        account.setAccountNumber(accountNumber);
        CreateAccountResponse response = new CreateAccountResponse();
         response.setResponseCode(200);
         response.setMessage("Account is successfully created");
         response.setSuccess(true);
         response.setAccountNumber(account.getAccountNumber());
        accountRepository.save(account);



        return response ;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public AccountInformationResponse getAccountInformation(String accountNumber, String password) {

        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new AccountDoesNotExistException("This account is does not exist");
        }

        if (!account.getAccountPassword().equals(password)){
            throw new BadRequestException("Username or password not found");
        }

        AccountInformationResponse response = AccountInformationResponse.builder()
                .responseCode(200)
                .message("Success")
                .success(true)
                .account(new Account(account.getAccountName(), account.getAccountNumber(), account.getAccountBalance())).build();
        return response;
    }


    @Override
    public TransactionResponse withdraw(WithdrawRequest request) throws AccountDoesNotExistException, ExcessWithdrawnAmountException {
        if(request.getWithdrawnAmount() < 1.0){
            throw new IllegalArgumentException("Can not withdraw below #1.00");
        }

        Account account = accountRepository.findAccountByAccountNumber(request.getAccountNumber());
        if(account == null){
            throw new AccountDoesNotExistException("This account does not exist");
        }

        if(account.getAccountBalance() - request.getWithdrawnAmount() < 500){
            throw new ExcessWithdrawnAmountException("Cannot withdraw amount because a limit " +
                    "of #500 should be left in the account");
        }


        Double newBalance = account.getAccountBalance() - request.getWithdrawnAmount();
        AccountTransaction transaction = AccountTransaction.builder()
                .transactionDate(LocalDate.now())
                .transactionType(TransactionType.WITHDRAWAL.name())
                .narration("transaction successful")
                .amount(request.getWithdrawnAmount())
                .accountBalance(newBalance)
                .build();

        account.addTransaction(transaction);
        account.setAccountBalance(newBalance);
        return new TransactionResponse(true, "1000 successfully withdrawn" +
                " new balance is: #" + account.getAccountBalance());
    }


    @Override
    public TransactionResponse deposit(DepositRequest request) {
        if(request.getAmount() < 1.0 || request.getAmount() > 1000000){
            throw new IllegalArgumentException("Cannot deposit below #1.00 or above #1,000,000");
        }

        Account account = accountRepository.findAccountByAccountNumber(request.getAccountNumber());
        Double newBalance = account.getAccountBalance() + request.getAmount();
        AccountTransaction transaction = AccountTransaction.builder()
                .transactionDate(LocalDate.now())
                .transactionType(TransactionType.Deposit.name())
                .narration("transaction successful")
                .amount(request.getAmount())
                .accountBalance(newBalance)
                .build();

        account.addTransaction(transaction);
        account.setAccountBalance(newBalance);
        return new TransactionResponse(true, "transaction successful");


    }

    @Override
    public List<AccountTransaction> getAllAccountTransactions(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        return account.getTransactions();
    }


}
