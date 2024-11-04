package com.banking.banking.Repository;

import com.banking.banking.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser , String > {
    AppUser findByuserName(String username);
}
