package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.dto.response.CategoryResponseDTO;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import com.cavidanrahmanov.depoti.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/depoti/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ListingRepository listingRepo;

    // Bütün ana kateqoriyaların (kategoriya ağacı) DTO formatında əldə edilməsi
    @GetMapping("/tree")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoryTree() {
        return ResponseEntity.ok(categoryService.getCategoryTreeDTO());
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<Listing>> getListingByCategory(@PathVariable Long id) {
        // 1) CategoryService ilə subtree-dən bütün ID-ləri topla
        List<Long> catIds = categoryService.getSubCategoryIds(id);

        // 2) Bütün bu kateqoriya ID-lərinə aid elanları bazadan tap
        return ResponseEntity.ok(listingRepo.findByCategoryIdIn(catIds));
    }
}
