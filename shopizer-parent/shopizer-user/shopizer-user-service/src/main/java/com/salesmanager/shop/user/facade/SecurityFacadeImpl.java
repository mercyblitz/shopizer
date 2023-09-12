package com.salesmanager.shop.user.facade;

import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.exception.ServiceRuntimeException;
import com.salesmanager.shop.user.entity.Group;
import com.salesmanager.shop.user.entity.PermissionCriteria;
import com.salesmanager.shop.user.entity.PermissionList;
import com.salesmanager.shop.user.model.ReadablePermission;
import com.salesmanager.shop.user.service.GroupService;
import com.salesmanager.shop.user.service.PermissionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("securityFacade")
public class SecurityFacadeImpl implements SecurityFacade {

    private static final String USER_PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{6,12})";

    private Pattern userPasswordPattern = Pattern.compile(USER_PASSWORD_PATTERN);

    @Inject
    private PermissionService permissionService;

    @Inject
    private GroupService groupService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<ReadablePermission> getPermissions(List<String> groups) {

        List<Group> userGroups = null;
        try {
            userGroups = groupService.listGroupByNames(groups);

            List<Integer> ids = new ArrayList<Integer>();
            for (Group g : userGroups) {
                ids.add(g.getId());
            }

            PermissionCriteria criteria = new PermissionCriteria();
            criteria.setGroupIds(new HashSet(ids));

            PermissionList permissions = permissionService.listByCriteria(criteria);
            throw new ServiceRuntimeException("Not implemented");
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean validateUserPassword(String password) {

        Matcher matcher = userPasswordPattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Match non encoded to encoded
     * Don't use this as a simple raw password check
     */
    @Override
    public boolean matchPassword(String modelPassword, String newPassword) {
        return passwordEncoder.matches(newPassword, modelPassword);
    }

    @Override
    public boolean matchRawPasswords(String password, String repeatPassword) {
        Assert.notNull(password, "password is null");
        Assert.notNull(repeatPassword, "repeat password is null");
        return password.equals(repeatPassword);
    }


}
