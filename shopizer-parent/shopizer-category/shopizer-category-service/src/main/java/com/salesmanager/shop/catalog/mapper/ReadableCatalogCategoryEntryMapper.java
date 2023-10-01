package com.salesmanager.shop.catalog.mapper;


import com.salesmanager.shop.catalog.entity.CatalogCategoryEntry;
import com.salesmanager.shop.catalog.model.ReadableCatalogCategoryEntry;
import com.salesmanager.shop.category.model.ReadableCategory;
import com.salesmanager.shop.commons.entity.mapper.Mapper;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ConversionRuntimeException;
import com.salesmanager.shop.commons.util.ImageFilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReadableCatalogCategoryEntryMapper implements Mapper<CatalogCategoryEntry, ReadableCatalogCategoryEntry> {
	
	
	@Autowired
	private ReadableCategoryMapper readableCategoryMapper;
	
	@Autowired
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public ReadableCatalogCategoryEntry convert(CatalogCategoryEntry source, MerchantStore store, Language language) {
		ReadableCatalogCategoryEntry destination = new ReadableCatalogCategoryEntry();
		return merge(source, destination, store, language);
	}

	@Override
	public ReadableCatalogCategoryEntry merge(CatalogCategoryEntry source, ReadableCatalogCategoryEntry destination, MerchantStore store,
											  Language language) {
		ReadableCatalogCategoryEntry convertedDestination = Optional.ofNullable(destination)
				.orElse(new ReadableCatalogCategoryEntry());
		
		try {
			//ReadableProductPopulator readableProductPopulator = new ReadableProductPopulator();
			//readableProductPopulator.setimageUtils(imageUtils);
			//readableProductPopulator.setPricingService(pricingService);
			
			//ReadableProduct readableProduct = readableProductPopulator.populate(source.getProduct(), store, language);
			ReadableCategory readableCategory = readableCategoryMapper.convert(source.getCategory(), store, language);

			convertedDestination.setCatalog(source.getCatalog().getCode());

			convertedDestination.setId(source.getId());
			convertedDestination.setVisible(source.isVisible());
			convertedDestination.setCategory(readableCategory);
			//destination.setProduct(readableProduct);
			return convertedDestination;
		} catch (Exception e) {
			throw new ConversionRuntimeException("Error while creating ReadableCatalogEntry", e);
		}
	}
}
