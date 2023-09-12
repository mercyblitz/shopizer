package com.salesmanager.shop.commons.entity.merchant;

import com.salesmanager.shop.commons.model.entity.Criteria;

public class MerchantStoreCriteria extends Criteria {
	
	private boolean retailers = false;
	private boolean stores = false;

	public boolean isRetailers() {
		return retailers;
	}

	public void setRetailers(boolean retailers) {
		this.retailers = retailers;
	}

	public boolean isStores() {
		return stores;
	}

	public void setStores(boolean stores) {
		this.stores = stores;
	}
	
	


}
