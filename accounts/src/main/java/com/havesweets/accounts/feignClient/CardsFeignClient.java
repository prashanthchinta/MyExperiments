package com.havesweets.accounts.feignClient;

import com.havesweets.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "CARDS", fallback = CardsFeignFallback.class)
public interface CardsFeignClient {

    @GetMapping(value = "/api/cards/fetchByAcc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardsDto>> getCardsByAccId(@RequestHeader("bankofbabji-correlation-id") String correlationId
            , @PathVariable(name = "id") Long accId);

    }
