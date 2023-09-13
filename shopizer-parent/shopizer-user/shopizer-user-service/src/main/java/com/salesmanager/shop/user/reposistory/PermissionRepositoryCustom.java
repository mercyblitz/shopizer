package com.salesmanager.shop.user.reposistory;

import com.salesmanager.shop.commons.entity.user.PermissionCriteria;
import com.salesmanager.shop.commons.entity.user.PermissionList;

public interface PermissionRepositoryCustom {

    PermissionList listByCriteria(PermissionCriteria criteria);

}
