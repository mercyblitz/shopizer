package com.salesmanager.shop.user.service;

import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.user.User;
import com.salesmanager.shop.commons.entity.user.UserCriteria;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.model.entity.Criteria;
import com.salesmanager.shop.commons.model.entity.GenericEntityList;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityService;
import org.springframework.data.domain.Page;

import java.util.List;



public interface UserService extends SalesManagerEntityService<Long, User> {

  User getByUserName(String userName) throws ServiceException;
  User getByUserName(String userName, String storeCode) throws ServiceException;

  List<User> listUser() throws ServiceException;
  
  User getById(Long id, MerchantStore store);
  
  User getByPasswordResetToken(String storeCode, String token);

  /**
   * Create or update a User
   * 
   * @param user
   * @throws ServiceException
   */
  void saveOrUpdate(User user) throws ServiceException;

  List<User> listByStore(MerchantStore store) throws ServiceException;

  User findByStore(Long userId, String storeCode) throws ServiceException;

  @Deprecated
  GenericEntityList<User> listByCriteria(Criteria criteria) throws ServiceException;
  
  Page<User> listByCriteria(UserCriteria criteria, int page, int count) throws ServiceException;
  
  User findByResetPasswordToken (String userName, String token, MerchantStore store) throws ServiceException;




}
