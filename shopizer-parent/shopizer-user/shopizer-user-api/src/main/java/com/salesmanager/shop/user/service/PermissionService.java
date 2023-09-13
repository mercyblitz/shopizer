package com.salesmanager.shop.user.service;

import com.salesmanager.shop.commons.entity.user.Group;
import com.salesmanager.shop.commons.entity.user.Permission;
import com.salesmanager.shop.commons.entity.user.PermissionCriteria;
import com.salesmanager.shop.commons.entity.user.PermissionList;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityService;

import java.util.List;


public interface PermissionService extends SalesManagerEntityService<Integer, Permission> {

    List<Permission> getByName();

    List<Permission> listPermission() throws ServiceException;

    Permission getById(Integer permissionId);

    List<Permission> getPermissions(List<Integer> groupIds) throws ServiceException;

    void deletePermission(Permission permission) throws ServiceException;

    PermissionList listByCriteria(PermissionCriteria criteria) throws ServiceException;

    void removePermission(Permission permission, Group group) throws ServiceException;

}
