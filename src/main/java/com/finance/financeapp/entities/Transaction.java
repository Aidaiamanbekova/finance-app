package com.finance.financeapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String description;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    private Category category;
}