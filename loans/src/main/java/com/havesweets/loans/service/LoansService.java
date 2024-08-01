package com.havesweets.loans.service;

import com.havesweets.loans.controller.LoansController;
import com.havesweets.loans.dao.LoansDao;
import com.havesweets.loans.dto.LoansDto;
import com.havesweets.loans.exception.DuplicateLoanException;
import com.havesweets.loans.exception.LoansNotFoundException;
import com.havesweets.loans.mapper.LoansMapper;
import com.havesweets.loans.repository.LoansRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoansService {

    @Autowired
    LoansRepo loansRepo;

    public LoansDto addLoans(LoansDto loansDto) {
        boolean exists = loansRepo.existsById(loansDto.getLoanId());
        if(exists)
        {
            throw new DuplicateLoanException("Loan Already Exists Please Pay First !!!!");
        }
        LoansDao loansDao = LoansMapper
                .toLoanDao(loansDto, new LoansDao());
        loansDao = loansRepo.save(loansDao);
        LoansDto response = LoansMapper
                .toLoanDto(loansDao, new LoansDto());
        return response;
    }

    public LoansDto updateLoans(LoansDto loansDto) throws LoansNotFoundException {
        LoansDto response = new LoansDto();
        if (loansRepo.existsById(loansDto.getLoanId())) {
            LoansDao loansDao = LoansMapper
                    .toLoanDao(loansDto, new LoansDao());
            loansDao.setLoanId(loansDto.getLoanId());
            loansDao = loansRepo.save(loansDao);
            response = LoansMapper
                    .toLoanDto(loansDao, new LoansDto());
        }else
        {
            throw new LoansNotFoundException("Loan Record Not Found Please Verify !!");
        }
        return response;
    }

    public List<LoansDto> getAllLoans() throws LoansNotFoundException {
        List<LoansDao> loansDaoList = loansRepo.findAll();
        if(loansDaoList.size()>0) {
            List<LoansDto> response = new ArrayList<>();
            for (LoansDao dao : loansDaoList) {
                LoansDto loansDto = LoansMapper
                        .toLoanDto(dao, new LoansDto());
                response.add(loansDto);
            }
            return response;
        }else {
            throw new LoansNotFoundException("No Loans Found for the Customer !!");
        }
    }

    public LoansDto getLoanById(Long loanId) throws LoansNotFoundException {
        Optional<LoansDao> loansDao = loansRepo.findById(loanId);
        if (loansDao.isPresent()) {
            return LoansMapper
                    .toLoanDto(loansDao.get(), new LoansDto());
        } else {
            throw new LoansNotFoundException("No Record Found for the Specified ID");
        }
    }

    public String deleteLoanById(Long loanId) throws LoansNotFoundException {
        if (loansRepo.existsById(loanId)) {
            loansRepo.deleteById(loanId);
            return "Successfully deleted loan record!!!!";
        } else {
            throw new LoansNotFoundException("No Record Found for Delete !!");
        }
    }

    public List<LoansDto> getByAccountId(Long accId, String correlationId) {

        List<LoansDao> loansDaoList = loansRepo.findByAccountId(accId);
        List<LoansDto> response = new ArrayList<>();
        for (LoansDao dao : loansDaoList) {
            LoansDto loansDto = LoansMapper
                    .toLoanDto(dao, new LoansDto());
            response.add(loansDto);
        }
        return response;
    }
}
