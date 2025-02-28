package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingScheduler {

    private final ListingRepository listingRepo;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkExpiredListings() {
        List<Listing> activeListings = listingRepo.findAllByIsExpiredFalse();
        LocalDateTime now = LocalDateTime.now();

        for (Listing listing : activeListings) {
            if (listing.getExpiryDate().isBefore(now)) {
                listing.setExpired(true);
                listingRepo.save(listing);
            }
        }
    }
}
