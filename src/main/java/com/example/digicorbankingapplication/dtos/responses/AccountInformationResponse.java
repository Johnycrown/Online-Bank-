package com.example.digicorbankingapplication.dtos.responses;

import com.example.digicorbankingapplication.data.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor

public class AccountInformationResponse {
    private int responseCode;
    private boolean success;
    private String message;
    private Account account;

}
