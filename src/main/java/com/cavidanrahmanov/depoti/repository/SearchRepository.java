package com.cavidanrahmanov.depoti.repository;

import com.cavidanrahmanov.depoti.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Listing,Long> {

    @Query("SELECT l FROM Listing l WHERE " +
            "l.normalizedTitle LIKE CONCAT('%', :normalizedKeyword, '%') OR " +
            "l.normalizedDescription LIKE CONCAT('%', :normalizedKeyword, '%') OR " +
            "l.normalizedCategory LIKE CONCAT('%', :normalizedKeyword, '%')")
    List<Listing> searchListingsByNormalized(String normalizedKeyword);
}
