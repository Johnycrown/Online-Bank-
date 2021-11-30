package com.example.digicorbankingapplication.controllers;


import com.example.digicorbankingapplication.dtos.requests.CreateAccountRequest;
import com.example.digicorbankingapplication.dtos.requests.DepositRequest;
import com.example.digicorbankingapplication.dtos.requests.WithdrawRequest;
import com.example.digicorbankingapplication.dtos.responses.TransactionResponse;
import com.example.digicorbankingapplication.exceptions.AccountDoesNotExistException;
import com.example.digicorbankingapplication.exceptions.DigicoreBankingException;
import com.example.digicorbankingapplication.exceptions.ExcessWithdrawnAmountException;
import com.example.digicorbankingapplication.exceptions.WrongPasswordException;
import com.example.digicorbankingapplication.service.AccountService;
import com.example.digicorbankingapplication.service.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    AccountService accountService = new AccountServiceImpl();

    @PostMapping("/api/vi/create_Account")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest request) throws Exception {
        try {
            return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
        }
        catch (DigicoreBankingException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/withdrawal")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequest request){
        try {
            return new ResponseEntity<>(accountService.withdraw(request), HttpStatus.OK);
        } catch (AccountDoesNotExistException | ExcessWithdrawnAmountException | IllegalArgumentException e) {
            return new ResponseEntity<>(new TransactionResponse(false, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest request){
        try{
            return new ResponseEntity<>(accountService.deposit(request), HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(new TransactionResponse(false, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/account_info/{accountNumber}")
    public ResponseEntity<?> getAccountInformation(@PathVariable String accountNumber, @RequestParam String password) {
        try {
            return new ResponseEntity<>(accountService.getAccountInformation(accountNumber, password), HttpStatus.OK);
        } catch (AccountDoesNotExistException | WrongPasswordException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

        @GetMapping("/account_statement/{accountNumber}")
        public ResponseEntity<?> getAccountStatement(@PathVariable String accountNumber){
            return new ResponseEntity<>(accountService.getAllAccountTransactions(accountNumber), HttpStatus.OK);
        }
    }



