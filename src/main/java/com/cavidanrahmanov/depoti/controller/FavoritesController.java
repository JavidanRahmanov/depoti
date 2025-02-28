package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.entity.Favorite;
import com.cavidanrahmanov.depoti.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String temporaryUserId,
            @RequestParam Long itemId) {

        if (userId == null && temporaryUserId == null) {
            return ResponseEntity.badRequest().body("User ID və ya Temporary User ID olmalıdır.");
        }

        favoriteService.addToFavorites(userId, temporaryUserId, itemId);
        return ResponseEntity.ok("Added to favorites");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favorite>> getUserFavorites(@PathVariable Long userId) {
        List<Favorite> favorites = favoriteService.getUserFavorites(userId);
        return ResponseEntity.ok(favorites);
    }

    @GetMapping("/temporary/{temporaryUserId}")
    public ResponseEntity<List<Favorite>> getTemporaryFavorites(@PathVariable String temporaryUserId) {
        List<Favorite> favorites = favoriteService.getTemporaryFavorites(temporaryUserId);
        return ResponseEntity.ok(favorites);
    }

    @GetMapping("/generate-temp-id")
    public ResponseEntity<String> generateTempUserId() {
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }
}
