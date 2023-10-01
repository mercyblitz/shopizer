package com.salesmanager.shop.commons.util;

import com.salesmanager.shop.commons.entity.email.Email;
import com.salesmanager.shop.commons.entity.email.EmailConfig;

public interface EmailModule {
  
  void send(final Email email) throws Exception;

  void setEmailConfig(EmailConfig emailConfig);

}
