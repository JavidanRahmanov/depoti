package com.cavidanrahmanov.depoti.repository;

import com.cavidanrahmanov.depoti.entity.Category;
import com.cavidanrahmanov.depoti.entity.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentIsNull();
    List<Category> findByParentId(Long parentId);

    // Head kateqoriyaları: parent null və tip HEAD_CATEGORY
    List<Category> findByParentIsNullAndType(CategoryType categoryType);

    // Mid və Sub kateqoriyaları: verilmiş head(or mid) kateqoriyanın id-sinə sahib olan və tip MID_CATEGORY(or Sub_Category)
    List<Category> findByParentIdAndType(Long parentCategoryId, CategoryType categoryType);

}

