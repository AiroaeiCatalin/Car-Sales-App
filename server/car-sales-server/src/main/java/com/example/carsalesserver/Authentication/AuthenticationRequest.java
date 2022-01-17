package com.example.carsalesserver.Authentication;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthenticationRequest {
    private String userName;
    private String password;
}
