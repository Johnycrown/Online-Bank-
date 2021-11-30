package com.example.digicorbankingapplication.dtos.requests;


import lombok.Data;

@Data
public class CreateAccountRequest {
    String accountName;
    String accountPassword;
    Double initialDeposit;
}
