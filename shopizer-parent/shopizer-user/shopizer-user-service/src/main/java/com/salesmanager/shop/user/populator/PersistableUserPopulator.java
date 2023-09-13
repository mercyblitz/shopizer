package com.salesmanager.shop.user.populator;


import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.entity.user.Group;
import com.salesmanager.shop.commons.entity.user.User;
import com.salesmanager.shop.commons.exception.ConversionException;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.language.LanguageService;
import com.salesmanager.shop.commons.service.merchant.MerchantStoreService;
import com.salesmanager.shop.commons.util.AbstractDataPopulator;
import com.salesmanager.shop.user.model.PersistableGroup;
import com.salesmanager.shop.user.model.PersistableUser;
import com.salesmanager.shop.user.service.GroupService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersistableUserPopulator extends AbstractDataPopulator<PersistableUser, User> {

  @Inject
  private LanguageService languageService;
  
  @Inject
  private GroupService groupService;
  
  @Inject
  private MerchantStoreService merchantStoreService;
  
  @Inject
  @Named("passwordEncoder")
  private PasswordEncoder passwordEncoder;
  
  @Override
  public User populate(PersistableUser source, User target, MerchantStore store, Language language)
      throws ConversionException {
    Validate.notNull(source, "PersistableUser cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");

    if (target == null) {
      target = new User();
    }

    target.setFirstName(source.getFirstName());
    target.setLastName(source.getLastName());
    target.setAdminEmail(source.getEmailAddress());
    target.setAdminName(source.getUserName());
    if(!StringUtils.isBlank(source.getPassword())) {
      target.setAdminPassword(passwordEncoder.encode(source.getPassword()));
    }
    
    if(!StringUtils.isBlank(source.getStore())) {
        try {
			MerchantStore userStore = merchantStoreService.getByCode(source.getStore());
			target.setMerchantStore(userStore);
		} catch (ServiceException e) {
			throw new ConversionException("Error while reading MerchantStore store [" + source.getStore() + "]",e);
		}
    } else {
    	target.setMerchantStore(store);
    }
    
    
    target.setActive(source.isActive());
    
    Language lang = null;
    try {
      lang = languageService.getByCode(source.getDefaultLanguage());
    } catch(Exception e) {
      throw new ConversionException("Cannot get language [" + source.getDefaultLanguage() + "]",e);
    }

    // set default language
    target.setDefaultLanguage(lang);

    List<Group> userGroups = new ArrayList<Group>();
    List<String> names = new ArrayList<String>();
    for (PersistableGroup group : source.getGroups()) {
      names.add(group.getName());
    }
    try {
      List<Group> groups = groupService.listGroupByNames(names);
      for(Group g: groups) {
        userGroups.add(g);
      }
    } catch (Exception e1) {
      throw new ConversionException("Error while getting user groups",e1);
    }
    
    target.setGroups(userGroups);

    return target;
  }

  @Override
  protected User createTarget() {
    // TODO Auto-generated method stub
    return null;
  }

}
