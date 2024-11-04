package com.banking.banking.Repository;

import com.banking.banking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction , String> {
}
