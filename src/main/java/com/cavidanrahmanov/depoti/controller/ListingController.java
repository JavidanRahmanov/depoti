package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.dto.request.ListingRequestDTO;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.security.dto.UserRequestDTO;
import com.cavidanrahmanov.depoti.service.ListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/depoti/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping(value = "/listing", consumes =  MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addListing(
            @RequestPart("listingDTO") @Valid ListingRequestDTO listingDTO,
            @RequestPart("images") List<MultipartFile> images,
            @AuthenticationPrincipal UserRequestDTO currentUserDTO) {
        try {

            Listing listing = listingService.addListing(listingDTO, images, currentUserDTO);
            return new ResponseEntity<>(listing, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
