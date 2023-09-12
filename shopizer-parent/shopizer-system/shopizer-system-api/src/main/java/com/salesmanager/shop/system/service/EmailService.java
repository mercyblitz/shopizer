package com.salesmanager.shop.system.service;


import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.system.model.Email;
import com.salesmanager.shop.system.model.EmailConfig;

public interface EmailService {

    void sendHtmlEmail(MerchantStore store, Email email) throws ServiceException, Exception;

    EmailConfig getEmailConfiguration(MerchantStore store) throws ServiceException;

    void saveEmailConfiguration(EmailConfig emailConfig, MerchantStore store) throws ServiceException;

}
