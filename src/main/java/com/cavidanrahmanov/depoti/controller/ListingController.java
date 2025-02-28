package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.dto.request.ListingRequestDTO;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/depoti/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @PostMapping("/listing")
    public ResponseEntity<?> addListing(@RequestPart ListingRequestDTO listingDTO,
                                        @RequestPart MultipartFile imageFile) {
        try {
            Listing listing = listingService.addListing(listingDTO, imageFile);
            return new ResponseEntity<>(listing, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listing/{listingId}/image")
    public ResponseEntity<byte[]> getImageByListingId(@PathVariable Long listingId) {
        Listing listing = listingService.getListingById(listingId);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(listing.getImageType()))
                .body(listing.getImageDate());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListing(@PathVariable Long id) {
        listingService.incrementViewCount(id);
        return ResponseEntity.ok(listingService.getListingById(id));
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<String> activateListing(@PathVariable Long id) {
        listingService.activateListing(id);
        return ResponseEntity.ok("Elan aktiv edildi!");
    }
}
