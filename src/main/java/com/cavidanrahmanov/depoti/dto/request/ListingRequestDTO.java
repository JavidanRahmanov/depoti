package com.cavidanrahmanov.depoti.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class ListingRequestDTO {

    private String userName;
    private String title;
    private String description;
    private double price;
    private Long categoryId;  // Category yerine sadece ID
    private Long sellerId;
}
