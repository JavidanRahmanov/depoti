package com.cavidanrahmanov.depoti.dto.response;

import com.cavidanrahmanov.depoti.entity.Category;
import com.cavidanrahmanov.depoti.security.model.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ListingResponseDTO {

    private Long id;
    private String title;
    private String description;
    private double price;
    private Category category;
    private Users seller;
    private String imageUrl;
    private String imageName;
    private String imageType;
    private byte[] imageDate;
    private double imageNo;
    private String listingNumber;
    private String normalizedTitle;
    private String normalizedDescription;
    private String normalizedCategory;
    private boolean isExpired;
    private LocalDateTime createdAt;
    private LocalDateTime expiryDate;
    private int viewCount;
    private List<FavoriteResponseDTO> favorites;
}
