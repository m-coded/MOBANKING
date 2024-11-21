package com.banking.banking.Config;

import com.banking.banking.Repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppUserConfig {

    private final AppUserRepository appUserRepository;

    public AppUserConfig(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
     return    authenticationConfiguration.getAuthenticationManager();


    }
@Bean
    public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(passwordEncoder());
    return  provider;
}

@Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
}

    @Bean
    public UserDetailsService userDetailsService() {

        return email -> {

            return appUserRepository.findByUsername(email).orElseThrow(() -> new RuntimeException("User not found"));};

    }
}


