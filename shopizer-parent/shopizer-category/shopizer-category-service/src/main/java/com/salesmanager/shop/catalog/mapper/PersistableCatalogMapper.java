package com.salesmanager.shop.catalog.mapper;

import com.salesmanager.shop.catalog.entity.Catalog;
import com.salesmanager.shop.catalog.model.PersistableCatalog;
import com.salesmanager.shop.commons.entity.mapper.Mapper;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import org.springframework.stereotype.Component;

@Component
public class PersistableCatalogMapper implements Mapper<PersistableCatalog, Catalog> {

	@Override
	public Catalog convert(PersistableCatalog source, MerchantStore store, Language language) {
		Catalog c = new Catalog();
		return this.merge(source, c, store, language);
	}

	@Override
	public Catalog merge(PersistableCatalog source, Catalog destination, MerchantStore store, Language language) {
		
		
		destination.setCode(source.getCode());
		destination.setDefaultCatalog(source.isDefaultCatalog());
		destination.setId(source.getId());
		destination.setMerchantStore(store);
		destination.setVisible(source.isVisible());
		
		return destination;
	}

}
