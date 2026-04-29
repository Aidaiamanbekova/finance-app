package com.finance.financeapp.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
    private String type; // "INCOME" or "EXPENSE"
    private Long categoryId;

}
