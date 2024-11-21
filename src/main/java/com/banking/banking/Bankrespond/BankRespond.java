package com.banking.banking.Bankrespond;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankRespond {

    private String responseCode;
    private String responseMessages;
    private AccountInfo accountInfo;
    private CardInfo cardInfo;

    public BankRespond(String jwt) {
        this.jwt = jwt;
    }

    private  String jwt;



}
