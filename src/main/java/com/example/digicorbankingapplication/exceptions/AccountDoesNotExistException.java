package com.example.digicorbankingapplication.exceptions;

public class AccountDoesNotExistException extends DigicoreBankingException{
    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
