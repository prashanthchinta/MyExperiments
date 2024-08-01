package com.havesweets.loans.repository;

import com.havesweets.loans.dao.LoansDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepo extends JpaRepository<LoansDao, Long> {
    List<LoansDao> findByAccountId(Long accId);
}
