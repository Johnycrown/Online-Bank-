package com.example.digicorbankingapplication.utils;

import com.example.digicorbankingapplication.data.model.Account;
import com.example.digicorbankingapplication.dtos.requests.CreateAccountRequest;
import com.example.digicorbankingapplication.dtos.responses.AccountInformationResponse;
import com.example.digicorbankingapplication.dtos.responses.CreateAccountResponse;

public class ModelMapper {

    public static Account map(CreateAccountRequest createAccountRequest) {

        Account myAccount = new Account();

        myAccount.setAccountName(createAccountRequest.getAccountName());
        myAccount.setAccountBalance(createAccountRequest.getInitialDeposit());
        myAccount.setAccountPassword(createAccountRequest.getAccountPassword());
        //setAccountNumber(createAccountRequest.getAccountNumber());
        return myAccount;
    }
}


//    public static CreateAccountResponse map(Account savedAccount){
//
//        CreateAccountResponse response = new CreateAccountResponse();
//
//        response.setResponseCode(savedAccount.());
//        response.setMessage(savedAccount.getMessage());
//        response.setSuccess(savedAccount.isSuccess());
//        return response;
//    }

//    public static AccountInformationResponse map(Class<AccountInformationResponse> accountInformationResponseClass, Account account) {
//    }
//}
