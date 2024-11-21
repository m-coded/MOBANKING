package com.banking.banking.RegisterService;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Getter
public class AccountHelper {



    private  final Map<String, String>  CURRENCIES = Map.of(

      "USD", "United States Dollar",
       "EUR", "Euro",
        "GBP", "British Pound",
          "jpy", "Japanese Dollar",
            "NGN", "  Nigerian Naira",
            "INR", "Indian Rupee"



    );
}
