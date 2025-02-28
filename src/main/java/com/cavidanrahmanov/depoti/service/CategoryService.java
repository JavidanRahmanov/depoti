package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.dto.response.CategoryResponseDTO;
import com.cavidanrahmanov.depoti.entity.Category;
import com.cavidanrahmanov.depoti.dto.CategoryMapper;
import com.cavidanrahmanov.depoti.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepo;

    // Recursive olaraq kategoriya ağacından bütün alt kateqoriya ID-lərini toplayır
    public List<Long> getSubCategoryIds(Long categoryId) {
        List<Long> ids = new ArrayList<>();
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));

        ids.add(category.getId());

        for (Category sub : category.getSubCategories()) {
            ids.addAll(getSubCategoryIds(sub.getId()));
        }
        return ids;
    }

    // Kategoriya ağacını DTO formatında əldə etmək üçün əlavə metod
    public List<CategoryResponseDTO> getCategoryTreeDTO() {
        List<Category> categories = categoryRepo.findByParentCategoryIsNull();
        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
