package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.dto.request.ListingRequestDTO;
import com.cavidanrahmanov.depoti.dto.response.ListingResponseDTO;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.security.dto.UserRequestDTO;
import com.cavidanrahmanov.depoti.service.ListingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/depoti/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @PostMapping(value = "/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addListing(@RequestPart("listing") ListingRequestDTO listingRequestDTO,
                                        @RequestPart("image") MultipartFile imageFile) {

        try {
           ListingResponseDTO listingResponseDTO = listingService.addListing(listingRequestDTO,imageFile);
            return new ResponseEntity<>(listingResponseDTO,HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
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
