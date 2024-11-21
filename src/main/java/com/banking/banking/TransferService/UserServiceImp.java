package com.banking.banking.TransferService;

import com.banking.banking.Bankrespond.CardRespond;
import com.banking.banking.Repository.AppUserRepository;
import com.banking.banking.Repository.CardRepository;
import com.banking.banking.Utils.AccountUtils;
import com.banking.banking.model.AppUser;
import com.banking.banking.model.Card;
import com.banking.banking.model.CardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class UserServiceImp  implements UserService {

    private final AppUserRepository appUserRepository;
    private final CardRepository cardRepository;

    @Override

    //add debit card to a user account
    public Card addDebitCardToUser(Long id, Card card) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        card.setCardHolder(card.getCardHolder());
        card.setCardNumber(String.valueOf(AccountUtils.generateCardNumber()));
        card.setPin(card.getPin());
        card.setBillingAddress(card.getBillingAddress());
        card.setCvv(String.valueOf(AccountUtils.generateCardCvvNumber()));
        card.setExpirationDate(String.valueOf(LocalDateTime.now().plusYears(3)));
        card.setCardType(CardType.DebitCard);
        card.setOwner(appUser);
        return cardRepository.save(card);
    }

    @Override
    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

}
