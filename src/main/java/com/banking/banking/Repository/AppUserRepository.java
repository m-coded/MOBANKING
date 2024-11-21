package com.banking.banking.Repository;

import com.banking.banking.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser , Long > {
    boolean existsByaccountNumber(String accountNumber);

    boolean existsByEmail(String email);

    Optional<AppUser> findByUsername(String username);

    AppUser findByaccountNumber(String sourceAccountNumber);
}
