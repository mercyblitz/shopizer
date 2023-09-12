package com.salesmanager.shop.user.reposistory;


import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.model.entity.Criteria;
import com.salesmanager.shop.commons.model.entity.GenericEntityList;
import com.salesmanager.shop.user.entity.User;

public interface UserRepositoryCustom {
  
  GenericEntityList<User> listByCriteria(Criteria criteria) throws ServiceException;

}
