package org.jsc.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private Long empresaId;
}