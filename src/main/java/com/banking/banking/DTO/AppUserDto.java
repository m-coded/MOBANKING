package com.banking.banking.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDto {

  private String  firstname;
    private String lastname;
    private  String username;
    private String email;
    private String gender;
    private String dob;
    private  String phoneNumber;
    private String password;
}
