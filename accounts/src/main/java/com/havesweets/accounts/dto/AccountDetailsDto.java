package com.havesweets.accounts.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDetailsDto {
    private AccountsDto accountsDto;
    private List<CardsDto> cardsDtos;
    private List<LoansDto> loansDtos;
}
