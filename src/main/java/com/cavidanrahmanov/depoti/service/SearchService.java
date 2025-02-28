package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepo;

    public List<Listing> searchListings(String keyword) {
        String normalizedKeyword = normalize(keyword);
        List<Listing> results = searchRepo.searchListingsByNormalized(normalizedKeyword);
        return results;
    }

    private String normalize(String input) {
        if (input == null) return null;
        String lower = input.toLowerCase();
        lower = lower.replace("ə", "e")
                .replace("ç", "c")
                .replace("ş", "s")
                .replace("ğ", "g")
                .replace("ı", "i");
        return lower;
    }
}
