package com.salesmanager.shop.commons.security.services;


import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;

public interface CredentialsService {
	
	/**
	 * Password validation with specific rules
	 * @param password
	 * @param repeatPassword
	 * @param store
	 * @param language
	 * @throws CredentialsException
	 */
	void validateCredentials(String password, String repeatPassword, MerchantStore store, Language language) throws CredentialsException;
	
	/**
	 * Generates password based on specific rules
	 * @param store
	 * @param language
	 * @return
	 * @throws CredentialsException
	 */
	String generatePassword(MerchantStore store, Language language) throws CredentialsException;

}
