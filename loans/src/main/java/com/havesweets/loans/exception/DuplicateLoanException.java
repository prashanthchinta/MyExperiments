package com.havesweets.loans.exception;

public class DuplicateLoanException extends RuntimeException{

    public DuplicateLoanException(String message)
    {
        super(message);
    }
}
