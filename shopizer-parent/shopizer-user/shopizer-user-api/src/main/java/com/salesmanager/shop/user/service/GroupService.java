package com.salesmanager.shop.user.service;

import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityService;
import com.salesmanager.shop.user.entity.Group;
import com.salesmanager.shop.user.entity.GroupType;

import java.util.List;
import java.util.Set;


public interface GroupService extends SalesManagerEntityService<Integer, Group> {


    List<Group> listGroup(GroupType groupType) throws ServiceException;

    List<Group> listGroupByIds(Set<Integer> ids) throws ServiceException;

    List<Group> listGroupByNames(List<String> names) throws ServiceException;

    Group findByName(String groupName) throws ServiceException;

}
