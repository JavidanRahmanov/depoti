package com.cavidanrahmanov.depoti.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    private String username;
    private String password;
    private String temporaryUserId; // Qeydiyyatsız favoritləri merge etmək üçün
}