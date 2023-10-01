package com.salesmanager.shop.catalog.service;

import com.salesmanager.shop.catalog.entity.Catalog;
import com.salesmanager.shop.catalog.repository.CatalogRepository;
import com.salesmanager.shop.catalog.repository.PageableCatalogRepository;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.Optional;

@Service("catalogService")
public class CatalogServiceImpl extends SalesManagerEntityServiceImpl<Long, Catalog> implements CatalogService {
	
	
	private CatalogRepository catalogRepository;
	
	@Autowired
	private PageableCatalogRepository pageableCatalogRepository;

	@Inject
	public CatalogServiceImpl(CatalogRepository repository) {
		super(repository);
		this.catalogRepository = repository;
	}

	@Override
	public Catalog saveOrUpdate(Catalog catalog, MerchantStore store) {
		catalogRepository.save(catalog);
		return catalog;
	}

	@Override
	public Page<Catalog> getCatalogs(MerchantStore store, Language language, String name, int page, int count) {
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableCatalogRepository.listByStore(store.getId(), name, pageRequest);
	}

	@Override
	public void delete(Catalog catalog) throws ServiceException {
		Assert.notNull(catalog,"Catalog must not be null");
		catalogRepository.delete(catalog);
	}

	@Override
	public Optional<Catalog> getById(Long catalogId, MerchantStore store) {
		return catalogRepository.findById(catalogId, store.getId());
	}

	@Override
	public Optional<Catalog> getByCode(String code, MerchantStore store) {
		return catalogRepository.findByCode(code, store.getId());
	}

	@Override
	public boolean existByCode(String code, MerchantStore store) {
		return catalogRepository.existsByCode(code, store.getId());
	}
	
	

}
