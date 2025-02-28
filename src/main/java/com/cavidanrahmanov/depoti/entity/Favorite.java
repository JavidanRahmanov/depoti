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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;

    private boolean isFavorite = false;
}

