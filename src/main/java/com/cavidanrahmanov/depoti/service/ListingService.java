package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.dto.request.ListingRequestDTO;
import com.cavidanrahmanov.depoti.entity.Category;
import com.cavidanrahmanov.depoti.entity.Image;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.exception.NotFoundException;
import com.cavidanrahmanov.depoti.repository.CategoryRepository;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import com.cavidanrahmanov.depoti.security.dto.UserRequestDTO;
import com.cavidanrahmanov.depoti.security.model.Users;
import com.cavidanrahmanov.depoti.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Listing addListing(ListingRequestDTO listingDTO, List<MultipartFile> images, UserRequestDTO currentUserDTO) throws IOException {
        Listing listing = modelMapper.map(listingDTO, Listing.class);

        // Hazırkı istifadəçini (auth olan user) təyin et
        Users currentUser = modelMapper.map(currentUserDTO,Users.class);
        listing.setSeller(currentUser);;

        // Resim işlemleri
        List<Image> imageList = new ArrayList<>();
        for (MultipartFile file : images) {
            Image image = new Image();
            image.setImageData(file.getBytes());
            image.setImageType(file.getContentType());
            image.setListing(listing);
            imageList.add(image);
        }

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
