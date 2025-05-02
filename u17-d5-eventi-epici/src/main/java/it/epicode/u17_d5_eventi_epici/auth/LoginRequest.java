package it.epicode.u17_d5_eventi_epici.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
