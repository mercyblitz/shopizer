package com.salesmanager.shop.user.model;

/**
 * Object used for reading a group
 *
 * @author carlsamson
 */
public class ReadableGroup extends GroupEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id = 0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
