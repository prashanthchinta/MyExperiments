package com.havesweets.cards.mapper;

import com.havesweets.cards.dao.Cards;
import com.havesweets.cards.dto.CardsDto;

public class CardsMapper {

    /**
     *
     * @param dto
     * @param cards
     * @return
     */
    public static Cards toCards(CardsDto dto, Cards cards) {
        cards.setCardType(dto.getCardType());
        cards.setAccountId(dto.getAccountId());
        cards.setExpiryDate(dto.getExpiryDate());
        return cards;

    }

    /**
     *
     * @param card
     * @param cardsDto
     * @return
     */
    public static CardsDto toCardsDto(Cards card, CardsDto cardsDto) {
        cardsDto.setCardId(card.getCardId());
        cardsDto.setCardType(card.getCardType());
        cardsDto.setAccountId(card.getAccountId());
        cardsDto.setExpiryDate(card.getExpiryDate());
        return cardsDto;

    }
}
