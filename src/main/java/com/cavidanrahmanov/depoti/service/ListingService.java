package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.dto.request.ListingRequestDTO;
import com.cavidanrahmanov.depoti.dto.response.ListingResponseDTO;
import com.cavidanrahmanov.depoti.entity.Category;
//import com.cavidanrahmanov.depoti.entity.Image;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.exception.NotFoundException;
import com.cavidanrahmanov.depoti.repository.CategoryRepository;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import com.cavidanrahmanov.depoti.security.dto.UserRequestDTO;
import com.cavidanrahmanov.depoti.security.model.Users;
import com.cavidanrahmanov.depoti.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

//    public Listing addListing(ListingRequestDTO listingDTO, List<MultipartFile> images, UserRequestDTO currentUserDTO) throws IOException {
//        Listing listing = modelMapper.map(listingDTO, Listing.class);
//
//        // Set the seller (current authenticated user)
//        Users currentUser = modelMapper.map(currentUserDTO, Users.class);
//        listing.setSeller(currentUser);
//
//        // Save the listing first to get an ID (in case you need it for naming files)
//        Listing savedListing = listingRepository.save(listing);
//
//        // Save images as files and store file paths in a list
        //        List<String> imagePaths = new ArrayList<>();
//        for (MultipartFile file : images) {
//            String filePath = saveImage(file);  // Save image to file system
//            imagePaths.add(filePath);
//        }
//
//        // Store the image paths as a single string (comma-separated) in the listing
//        savedListing.setImagePaths(String.join(",", imagePaths));
//
//        return listingRepository.save(savedListing);
//    }
//
    @Value("${file.upload-dir}")
    private String uploadDir;

    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // Create directory if it doesn't exist
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename(); // Unique filename
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString(); // Return file path to store in DB
    }

    public ListingResponseDTO addListing(ListingRequestDTO listingRequestDTO, MultipartFile imageFile) throws IOException {

//        Listing savedListing = modelMapper.map(listingRequestDTO,Listing.class);
//        listingRepository.save(savedListing);
//       String filePath = saveImage(imageFile);
//       savedListing.setImagePaths(filePath);

        Listing savedListing = modelMapper.map(listingRequestDTO,Listing.class);
        savedListing.setImageName(imageFile.getOriginalFilename());
        savedListing.setImageType(imageFile.getContentType());
        savedListing.setImageDate(imageFile.getBytes());
        listingRepository.save(savedListing);
        return modelMapper.map(savedListing,ListingResponseDTO.class);
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
