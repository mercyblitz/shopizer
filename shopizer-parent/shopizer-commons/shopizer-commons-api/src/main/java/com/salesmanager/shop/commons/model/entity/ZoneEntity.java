package com.salesmanager.shop.commons.model.entity;

public class ZoneEntity extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String countryCode;
	private String code;
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
