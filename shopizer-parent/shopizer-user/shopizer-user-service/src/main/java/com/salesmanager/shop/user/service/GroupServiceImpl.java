package com.salesmanager.shop.user.service;

import com.salesmanager.shop.commons.entity.user.Group;
import com.salesmanager.shop.commons.entity.user.GroupType;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.shop.user.reposistory.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service("groupService")
public class GroupServiceImpl extends SalesManagerEntityServiceImpl<Integer, Group> implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        super(groupRepository);
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> listGroup(GroupType groupType) throws ServiceException {
        try {
            return groupRepository.findByType(groupType);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Group> listGroupByIds(Set<Integer> ids) throws ServiceException {

        try {
            return ids.isEmpty() ? new ArrayList<Group>() : groupRepository.findByIds(ids);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }


    @Override
    public Group findByName(String groupName) throws ServiceException {
        return groupRepository.findByGroupName(groupName);
    }


    @Override
    public List<Group> listGroupByNames(List<String> names) throws ServiceException {
        return groupRepository.findByNames(names);
    }


}
