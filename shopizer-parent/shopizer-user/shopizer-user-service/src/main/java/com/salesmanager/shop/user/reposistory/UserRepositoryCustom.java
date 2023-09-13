package com.salesmanager.shop.user.reposistory;


import com.salesmanager.shop.commons.entity.user.User;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.model.entity.Criteria;
import com.salesmanager.shop.commons.model.entity.GenericEntityList;

public interface UserRepositoryCustom {

    GenericEntityList<User> listByCriteria(Criteria criteria) throws ServiceException;

}
