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
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String accountId;
    private Double balance;
    private String accountName;
    @Column(unique = true, nullable = false)
    private String accountNumber;
    private String currency;
    private String code;
    private String label;
    private char symbol;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;

    @OneToMany(mappedBy = "account" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transaction> transaction;
}
