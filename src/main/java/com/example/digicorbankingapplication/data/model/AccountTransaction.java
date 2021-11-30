package com.example.digicorbankingapplication.data.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class AccountTransaction {
    private LocalDate transactionDate;
    private String transactionType;
    private String narration;
    private Double amount;
    private Double accountBalance;
}
