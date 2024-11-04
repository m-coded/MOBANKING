package com.banking.banking.Repository;

import com.banking.banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account ,  String> {
}
