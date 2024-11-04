package com.banking.banking.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private  String uid;
    private String firstname;
    private String lastname;
    private  String userName;
    private String email;
    private String gender;
    private Date dob;
    private  long phoneNumber;
    private String tag;
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;




    @OneToOne(cascade = CascadeType.ALL,mappedBy = "owner")
    private  Card card;

    @OneToMany(mappedBy = "owner" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transaction> transaction;

    @OneToMany(mappedBy = "owner" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Account> account;
}
