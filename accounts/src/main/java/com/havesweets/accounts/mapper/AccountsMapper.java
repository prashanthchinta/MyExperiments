package com.havesweets.accounts.mapper;

import com.havesweets.accounts.dao.Accounts;
import com.havesweets.accounts.dto.AccountsDto;

public class AccountsMapper {

    public static AccountsDto toAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountId(accounts.getAccountId());
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setDistrict(accounts.getDistrict());
        accountsDto.setCountry(accounts.getCountry());
        accountsDto.setPincode(accounts.getPincode());
        accountsDto.setStreet(accounts.getStreet());

        return accountsDto;
    }

    public static Accounts toAccounts(AccountsDto accountsDto, Accounts accounts) {
        if (accountsDto.getAccountId() != 0) {
            accounts.setAccountId(accountsDto.getAccountId());
        }
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setDistrict(accountsDto.getDistrict());
        accounts.setCountry(accountsDto.getCountry());
        accounts.setPincode(accountsDto.getPincode());
        accounts.setStreet(accountsDto.getStreet());
        return accounts;
    }
}