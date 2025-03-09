package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.dto.response.CategoryResponseDTO;
import com.cavidanrahmanov.depoti.entity.Category;
import com.cavidanrahmanov.depoti.entity.CategoryType;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import com.cavidanrahmanov.depoti.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ListingRepository listingRepo;

    @GetMapping("/head")
    public List<Category> getHeadCategories() {
        return categoryService.getHeadCategories();
    }

    // Seçilmiş head kateqoriyaya uyğun mid kateqoriyaları qaytarır
    @GetMapping("/{headId}/mid")
    public List<Category> getMidCategories(@PathVariable Long headId) {
        return categoryService.getMidCategories(headId);
    }

    // Seçilmiş mid kateqoriyaya uyğun sub kateqoriyaları qaytarır
    @GetMapping("/{midId}/sub")
    public List<Category> getSubCategories(@PathVariable Long midId) {
        return categoryService.getSubCategories(midId);
    }

//    // Bütün ana kateqoriyaların (kategoriya ağacı) DTO formatında əldə edilməsi
//    @GetMapping("/tree")
//    public ResponseEntity<List<CategoryResponseDTO>> getCategoryTree() {
//        return ResponseEntity.ok(categoryService.getCategoryTreeDTO());
//    }
//
//    @GetMapping("/{id}/items")
//    public ResponseEntity<List<Listing>> getListingByCategory(@PathVariable Long id) {
//        // 1) CategoryService ilə subtree-dən bütün ID-ləri topla
//        List<Long> catIds = categoryService.getSubCategoryIds(id);
//
//        // 2) Bütün bu kateqoriya ID-lərinə aid elanları bazadan tap
//        return ResponseEntity.ok(listingRepo.findByCategoryIdIn(catIds));
//    }
//
//
//    @PostMapping
//    public Category createCategory(@RequestParam String name,
//                                   @RequestParam CategoryType type,
//                                   @RequestParam(required = false) Long parentId) {
//        return categoryService.createCategory(name, type, parentId);
//    }
//
//    @GetMapping("/{parentId}/subcategories")
//    public List<Category> getSubCategories(@PathVariable Long parentId) {
//        return categoryService.getSubCategories(parentId);
//    }
}
