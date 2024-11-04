package com.banking.banking.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class Transaction {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
    private String txId;


    private  Double amount;
    private  Double txFee;
    private String sender;
    private  String receiver;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
