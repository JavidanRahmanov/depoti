package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.dto.request.ListingRequestDTO;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.exception.NotFoundException;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final ModelMapper modelMapper;

    public Listing addListing(ListingRequestDTO listingDTO, MultipartFile imageFile) throws IOException {
        Listing listing = modelMapper.map(listingDTO, Listing.class);
        listing.setImageName(imageFile.getOriginalFilename());
        listing.setImageType(imageFile.getContentType());
        listing.setImageDate(imageFile.getBytes());
        return listingRepository.save(listing);
    }

    public Listing getListingById(Long listingId) {
        return listingRepository.findById(listingId)
                .orElseThrow(() -> new NotFoundException("Listing not found with id: " + listingId));
    }

    @Transactional
    public void incrementViewCount(Long listingId) {
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new NotFoundException("Listing not found with id: " + listingId));
        listing.incrementViewCount(); // JPA otomatik güncelleyecek
    }

    public void activateListing(Long listingId) {
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new NotFoundException("Listing not found with id: " + listingId));
        listing.setExpired(false);
        listing.setExpiryDate(LocalDateTime.now().plusDays(10));
        listingRepository.save(listing);
    }
}
