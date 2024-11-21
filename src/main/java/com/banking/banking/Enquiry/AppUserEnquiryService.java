package com.banking.banking.Enquiry;

import com.banking.banking.Bankrespond.AccountInfo;
import com.banking.banking.Bankrespond.BankRespond;
import com.banking.banking.DTO.enquiryRequest;
import com.banking.banking.Repository.AppUserRepository;
import com.banking.banking.Utils.AccountUtils;
import com.banking.banking.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserEnquiryService {

    private final AppUserRepository appUserRepository;

    public BankRespond BankUserBalanceEnquiry(enquiryRequest request) {
        boolean isAccountNumberExist = appUserRepository.existsByaccountNumber(request.getAccountNumber());
        if (!isAccountNumberExist) {
            BankRespond responds = BankRespond.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessages(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
            return responds;
        }
        //if user account exist//
        AppUser foundUser = appUserRepository.findByaccountNumber(request.getAccountNumber());
        return BankRespond.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessages(AccountUtils.ACCOUNT_FOUND_SUCCESS)
                .accountInfo(AccountInfo.builder()
                        .accountName(foundUser.getFirstname() + " " + foundUser.getLastname())
                        .accountBalance(foundUser.getAccountBalance())
                        .accountNumber(request.getAccountNumber())

                        .build())

                .build();
    }


    public String nameEnquiry(enquiryRequest request) {
        boolean isAccountNumberExist = appUserRepository.existsByaccountNumber(request.getAccountNumber());
        if (!isAccountNumberExist) {
            return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;
        }
        AppUser foundUser = appUserRepository.findByaccountNumber(request.getAccountNumber());
        return foundUser.getFirstname() + " " + foundUser.getLastname();


    }
}
