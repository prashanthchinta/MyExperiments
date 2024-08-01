package com.havesweets.cards.dao;

import jakarta.persistence.*;
import lombok.*;

import javax.print.attribute.standard.MediaSize;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cards extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private long cardId;
    @Column(name = "account_id")
    private long accountId;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "expiry_date")
    private String expiryDate;

}
