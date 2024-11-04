package com.banking.banking.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class Card {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
    private String cardId;

    private String cardHolder;
    @Column(nullable = false, unique = true)
    private long cardNumber;
    private String balance;
    @CreationTimestamp
    private  LocalDate  issAt;
    @UpdateTimestamp
    private LocalDateTime expAt;
    private String cvv;
    private String pin;
    private String billingAddress;
      @OneToOne
      @JoinColumn(name = "owner_id")
    private AppUser owner;

      @OneToMany(mappedBy = "card" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transaction> transaction;

}
