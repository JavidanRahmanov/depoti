package com.cavidanrahmanov.depoti.entity;

import com.cavidanrahmanov.depoti.security.model.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "favorites", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "listing_id"})})
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String temporaryUserId;
    private Long listingId;

    public Favorite(Long userId, String temporaryUserId, Long itemId) {
        this.userId = userId;
        this.temporaryUserId = temporaryUserId;
        this.listingId = itemId;
    }
    
}

