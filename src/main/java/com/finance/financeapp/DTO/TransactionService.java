package com.finance.financeapp.DTO;

import com.finance.financeapp.entities.*;
import com.finance.financeapp.repositories.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    public TransactionService(TransactionRepository transactionRepo, UserRepository userRepo, CategoryRepository categoryRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    public Transaction addTransaction(TransactionDTO dto, String username) {
        User user = userRepo.findByUsername(username).orElseThrow();
        Category category = categoryRepo.findById(dto.getCategoryId()).orElseThrow();
        Transaction t = new Transaction();
        t.setAmount(dto.getAmount());
        t.setDescription(dto.getDescription());
        t.setDate(dto.getDate() == null ? LocalDateTime.now() : dto.getDate());
        t.setType(TransactionType.valueOf(dto.getType()));
        t.setUser(user);
        t.setCategory(category);
        return transactionRepo.save(t);
    }

    public List<Transaction> getUserTransactions(String username) {
        User user = userRepo.findByUsername(username).orElseThrow();
        return transactionRepo.findByUser(user);
    }

    public void deleteTransaction(Long id, String username) {
        Transaction t = transactionRepo.findById(id).orElseThrow();
        if (!t.getUser().getUsername().equals(username)) throw new RuntimeException("Not owner");
        transactionRepo.deleteById(id);
    }
}