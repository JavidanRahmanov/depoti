package com.cavidanrahmanov.depoti.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FavoritesRequestDTO {

    private boolean isFavorite;

    public FavoritesRequestDTO(Long id, Long id1, String title) {
    }
}

