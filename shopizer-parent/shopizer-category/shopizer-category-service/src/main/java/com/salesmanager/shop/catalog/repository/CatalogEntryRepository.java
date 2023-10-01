package com.salesmanager.shop.catalog.repository;

import com.salesmanager.shop.catalog.entity.CatalogCategoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogEntryRepository extends JpaRepository<CatalogCategoryEntry, Long> {

}
