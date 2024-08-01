package com.havesweets.accounts.constants;

import org.springframework.http.HttpStatus;

public class AccountConstants {

    public static final int CREATED_STATUS = HttpStatus.CREATED.value();
    public static final String CREATED_MESSAGE = "Account Created Successfully.!";
    public static final int OK_STATUS = HttpStatus.OK.value();
    public static final String OK_MESSAGE = "Details Fetched Successfully.!";
    public static final int CONFLICT_STATUS = HttpStatus.CONFLICT.value();
    public static final String CONFLICT_MESSAGE = "Conflict occurs Please tye later.!";
    public static final int NOT_FOUND = HttpStatus.NOT_FOUND.value();
    public static final String NOT_FOUND_MESSAGE = "Account Record Not Found.!";
}
