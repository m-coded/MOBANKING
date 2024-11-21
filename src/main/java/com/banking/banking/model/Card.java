package com.banking.banking.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class Card {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardHolder;
    private String cardNumber;
    private String  amount;
    private String cvv;
    private String pin;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @CreationTimestamp// Example: "Debit", "Credit"
    private String expirationDate;
    private String billingAddress;
    @ManyToOne
   @JoinColumn(name = "owner_id", nullable = false)
    private AppUser owner;


}
