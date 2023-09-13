package com.salesmanager.shop.commons.model.store;


import com.salesmanager.shop.commons.model.content.ReadableImage;
import com.salesmanager.shop.commons.model.entity.ReadableAddress;
import com.salesmanager.shop.commons.model.entity.ReadableAudit;
import com.salesmanager.shop.commons.model.entity.ReadableAuditable;
import com.salesmanager.shop.commons.model.entity.ReadableLanguage;

import java.io.Serializable;
import java.util.List;

public class ReadableMerchantStore extends MerchantStoreEntity implements ReadableAuditable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String currentUserLanguage;
	private ReadableAddress address;
	private ReadableImage logo;
	private ReadableAudit audit;
	private ReadableMerchantStore parent;

	private List<ReadableLanguage> supportedLanguages;

	public String getCurrentUserLanguage() {
		return currentUserLanguage;
	}

	public void setCurrentUserLanguage(String currentUserLanguage) {
		this.currentUserLanguage = currentUserLanguage;
	}

	public ReadableAddress getAddress() {
		return address;
	}

	public void setAddress(ReadableAddress address) {
		this.address = address;
	}

	public ReadableImage getLogo() {
		return logo;
	}

	public void setLogo(ReadableImage logo) {
		this.logo = logo;
	}

	public void setReadableAudit(ReadableAudit audit) {
		this.audit = audit;

	}

	public ReadableAudit getReadableAudit() {
		return this.audit;
	}

	public ReadableMerchantStore getParent() {
		return parent;
	}

	public void setParent(ReadableMerchantStore parent) {
		this.parent = parent;
	}

	public List<ReadableLanguage> getSupportedLanguages() {
		return supportedLanguages;
	}

	public void setSupportedLanguages(List<ReadableLanguage> supportedLanguages) {
		this.supportedLanguages = supportedLanguages;
	}


}
