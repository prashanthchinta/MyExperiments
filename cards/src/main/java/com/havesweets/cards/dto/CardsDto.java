package com.havesweets.cards.dto;

import lombok.Data;

@Data
public class CardsDto {

    private long cardId;
    private long accountId;
    private String cardType;
    private String expiryDate;
}
