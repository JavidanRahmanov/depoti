package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.entity.Favorite;
import com.cavidanrahmanov.depoti.repository.FavoritesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoriteRepository;

    public String generateTemporaryUserId() {
        return UUID.randomUUID().toString();
    }

    public void addToFavorites(Long userId, String temporaryUserId, Long itemId) {
        if (userId != null) {
            Optional<Favorite> existingFavorite = favoriteRepository.findByUserIdAndListingId(userId, itemId);
            if (existingFavorite.isEmpty()) {
                favoriteRepository.save(new Favorite(userId, null, itemId));
            }
        } else {
            Optional<Favorite> existingFavorite = favoriteRepository.findByTemporaryUserIdAndListingId(temporaryUserId, itemId);
            if (existingFavorite.isEmpty()) {
                favoriteRepository.save(new Favorite(null, temporaryUserId, itemId));
            }
        }
    }

    public List<Favorite> getUserFavorites(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public List<Favorite> getTemporaryFavorites(String temporaryUserId) {
        return favoriteRepository.findByTemporaryUserId(temporaryUserId);
    }

    public void mergeFavoritesAfterLogin(String temporaryUserId, Long realUserId) {
        // 1. Real istifadəçinin artıq favoritləri var?
        List<Favorite> realUserFavorites = favoriteRepository.findByUserId(realUserId);

        // 2. Temporary favoritləri tap
        List<Favorite> tempFavorites = favoriteRepository.findByTemporaryUserId(temporaryUserId);

        // 3. Hər temporary favorit üçün yoxlayırıq ki, real user-də artıq var ya yox
        for (Favorite tempFav : tempFavorites) {
            boolean exists = realUserFavorites.stream()
                    .anyMatch(fav -> fav.getListingId().equals(tempFav.getListingId()));

            // 4. Əgər favorit real user-də yoxdursa, əlavə edirik
            if (!exists) {
                favoriteRepository.save(new Favorite(realUserId, null, tempFav.getListingId()));
            }
        }

        // 5. Temporary favoritləri bazadan silirik
        favoriteRepository.deleteAll(tempFavorites);
    }

}
