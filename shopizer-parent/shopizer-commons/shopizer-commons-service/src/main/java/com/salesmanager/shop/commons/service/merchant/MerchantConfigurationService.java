package com.salesmanager.shop.commons.service.merchant;


import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.system.MerchantConfig;
import com.salesmanager.shop.commons.entity.system.MerchantConfiguration;
import com.salesmanager.shop.commons.enums.MerchantConfigurationType;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityService;

import java.util.List;

public interface MerchantConfigurationService extends SalesManagerEntityService<Long, MerchantConfiguration> {

    MerchantConfiguration getMerchantConfiguration(String key, MerchantStore store) throws ServiceException;

    void saveOrUpdate(MerchantConfiguration entity) throws ServiceException;

    List<MerchantConfiguration> listByStore(MerchantStore store)
            throws ServiceException;

    List<MerchantConfiguration> listByType(MerchantConfigurationType type,
                                           MerchantStore store) throws ServiceException;

    MerchantConfig getMerchantConfig(MerchantStore store)
            throws ServiceException;

    void saveMerchantConfig(MerchantConfig config, MerchantStore store)
            throws ServiceException;

}
