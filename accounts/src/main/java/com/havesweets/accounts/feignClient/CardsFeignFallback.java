package com.havesweets.accounts.feignClient;

import com.havesweets.accounts.dto.CardsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardsFeignFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<List<CardsDto>> getCardsByAccId(String correlationId, Long accId) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<CardsDto>());
    }
}
