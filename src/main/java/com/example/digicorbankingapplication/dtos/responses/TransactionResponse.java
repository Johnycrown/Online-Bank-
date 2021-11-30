package com.example.digicorbankingapplication.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {
    private boolean success;
    private String message;
}
