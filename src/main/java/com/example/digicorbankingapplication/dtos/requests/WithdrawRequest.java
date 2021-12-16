package com.example.digicorbankingapplication.dtos.requests;

import lombok.Data;

@Data
public class WithdrawRequest {
    private String accountNumber;
    private String accountPassword;
    private Double withdrawnAmount;

}
