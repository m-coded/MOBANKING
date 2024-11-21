package com.banking.banking.Config;

import com.banking.banking.JWT.JwtAuthenticationFilter;
import com.banking.banking.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     @Autowired
    private AuthenticationProvider authenticationProvider;
     @Autowired
     private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sesson -> sesson.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( authConfig -> {
                    authConfig.requestMatchers(HttpMethod.POST,"/auth/register").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST,"/auth/login").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET,"/card/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET,"/auth/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST,"/card/{id}/register").permitAll();




                    authConfig.requestMatchers("/error").permitAll();


                    authConfig.requestMatchers(HttpMethod.GET,"/card/{id}").hasAnyAuthority(Role.USER.name());



                    authConfig.requestMatchers(HttpMethod.GET,"/auth/greeting").hasAnyAuthority(Role.USER.name());

                    authConfig.requestMatchers(HttpMethod.GET,"/Api/Transfer").hasAnyAuthority(Role.USER.name(),Role.ADMIN.name());


                    authConfig.anyRequest().denyAll();

                });






            return   httpSecurity.build();

    }
}
