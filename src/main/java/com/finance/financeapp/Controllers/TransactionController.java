package com.finance.financeapp.Controllers;

import com.finance.financeapp.DTO.TransactionDTO;
import com.finance.financeapp.entities.Transaction;
import com.finance.financeapp.DTO.TransactionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping
    public Transaction add(@RequestBody TransactionDTO dto) {
        return transactionService.addTransaction(dto, getCurrentUsername());
    }

    @GetMapping
    public List<Transaction> list() {
        return transactionService.getUserTransactions(getCurrentUsername());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transactionService.deleteTransaction(id, getCurrentUsername());
    }
}
