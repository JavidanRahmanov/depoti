package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.dto.request.ListingRequestDTO;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.security.dto.UserRequestDTO;
import com.cavidanrahmanov.depoti.service.ListingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/depoti/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    private final ObjectMapper objectMapper; // from Jackson

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Listing> addListing(
            @Parameter(description = "Listing DTO in JSON format", required = true)
            @RequestPart("listingDTO") String listingDTOJson,

            @Parameter(description = "User DTO in JSON format", required = true)
            @RequestPart("currentUserDTO") String currentUserDTOJson,

            @Parameter(description = "Images", required = true)
            @RequestPart("images") List<MultipartFile> images
    ) {
        try {
            // Parse the JSON strings into DTO objects
            ListingRequestDTO listingDTO = objectMapper.readValue(listingDTOJson, ListingRequestDTO.class);
            UserRequestDTO userDTO = objectMapper.readValue(currentUserDTOJson, UserRequestDTO.class);

            Listing savedListing = listingService.addListing(listingDTO, images, userDTO);
            return ResponseEntity.ok(savedListing);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
