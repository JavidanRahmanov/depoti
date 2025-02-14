package com.cavidanrahmanov.depoti.repository;

import com.cavidanrahmanov.depoti.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Listing,Integer> {

    @Query("SELECT l from Listing l WHERE " +
            "LOWER(l.title)  LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.description)  LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.category)  LIKE LOWER(CONCAT('%', :keyword, '%')) "
    )
    List<Listing> searchListings(String keyword);
}
