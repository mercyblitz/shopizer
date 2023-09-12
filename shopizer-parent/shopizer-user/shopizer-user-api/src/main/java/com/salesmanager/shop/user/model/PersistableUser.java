package com.salesmanager.shop.user.model;

import java.util.ArrayList;
import java.util.List;

public class PersistableUser extends UserEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String password;
    private String repeatPassword;
    private String store;
    private String userName;
    private boolean active;
    private List<PersistableGroup> groups = new ArrayList<PersistableGroup>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PersistableGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<PersistableGroup> groups) {
        this.groups = groups;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

}
