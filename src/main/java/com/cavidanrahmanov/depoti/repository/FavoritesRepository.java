package com.cavidanrahmanov.depoti.repository;

import com.cavidanrahmanov.depoti.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);
    List<Favorite> findByTemporaryUserId(String temporaryUserId);
    Optional<Favorite> findByTemporaryUserIdAndListingId(String temporaryUserId, Long listingId);
    Optional<Favorite> findByUserIdAndListingId(Long userId, Long listingId);
}


