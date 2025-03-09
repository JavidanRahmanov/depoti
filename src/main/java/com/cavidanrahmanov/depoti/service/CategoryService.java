package com.cavidanrahmanov.depoti.service;

import com.cavidanrahmanov.depoti.dto.response.CategoryResponseDTO;
import com.cavidanrahmanov.depoti.entity.Category;
import com.cavidanrahmanov.depoti.dto.CategoryMapper;
import com.cavidanrahmanov.depoti.entity.CategoryType;
import com.cavidanrahmanov.depoti.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Bütün head kateqoriyaları (HEAD_CATEGORY və parent null)
    public List<Category> getHeadCategories() {
        return categoryRepository.findByParentIsNullAndType(CategoryType.HEAD_CATEGORY);
    }

    // Seçilmiş head kateqoriyaya uyğun mid kateqoriyaları (type MID_CATEGORY)
    public List<Category> getMidCategories(Long headId) {
        return categoryRepository.findByParentIdAndType(headId, CategoryType.MID_CATEGORY);
    }

    // Seçilmiş mid kateqoriyaya uyğun sub kateqoriyaları (type SUB_CATEGORY)
    public List<Category> getSubCategories(Long midId) {
        return categoryRepository.findByParentIdAndType(midId, CategoryType.SUB_CATEGORY);
    }



//    // Recursive olaraq kategoriya ağacından bütün alt kateqoriya ID-lərini toplayır
//    public List<Long> getSubCategoryIds(Long categoryId) {
//        List<Long> ids = new ArrayList<>();
//        Category category = categoryRepo.findById(categoryId)
//                .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));
//
//        ids.add(category.getId());
//
//        for (Category sub : category.getSubCategories()) {
//            ids.addAll(getSubCategoryIds(sub.getId()));
//        }
//        return ids;
//    }
//
//    // Kategoriya ağacını DTO formatında əldə etmək üçün əlavə metod
//    public List<CategoryResponseDTO> getCategoryTreeDTO() {
//        List<Category> categories = categoryRepo.findByParentCategoryIsNull();
//        return categories.stream()
//                .map(CategoryMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    public Category createCategory(String name, CategoryType type, Long parentId) {
//        Category category = new Category();
//        category.setName(name);
//        category.setCategoryType(type);
//
//        if (parentId != null) {
//            Optional<Category> parentCategory = categoryRepo.findById(parentId);
//            parentCategory.ifPresent(category::setParentCategory);
//        }
//
//        return categoryRepo.save(category);
//    }
//
//    public List<Category> getSubCategories(Long parentId) {
//        return categoryRepo.findByParentCategoryId(parentId);
//    }
}
