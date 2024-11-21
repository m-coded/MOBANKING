package com.banking.banking.DTO;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDebitRequest {


    private String cardHolder;
    @Column(nullable = false, unique = true)
    private String cardNumber;
    private int cvv;
    private String pin;
    private String billingAddress;
    private BigDecimal amount;
}
