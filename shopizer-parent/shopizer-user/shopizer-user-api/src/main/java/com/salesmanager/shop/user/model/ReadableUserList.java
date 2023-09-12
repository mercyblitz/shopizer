package com.salesmanager.shop.user.model;


import com.salesmanager.shop.commons.model.entity.ReadableList;

import java.util.ArrayList;
import java.util.List;

public class ReadableUserList extends ReadableList {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<ReadableUser> data = new ArrayList<ReadableUser>();

    public List<ReadableUser> getData() {
        return data;
    }

    public void setData(List<ReadableUser> data) {
        this.data = data;
    }

}
