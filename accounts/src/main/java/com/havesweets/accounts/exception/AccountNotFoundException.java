package com.havesweets.accounts.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String message)
    {
        super(message);
    }
}
