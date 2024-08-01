package com.havesweets.accounts.serviceImpl;

import com.havesweets.accounts.constants.AccountConstants;
import com.havesweets.accounts.controller.AccountsController;
import com.havesweets.accounts.dao.Accounts;
import com.havesweets.accounts.dto.AccountDetailsDto;
import com.havesweets.accounts.dto.AccountsDto;
import com.havesweets.accounts.dto.CardsDto;
import com.havesweets.accounts.dto.LoansDto;
import com.havesweets.accounts.exception.AccountNotFoundException;
import com.havesweets.accounts.exception.DuplicateAccounException;
import com.havesweets.accounts.feignClient.CardsFeignClient;
import com.havesweets.accounts.feignClient.LoansFeignClient;
import com.havesweets.accounts.mapper.AccountsMapper;
import com.havesweets.accounts.repository.AccountsRepo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsService {

    private static final Logger logger = LoggerFactory.getLogger(AccountsService.class);

    private AccountsRepo accountsRepo;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;


    public AccountsDto getAccounts(String accountId) throws AccountNotFoundException {
        Optional<Accounts> accounts = accountsRepo.findById(Long.valueOf(accountId));
        if (accounts.isEmpty()) {
            throw new AccountNotFoundException(AccountConstants.NOT_FOUND_MESSAGE);
        }
        return AccountsMapper.toAccountsDto(accounts.get(), new AccountsDto());
    }

    public List<AccountsDto> getAccountList() {
        List<Accounts> accounts = accountsRepo.findAll();
        List<AccountsDto> list = new ArrayList<>();
        for (Accounts account : accounts) {
            AccountsDto accountsDto = AccountsMapper.toAccountsDto(account, new AccountsDto());
            list.add(accountsDto);
        }
        return list;
    }

    public AccountsDto addAccount(AccountsDto accountsDto) {
        long accountNumber = accountsDto.getAccountNumber();
        Accounts existsAccount = accountsRepo.findByAccountNumber(accountNumber);
        if(existsAccount!=null)
        {
            throw new DuplicateAccounException(AccountConstants.CONFLICT_MESSAGE);
        }else {
            Accounts accounts = AccountsMapper.toAccounts(accountsDto, new Accounts());
            Accounts response = accountsRepo.save(accounts);
            AccountsDto dto = AccountsMapper.toAccountsDto(response,new AccountsDto());
            return dto;
        }
    }

    public Accounts updateAccount(AccountsDto accountsDto) throws AccountNotFoundException {
        boolean exists = accountsRepo.existsById(accountsDto.getAccountId());
        Accounts accounts = null;
        if (exists) {
            accounts = AccountsMapper.toAccounts(accountsDto, new Accounts());
            accounts = accountsRepo.save(accounts);
        }else {
            throw new AccountNotFoundException(AccountConstants.NOT_FOUND_MESSAGE);
        }
        return accounts;

    }

    public void deleteAccount(long id) throws AccountNotFoundException {
        Optional<Accounts> accounts = accountsRepo.findById(id);
        if (accounts.isPresent())
        {
            accountsRepo.delete(accounts.get());
        }else {
            throw new AccountNotFoundException(AccountConstants.NOT_FOUND_MESSAGE);
        }
    }


    public AccountDetailsDto getAccountDetails(String correlationId,Long accountId) throws AccountNotFoundException {

        logger.debug("BankofBabji Correlation Id  : "+correlationId);

        Optional<Accounts> accounts = accountsRepo.findById(accountId);
        AccountDetailsDto accountDetailsDto = new AccountDetailsDto();
        if (accounts.isEmpty()) {
            throw new AccountNotFoundException("Account Not found for the id!!!");
        } else {
            Accounts accounts1 = accounts.get();
            AccountsDto accountsDto = AccountsMapper.toAccountsDto(accounts1, new AccountsDto());

            ResponseEntity<List<CardsDto>> responseEntity =
                    cardsFeignClient.getCardsByAccId(correlationId,accountId);
            ResponseEntity<List<LoansDto>> responseEntity1 =
                    loansFeignClient.getByAccountId(correlationId,accountId);

            accountDetailsDto.setAccountsDto(accountsDto);
            accountDetailsDto.setCardsDtos(responseEntity.getBody());
            accountDetailsDto.setLoansDtos(responseEntity1.getBody());
        }
        return accountDetailsDto;
    }
}
