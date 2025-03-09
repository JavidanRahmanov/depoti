package com.cavidanrahmanov.depoti.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private Long id;

    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    private List<Long> listingIds;  // Satıcının digər elanları
}

