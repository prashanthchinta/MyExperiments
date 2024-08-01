package com.havesweets.accounts.repository;

import com.havesweets.accounts.dao.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts,Long> {
    Accounts findByAccountNumber(long accountNumber);
}
