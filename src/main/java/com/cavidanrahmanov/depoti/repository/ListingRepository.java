package com.cavidanrahmanov.depoti.repository;

import com.cavidanrahmanov.depoti.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByCategoryId(Long categoryId);
    List<Listing> findBySellerId(Long sellerId);

    Listing getListingById(Long listingId);
}

