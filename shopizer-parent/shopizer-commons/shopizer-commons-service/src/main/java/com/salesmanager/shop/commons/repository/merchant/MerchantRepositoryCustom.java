package com.salesmanager.shop.commons.repository.merchant;


import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.merchant.MerchantStoreCriteria;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.model.entity.GenericEntityList;

public interface MerchantRepositoryCustom {

    GenericEntityList<MerchantStore> listByCriteria(MerchantStoreCriteria criteria) throws ServiceException;


}
