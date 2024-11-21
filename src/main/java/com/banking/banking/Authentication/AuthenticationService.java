package com.banking.banking.Authentication;

import com.banking.banking.Repository.AppUserRepository;
import com.banking.banking.JWT.jwtService;
import com.banking.banking.model.AppUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {


    private final AuthenticationManager authenticationManager;

    private final AppUserRepository appUserRepository;

    private final jwtService jwtService;


    public AuthenticationService(AuthenticationManager authenticationManager, AppUserRepository appUserRepository, com.banking.banking.JWT.jwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }


    public AuthenticationRespond login(AuthenticationRequest request){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
        authenticationManager.authenticate(authenticationToken);

        AppUser user = (AppUser) appUserRepository.findByUsername(request.getEmail()).orElseThrow(() -> new RuntimeException("Couldn't find'"));

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        return new AuthenticationRespond(jwt);
    }

    private Map<String , Object> generateExtraClaims(AppUser user) {

        Map<String , Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getUsername());
        extraClaims.put("password", user.getPassword());
        return extraClaims;
    }

}
