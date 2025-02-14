package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    public Listing addListing(Listing listing, MultipartFile imageFile) throws IOException {

        listing.setImageName(imageFile.getOriginalFilename());// modelmapper istifadesi lazimdi
        listing.setImageType(imageFile.getContentType());
        listing.setImageDate(imageFile.getBytes());
        return listingRepository.save(listing);
    }

    public Listing getListingById(Long listingId) {
        return listingRepository.getListingById(listingId);
    }
}
