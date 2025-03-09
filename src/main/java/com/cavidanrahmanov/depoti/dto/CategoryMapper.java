package com.cavidanrahmanov.depoti.dto;

import com.cavidanrahmanov.depoti.dto.response.CategoryResponseDTO;
import com.cavidanrahmanov.depoti.entity.Category;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CategoryMapper {
    public static CategoryResponseDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }

        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setParentId(category.getParent() != null ? category.getParent().getId() : null);

        dto.setSubCategories(
                category.getSubCategories() != null
                        ? category.getSubCategories().stream()
                        .map(CategoryMapper::toDTO) // Hər bir alt kateqoriya üçün recursive mapping
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        );

        return dto;
    }
}
