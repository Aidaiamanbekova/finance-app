package com.finance.financeapp.repositories;

import com.finance.financeapp.entities.Transaction;
import com.finance.financeapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserAndDateBetween(User user, LocalDateTime start, LocalDateTime end);
    List<Transaction> findByUser(User user);
}
