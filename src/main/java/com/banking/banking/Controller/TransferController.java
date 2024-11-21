package com.banking.banking.Controller;

import com.banking.banking.Bankrespond.BankRespond;
import com.banking.banking.DTO.TransferRequest;
import com.banking.banking.TransferService.TransferUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api")
public class TransferController {

    @Autowired
    TransferUserServices transferUserServices;

    @PostMapping("/Transfer")
    public ResponseEntity<BankRespond> Transfer(@RequestBody TransferRequest request){
        return ResponseEntity.ok(transferUserServices.Transfer(request));
    }


}
