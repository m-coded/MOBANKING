package com.banking.banking.TransferService;

import com.banking.banking.Bankrespond.CardRespond;
import com.banking.banking.Repository.AppUserRepository;
import com.banking.banking.model.AppUser;
import com.banking.banking.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {

    Card addDebitCardToUser(Long id, Card card);
    AppUser getUserById(Long id);
}