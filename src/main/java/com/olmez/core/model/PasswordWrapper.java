package com.olmez.core.model;

import lombok.Data;

@Data
public class PasswordWrapper {
    private String username;
    private String rawPassword;
}
