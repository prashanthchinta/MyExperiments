package com.havesweets.accounts.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;
    @Column(name = "account_number")
    private long accountNumber;
    @Column(name = "account_type")
    private String accountType;
    private String street;
    private String district;
    private String country;
    private int pincode;

}
