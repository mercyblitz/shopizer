package com.salesmanager.shop.commons.service.system;


import com.salesmanager.shop.commons.entity.email.Email;
import com.salesmanager.shop.commons.entity.email.EmailConfig;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.exception.ServiceException;


public interface EmailService {

	void sendHtmlEmail(MerchantStore store, Email email) throws ServiceException, Exception;
	
	EmailConfig getEmailConfiguration(MerchantStore store) throws ServiceException;
	
	void saveEmailConfiguration(EmailConfig emailConfig, MerchantStore store) throws ServiceException;
	
}
