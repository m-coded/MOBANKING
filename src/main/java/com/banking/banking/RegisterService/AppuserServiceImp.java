package com.banking.banking.RegisterService;

import com.banking.banking.Utils.AccountUtils;
import com.banking.banking.Bankrespond.AccountInfo;
import com.banking.banking.Bankrespond.BankRespond;
import com.banking.banking.DTO.AppUserDto;
import com.banking.banking.Repository.AppUserRepository;
import com.banking.banking.model.AppUser;
import com.banking.banking.model.Card;
import com.banking.banking.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AppuserServiceImp  implements  AppUserServices {


    private final AppUserRepository appUserRepository;
    private final com.banking.banking.JWT.jwtService jwtService;
    private final PasswordEncoder encoder;

    @Override
    public BankRespond createAccount(AppUserDto appUserDto) {
        if (appUserRepository.existsByEmail(appUserDto.getEmail())) {
            ;

            BankRespond responds = BankRespond.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS)
                    .responseMessages(AccountUtils.ACCOUNT_EXISTS_MESSAGES)
                    .accountInfo(null)
                    .build();

            return responds;
        }

        AppUser user = AppUser.builder()
                .firstname(appUserDto.getFirstname())
                .lastname(appUserDto.getLastname())
                .dob(appUserDto.getDob())
                .username(appUserDto.getEmail())
                .email(appUserDto.getEmail())
                .gender(appUserDto.getGender())
                .phoneNumber(appUserDto.getPhoneNumber())
                .password(encoder.encode(appUserDto.getPassword()))
                .accountBalance(BigDecimal.ZERO)
                .accountNumber(AccountUtils.generateAccountNumber())
                .roles(Role.USER)

                .build();
        AppUser users = appUserRepository.save(user);
        String token = jwtService.generateToken(user,generateExtraClaims(user));


        BankRespond respond = BankRespond.builder()
               .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
               .responseMessages(AccountUtils.ACCOUNT_CREATION_MESSAGES)
               .jwt(token)
               .accountInfo(AccountInfo.builder()
                       .accountName(appUserDto.getFirstname()+ " " + appUserDto.getLastname())
                       .accountNumber(users.getAccountNumber())
                       .accountBalance(users.getAccountBalance())
                       .build())
               .build();
       return respond;
    }




    private Map<String , Object> generateExtraClaims(AppUser user) {

        Map<String , Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getUsername());
        extraClaims.put("password", user.getPassword());
        return extraClaims;
    }


}