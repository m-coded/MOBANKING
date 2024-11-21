package com.banking.banking.Repository;

import com.banking.banking.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);
}
