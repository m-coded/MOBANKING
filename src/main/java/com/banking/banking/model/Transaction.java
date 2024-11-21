package com.banking.banking.model;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Transaction {








 @Enumerated(EnumType.STRING)
private Role role;
 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
 private AppUser owner;


}
