package com.videogamesstore.api.dto;

import lombok.Data;

@Data
public class RegistroRequest {
    private String nickname;
    private String email;
    private String password;
}