package org.application.kingphobe.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
}
