package com.salesmanager.shop.catalog.model;


import com.salesmanager.shop.commons.model.entity.ReadableList;

import java.util.ArrayList;
import java.util.List;

public class ReadableCatalogList extends ReadableList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ReadableCatalog> catalogs = new ArrayList<ReadableCatalog>();
	public List<ReadableCatalog> getCatalogs() {
		return catalogs;
	}
	public void setCatalogs(List<ReadableCatalog> catalogs) {
		this.catalogs = catalogs;
	}

}
