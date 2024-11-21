package com.banking.banking.Controller;

import com.banking.banking.Authentication.AuthenticationRequest;
import com.banking.banking.Authentication.AuthenticationRespond;
import com.banking.banking.Authentication.AuthenticationService;
import com.banking.banking.Bankrespond.BankRespond;
import com.banking.banking.DTO.AppUserDto;
import com.banking.banking.RegisterService.AppUserServices;
import com.banking.banking.Repository.CardRepository;
import com.banking.banking.model.AppUser;
import com.banking.banking.model.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin()
@RequestMapping("/auth")
public class AppUserController {

private  final AppUserServices services;
private final AuthenticationService authenticationService;
private final CardRepository cardRepository;

    public AppUserController(AppUserServices services, AuthenticationService authenticationService, CardRepository cardRepository) {
        this.services = services;
        this.authenticationService = authenticationService;
        this.cardRepository = cardRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<BankRespond> registerUser(@RequestBody AppUserDto userDto){
        return ResponseEntity.ok(services.createAccount(userDto));
    }

    
    @PostMapping("/login")
    public ResponseEntity<AuthenticationRespond> loginUser(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }


    @GetMapping("/greeting")
    public  String greatting(){
        return "welcome User";
        
        
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Optional<Card>> getCardByid(@PathVariable Long id){

        return ResponseEntity.ok(cardRepository.findById(id));
    }




}
