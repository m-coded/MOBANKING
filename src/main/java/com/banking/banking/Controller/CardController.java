package com.banking.banking.Controller;

import com.banking.banking.TransferService.UserService;
import com.banking.banking.model.AppUser;
import com.banking.banking.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {


    @Autowired
    private UserService userService;



    @PostMapping("/{id}/register")
    public Card addDebitCardToUser(@PathVariable Long id , @RequestBody Card card){
        return userService.addDebitCardToUser(id, card);
    }

    @GetMapping("/{id}")
    public AppUser getUserId(@PathVariable Long id ){
        return userService.getUserById(id);
    }
}
