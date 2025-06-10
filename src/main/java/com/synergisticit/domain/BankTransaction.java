package com.synergisticit.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/10/2024
 */
@Entity
@Data
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long bankTractionFromAccount;//use for withdraw and transfer

    private Long bankTractionToAccount;//use for deposit and transfer

    @Enumerated(EnumType.STRING)
    private BankTransactionType bankTransactionType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime bankTransactionDateTime;

    private String comments;

    private Double amount;


}
