package com.havesweets.cards.repository;

import com.havesweets.cards.dao.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardsRepo extends JpaRepository<Cards, Long> {
    Optional<List<Cards>> findByAccountId(Long accId);
}
