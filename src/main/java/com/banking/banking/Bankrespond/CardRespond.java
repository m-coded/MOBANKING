package com.banking.banking.Bankrespond;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRespond {

    private String responseCode;
    private String responseMessages;
    private CardInfo cardInfo;
}
