package com.havesweets.accounts.feignClient;

import com.havesweets.accounts.dto.LoansDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoansFeignFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<List<LoansDto>> getByAccountId(String correlationId, Long accId) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
    }
}
