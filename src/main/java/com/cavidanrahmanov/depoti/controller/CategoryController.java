package com.cavidanrahmanov.depoti.controller;

import com.cavidanrahmanov.depoti.entity.Category;
import com.cavidanrahmanov.depoti.entity.Listing;
import com.cavidanrahmanov.depoti.repository.CategoryRepository;
import com.cavidanrahmanov.depoti.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ListingRepository listingRepository;

    // Bütün ana kateqoriyalar (kategoriya ağacı)
    @GetMapping("/tree")
    public List<Category> getCategoryTree() {
        return categoryRepository.findByParentCategoryIsNull();
    }

    // Seçilmiş bir kateqoriyaya aid elanları gətir
    @GetMapping("/{id}/items")
    public List<Listing> getListingByCategory(@PathVariable Long id) {
        return listingRepository.findByCategoryId(id);
    }
}

