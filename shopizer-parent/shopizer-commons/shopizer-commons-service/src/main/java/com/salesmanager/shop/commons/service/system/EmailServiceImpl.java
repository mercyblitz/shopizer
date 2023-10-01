package com.salesmanager.shop.commons.service.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.shop.commons.constants.Constants;
import com.salesmanager.shop.commons.entity.email.Email;
import com.salesmanager.shop.commons.entity.email.EmailConfig;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.system.MerchantConfiguration;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.merchant.MerchantConfigurationService;
import com.salesmanager.shop.commons.util.HtmlEmailSender;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Inject
    private MerchantConfigurationService merchantConfigurationService;

    @Inject
    private HtmlEmailSender sender;

    @Override
    public void sendHtmlEmail(MerchantStore store, Email email) throws ServiceException, Exception {

        EmailConfig emailConfig = getEmailConfiguration(store);

        sender.setEmailConfig(emailConfig);
        sender.send(email);
    }

    @Override
    public EmailConfig getEmailConfiguration(MerchantStore store) throws ServiceException {

        MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(Constants.EMAIL_CONFIG, store);
        EmailConfig emailConfig = null;
        if (configuration != null) {
            String value = configuration.getValue();

            ObjectMapper mapper = new ObjectMapper();
            try {
                emailConfig = mapper.readValue(value, EmailConfig.class);
            } catch (Exception e) {
                throw new ServiceException("Cannot parse json string " + value);
            }
        }
        return emailConfig;
    }


    @Override
    public void saveEmailConfiguration(EmailConfig emailConfig, MerchantStore store) throws ServiceException {
        MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(Constants.EMAIL_CONFIG, store);
        if (configuration == null) {
            configuration = new MerchantConfiguration();
            configuration.setMerchantStore(store);
            configuration.setKey(Constants.EMAIL_CONFIG);
        }

        String value = emailConfig.toJSONString();
        configuration.setValue(value);
        merchantConfigurationService.saveOrUpdate(configuration);
    }

}
