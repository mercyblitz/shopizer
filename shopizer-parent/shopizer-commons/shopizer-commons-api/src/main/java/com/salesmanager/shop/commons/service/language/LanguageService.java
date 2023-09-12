package com.salesmanager.shop.commons.service.language;


import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface LanguageService extends SalesManagerEntityService<Integer, Language> {

	Language getByCode(String code) throws ServiceException;

	Map<String, Language> getLanguagesMap() throws ServiceException;

	List<Language> getLanguages() throws ServiceException;

	Locale toLocale(Language language, MerchantStore store);

	Language toLanguage(Locale locale);
	
	Language defaultLanguage();
}
