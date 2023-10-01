package com.salesmanager.shop.catalog.service;


import com.salesmanager.shop.catalog.entity.Catalog;
import com.salesmanager.shop.catalog.entity.CatalogCategoryEntry;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityService;
import org.springframework.data.domain.Page;

public interface CatalogEntryService extends SalesManagerEntityService<Long, CatalogCategoryEntry> {
	
	
	void add (CatalogCategoryEntry entry, Catalog catalog);
	
	void remove (CatalogCategoryEntry catalogEntry) throws ServiceException;
	
	Page<CatalogCategoryEntry> list(Catalog catalog, MerchantStore store, Language language, String name, int page, int count);

}
