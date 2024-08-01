package com.havesweets.loans.dto;

import lombok.Data;


@Data
public class LoansDto {

    private long loanId;
    private long accountId;
    private double loanAmount;
    private double paid;
    private double balance;
}
