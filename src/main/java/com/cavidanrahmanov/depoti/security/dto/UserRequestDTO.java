package com.cavidanrahmanov.depoti.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class UserRequestDTO {

    private Long id;

    private String name;
    private String password;
    private String phoneNumber;
    private List<Long> listingIds;  // Satıcının digər elanları
}

