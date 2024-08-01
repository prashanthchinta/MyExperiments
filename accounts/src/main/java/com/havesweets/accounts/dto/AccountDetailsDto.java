package com.havesweets.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AccountDetailsDto {
    private AccountsDto accountsDto;
    private List<CardsDto> cardsDtos;
    private List<LoansDto> loansDtos;
}
