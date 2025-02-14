package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import com.cavidanrahmanov.depoti.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {


    private final ListingService listingService;

//    // Şəkil yükləmə üçün endpoint
//    @PostMapping("/{id}/uploadImage")
//    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
//        try {
//            // Faylı yükləyəcəyimiz qovluq
//            String uploadDir = "uploads/";
//            File uploadFolder = new File(uploadDir);
//            if (!uploadFolder.exists()) {
//                uploadFolder.mkdirs(); // Qovluğu yarat
//            }
//
//            // Faylın tam yolu
//            String filePath = uploadDir + file.getOriginalFilename();
//            File destinationFile = new File(filePath);
//            file.transferTo(destinationFile); // Faylı saxla
//
//            // Fayl URL-i yaradılır
//            String imageUrl = "/uploads/" + file.getOriginalFilename();
//
//            // Listing obyektini tapıb imageUrl sahəsini doldur
//            Listing listing = listingRepository.findById(id)
//                    .orElseThrow(() -> new RuntimeException("Listing tapılmadı"));
//            listing.setImageUrl(imageUrl);
//            listingRepository.save(listing); // Yenilənmiş listing-i saxla
//
//            return ResponseEntity.ok("Şəkil uğurla yükləndi: " + imageUrl);
//        } catch (IOException e) {
//            return ResponseEntity.internalServerError().body("Şəkil yüklənmədi.");
//        }
//    }

    @PostMapping("/product")
    public ResponseEntity<?> addListing(@RequestPart Listing listing,
                                        @RequestPart MultipartFile imageFile)  {

        try {
            Listing listing1 = listingService.addListing(listing,imageFile);
            return new ResponseEntity<>(listing1, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listing/{listingId}/image")
    public ResponseEntity<byte[]> getImageByListingId(@PathVariable Long listingId){

        Listing listing = listingService.getListingById(listingId);
        byte[] imageFile = listing.getImageDate();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(listing.getImageType()))
                .body(imageFile);
    }
}

