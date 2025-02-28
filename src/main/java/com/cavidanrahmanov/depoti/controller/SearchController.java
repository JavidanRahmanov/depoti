package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<List<Listing>> searchListings(@PathVariable String keyword){

        return ResponseEntity.ok(searchService.searchListings(keyword));
    }
}
