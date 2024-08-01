package com.havesweets.loans.mapper;

import com.havesweets.loans.dao.LoansDao;
import com.havesweets.loans.dto.LoansDto;

public class LoansMapper {
    public static LoansDao toLoanDao(LoansDto loansDto, LoansDao loansDao) {

        loansDao.setLoanAmount(loansDto.getLoanAmount());
        loansDao.setPaid(loansDto.getPaid());
        loansDao.setAccountId(loansDto.getAccountId());
        loansDao.setBalance(loansDto.getBalance());
        return loansDao;
    }

    public static LoansDto toLoanDto(LoansDao loansDao, LoansDto loansDto) {
        loansDto.setLoanId(loansDao.getLoanId());
        loansDto.setLoanAmount(loansDao.getLoanAmount());
        loansDto.setPaid(loansDao.getPaid());
        loansDto.setAccountId(loansDao.getAccountId());
        loansDto.setBalance(loansDao.getBalance());

        return loansDto;
    }
}
