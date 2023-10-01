package com.salesmanager.shop.catalog.service;

import com.salesmanager.shop.catalog.entity.Catalog;
import com.salesmanager.shop.catalog.entity.CatalogCategoryEntry;
import com.salesmanager.shop.catalog.repository.CatalogEntryRepository;
import com.salesmanager.shop.catalog.repository.PageableCatalogEntryRepository;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("catalogEntryService")
public class CatalogEntryServiceImpl extends SalesManagerEntityServiceImpl<Long, CatalogCategoryEntry>
implements CatalogEntryService {
	
	@Autowired
	private PageableCatalogEntryRepository pageableCatalogEntryRepository;

	private CatalogEntryRepository catalogEntryRepository;
	
	@Inject
	public CatalogEntryServiceImpl(CatalogEntryRepository repository) {
		super(repository);
		this.catalogEntryRepository = repository;
	}

	@Override
	public void add(CatalogCategoryEntry entry, Catalog catalog) {
		entry.setCatalog(catalog);
		catalogEntryRepository.save(entry);
	}


	@Override
	public Page<CatalogCategoryEntry> list(Catalog catalog, MerchantStore store, Language language, String name, int page,
			int count) {
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableCatalogEntryRepository.listByCatalog(catalog.getId(), store.getId(), language.getId(), name, pageRequest);

	}

	@Override
	public void remove(CatalogCategoryEntry catalogEntry) throws ServiceException {
		catalogEntryRepository.delete(catalogEntry);
		
	}


}
