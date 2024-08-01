package com.havesweets.loans.dao;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Loans")
public class LoansDao extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private long loanId;
    @Column(name = "account_id")
    private long accountId;
    @Column(name = "loan_amount")
    private double loanAmount;
    @Column(name = "paid_amount")
    private double paid;
    @Column(name = "balance_amount")
    private double balance;
}
