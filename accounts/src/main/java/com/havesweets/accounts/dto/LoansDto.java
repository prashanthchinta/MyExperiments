package com.havesweets.accounts.dto;

import lombok.Data;


@Data
public class LoansDto {

    private long loanId;
    private double loanAmount;
    private double paid;
    private double balance;
}
