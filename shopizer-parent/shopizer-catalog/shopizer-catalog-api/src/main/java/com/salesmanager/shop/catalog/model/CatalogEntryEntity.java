package com.salesmanager.shop.catalog.model;

import com.salesmanager.shop.commons.model.entity.Entity;

public class CatalogEntryEntity extends Entity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String catalog;
	private boolean visible;
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
