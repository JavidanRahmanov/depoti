package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.dto.request.FavoritesRequestDTO;
import com.cavidanrahmanov.depoti.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depoti/favorites")
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoritesRequestDTO>> getFavorites(@PathVariable Long userId) {
        return ResponseEntity.ok(favoritesService.getFavorites(userId));
    }

    @PostMapping("/{userId}/{listingId}")
    public ResponseEntity<String> addFavorite(@PathVariable Long userId, @PathVariable Long listingId) {
        favoritesService.addFavorite(userId, listingId);
        return ResponseEntity.ok("Added to favorites");
    }

    @DeleteMapping("/{userId}/{listingId}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long userId, @PathVariable Long listingId) {
        favoritesService.removeFavorite(userId, listingId);
        return ResponseEntity.ok("Removed from favorites");
    }
}
