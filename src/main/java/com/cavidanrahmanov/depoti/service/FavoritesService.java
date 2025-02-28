package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.security.model.Users;
import com.cavidanrahmanov.depoti.security.repository.UserRepository;
import com.cavidanrahmanov.depoti.dto.request.FavoritesRequestDTO;
import com.cavidanrahmanov.depoti.entity.Favorite;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.repository.FavoritesRepository;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoriteRepo;
    private final UserRepository userRepo;
    private final ListingRepository listingRepo;

    public List<FavoritesRequestDTO> getFavorites(Long userId) {
        return favoriteRepo.findByUserId(userId).stream()
                .map(fav -> new FavoritesRequestDTO(fav.getId(), fav.getListing().getId(), fav.getListing().getTitle()))
                .collect(Collectors.toList());
    }

    public void addFavorite(Long userId, Long listingId) {
        if (favoriteRepo.findByUserIdAndListingId(userId, listingId).isPresent()) {
            throw new RuntimeException("Listing is already in favorites!");
        }

        Users user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Listing listing = listingRepo.findById(listingId).orElseThrow(() -> new RuntimeException("Listing not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setListing(listing);
        favoriteRepo.save(favorite);
    }

    public void removeFavorite(Long userId, Long listingId) {
        Favorite favorite = favoriteRepo.findByUserIdAndListingId(userId, listingId)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        favoriteRepo.delete(favorite);
    }
}

