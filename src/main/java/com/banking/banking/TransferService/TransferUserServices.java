package com.banking.banking.TransferService;

import com.banking.banking.Utils.AccountUtils;
import com.banking.banking.Bankrespond.AccountInfo;
import com.banking.banking.Bankrespond.BankRespond;
import com.banking.banking.DTO.TransferRequest;
import com.banking.banking.Repository.AppUserRepository;
import com.banking.banking.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferUserServices {

    private final AppUserRepository appUserRepository;



    public BankRespond Transfer(TransferRequest request) {
        boolean isDestinationAccountExist = appUserRepository.existsByaccountNumber(request.getDestinationAccountNumber());
        if (!isDestinationAccountExist) {
            return BankRespond.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessages(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        AppUser sourceAccountUser = appUserRepository.findByaccountNumber(request.getSourceAccountNumber());
        if (request.getAmount().compareTo(sourceAccountUser.getAccountBalance()) > 0) {
            return BankRespond.builder()
                    .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
                    .responseMessages(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountBalance(sourceAccountUser.getAccountBalance())
                            .build())
                    .build();
        }
        sourceAccountUser.setAccountBalance(sourceAccountUser.getAccountBalance().subtract(request.getAmount()));
        String sourceUsername = sourceAccountUser.getFirstname() + " " + sourceAccountUser.getLastname();
        appUserRepository.save(sourceAccountUser);


        AppUser detinationaccoutuser = appUserRepository.findByaccountNumber(request.getDestinationAccountNumber());
        detinationaccoutuser.setAccountBalance(detinationaccoutuser.getAccountBalance().add(request.getAmount()));
        //  String reciprentUsername =detinationaccoutuser.getFirstName() + " " + detinationaccoutuser.getLastName();
        appUserRepository.save(detinationaccoutuser);

        return BankRespond.builder()
                .responseCode(AccountUtils.TRANSFER_SUCCESSFUL_CODE)
                .responseMessages(AccountUtils.TRANSFER_SUCCESSFUL_MESSAGE)
                .accountInfo(null)

                .build();
    }

}
