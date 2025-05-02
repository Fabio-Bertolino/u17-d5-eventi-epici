package it.epicode.u17_d5_eventi_epici.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
