package com.cavidanrahmanov.depoti.security.dto;

import com.cavidanrahmanov.depoti.dto.request.FavoritesRequestDTO;
import com.cavidanrahmanov.depoti.dto.request.ListingRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    private String name;
    private String phoneNumber;
    private List<ListingRequestDTO> listings;
}
