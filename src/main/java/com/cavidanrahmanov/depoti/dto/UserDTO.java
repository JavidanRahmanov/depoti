package com.cavidanrahmanov.depoti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String phoneNumber;
    private List<ListingDTO> listings;  // Satıcının digər elanları
}

