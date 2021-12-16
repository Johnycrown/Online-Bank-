package com.example.digicorbankingapplication.dtos.responses;


import lombok.Data;

@Data
public class CreateAccountResponse {

    int responseCode;
    boolean success;
    String message;
    String accountNumber;
}
