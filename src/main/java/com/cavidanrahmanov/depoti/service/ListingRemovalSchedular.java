package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListingRemovalSchedular {

    private final ListingRepository listingRepo;

    @Scheduled(cron = "0 0 0 * * ?")
    public void removeOldExpiredListings() {
        LocalDateTime now = LocalDateTime.now();
        // Məsələn, expiryDate + 30 gün keçibsə
        LocalDateTime threshold = now.minusDays(30);

        List<Listing> oldExpiredListings = listingRepo
                .findAllByIsExpiredTrue()  // Expired olanların hamısını al
                .stream()
                .filter(listing -> listing.getExpiryDate().isBefore(threshold))
                .collect(Collectors.toList());

        listingRepo.deleteAll(oldExpiredListings);
    }
}
