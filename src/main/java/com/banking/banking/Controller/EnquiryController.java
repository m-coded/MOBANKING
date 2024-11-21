package com.banking.banking.Controller;

import com.banking.banking.Bankrespond.BankRespond;
import com.banking.banking.DTO.enquiryRequest;
import com.banking.banking.Enquiry.AppUserEnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api")
public class EnquiryController {
    @Autowired

    private AppUserEnquiryService appUserEnquiryService;

    @GetMapping("/balanceEnquiry")
    public BankRespond balanceEnquiry(@RequestBody enquiryRequest request){
        return appUserEnquiryService.BankUserBalanceEnquiry(request);

    }

    @GetMapping("/nameEnquiry")
    public  String nameEnquiry(@RequestBody  enquiryRequest request){
        return appUserEnquiryService.nameEnquiry(request);
    }
}
