package com.havesweets.accounts.feignClient;

import com.havesweets.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "LOANS", fallback = LoansFeignFallback.class)
public interface LoansFeignClient {

    @GetMapping(value = "/api/loans/fetchByAcc/{id}")
    public ResponseEntity<List<LoansDto>> getByAccountId(@RequestHeader("bankofbabji-correlation-id") String correlationId,
                                                         @PathVariable(name = "id") Long accId);

}
