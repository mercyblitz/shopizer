package com.salesmanager.shop.catalog.model;


import com.salesmanager.shop.category.model.ReadableCategory;
import com.salesmanager.shop.commons.model.store.ReadableMerchantStore;

import java.util.ArrayList;
import java.util.List;

public class ReadableCatalog extends ReadableCatalogName {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ReadableMerchantStore store;

	private List<ReadableCategory> category = new ArrayList<ReadableCategory>();

	
/*	public List<ReadableCatalogCategoryEntry> getEntry() {
		return entry;
	}
	public void setEntry(List<ReadableCatalogCategoryEntry> entry) {
		this.entry = entry;
	}*/

	public ReadableMerchantStore getStore() {
		return store;
	}
	public void setStore(ReadableMerchantStore store) {
		this.store = store;
	}
	public List<ReadableCategory> getCategory() {
		return category;
	}
	public void setCategory(List<ReadableCategory> category) {
		this.category = category;
	}



}
