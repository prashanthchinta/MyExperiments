package com.havesweets.cards.service;

import com.havesweets.cards.dao.Cards;
import com.havesweets.cards.dto.CardsDto;
import com.havesweets.cards.exception.CardsNotFoundException;
import com.havesweets.cards.mapper.CardsMapper;
import com.havesweets.cards.repository.CardsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardsService {
    @Autowired
    CardsRepo cardsRepo;

    /**
     * @param cardId
     * @return
     * @throws AccountNotFoundException
     */
    public CardsDto getCardsById(Long cardId) throws CardsNotFoundException {
        Optional<Cards> cards = cardsRepo.findById(cardId);
        if (cards.isPresent()) {
            Cards card = cards.get();
            CardsDto cardsDto = CardsMapper.toCardsDto(card, new CardsDto());
            return cardsDto;
        } else {
            throw new CardsNotFoundException("Related Card Record Not found !!!");
        }
    }

    /**
     * @return
     */
    public List<CardsDto> getCards() throws CardsNotFoundException {

        List<CardsDto> response = new ArrayList<>();
        List<Cards> allCards = cardsRepo.findAll();
        if(allCards.size()>0) {
            for (Cards cards : allCards) {
                CardsDto cardsDto = CardsMapper.toCardsDto(cards, new CardsDto());
                response.add(cardsDto);
            }
            return response;
        }else {
            throw new CardsNotFoundException("Cards Not Present for the User !!");
        }
    }

    /**
     * @param cardsDto
     * @return
     */
    public CardsDto addCards(CardsDto cardsDto) {
        Cards card = CardsMapper.toCards(cardsDto, new Cards());
        Cards result = cardsRepo.save(card);
        CardsDto response = CardsMapper.toCardsDto(result, new CardsDto());
        response.setCardId(result.getCardId());
        return response;
    }

    /**
     * @param cardsDto
     * @return
     */
    public CardsDto updateCards(CardsDto cardsDto) throws CardsNotFoundException {
        Cards card = null;

        Optional<Cards> cards = cardsRepo.findById(cardsDto.getCardId());
        if (cards.isPresent()) {
            card = CardsMapper.toCards(cardsDto, cards.get());
            card = cardsRepo.save(card);

            return CardsMapper.toCardsDto(card, new CardsDto());
        } else {
            throw new CardsNotFoundException("Cards information not found for Update !!");
        }
    }

    /**
     * @param cardId
     * @return
     */
    public String deleteCards(Long cardId) throws CardsNotFoundException {
        if (cardsRepo.existsById(cardId)) {
            cardsRepo.deleteById(cardId);
            return "Successfully Deleted the Card!!!";
        } else {
            throw new CardsNotFoundException("Cards Record Not Found For Delete !!");
        }
    }

    /**
     *
     * @param accId
     * @return
     * @throws CardsNotFoundException
     */
    public List<CardsDto> getCardsByAccId(Long accId) {
        Optional<List<Cards>> cards = cardsRepo.findByAccountId(accId);
        List<CardsDto> cardsDtos = new ArrayList<>();
        if (cards.isPresent()) {
            for (Cards card : cards.get()) {
                CardsDto cardsDto = CardsMapper.toCardsDto(card, new CardsDto());
                cardsDtos.add(cardsDto);
            }

            return cardsDtos;
        }else {
            return cardsDtos;
        }
    }
}
