package com.banking.banking.Bankrespond;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardInfo {

    private String cardHolderName;
    private String cardDetailsNumber;
    private int cardAccountBalance;
    private int cardCvvNumber;
    private LocalDateTime issAt;
    private LocalDateTime expAt;
    private String pin;
    private String billingAddress;
}
