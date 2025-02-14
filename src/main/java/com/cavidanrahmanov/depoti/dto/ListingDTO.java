package com.cavidanrahmanov.depoti.dto;

import com.cavidanrahmanov.depoti.entity.Category;
import com.cavidanrahmanov.depoti.security.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListingDTO {

    private String title;
    private String description;
    private double price;
    private Category category;
    private User seller;
}
