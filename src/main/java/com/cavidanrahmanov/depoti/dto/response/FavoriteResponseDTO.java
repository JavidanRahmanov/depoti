package com.cavidanrahmanov.depoti.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FavoriteResponseDTO {

    private Long id;
    private boolean isFavorite;
}
