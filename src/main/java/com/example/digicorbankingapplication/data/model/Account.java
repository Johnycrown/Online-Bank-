package com.example.digicorbankingapplication.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Account {
    @Id

    private String accountNumber;
    private  String accountName;
    private String accountPassword;
    private Double accountBalance;
    private List<AccountTransaction> transactions;

    public Account(String accountName, String accountNumber, Double initialDeposit) {

        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBalance = initialDeposit;
    }
    public void addTransaction(AccountTransaction transaction){
        if(transactions == null){
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
    }


}
