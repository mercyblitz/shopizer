package com.salesmanager.shop.category.repository;


import com.salesmanager.shop.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageableCategoryRepositoryCustom {

    Page<Category> listByStore(Integer storeId, Integer languageId, String name, Pageable pageable);

}
