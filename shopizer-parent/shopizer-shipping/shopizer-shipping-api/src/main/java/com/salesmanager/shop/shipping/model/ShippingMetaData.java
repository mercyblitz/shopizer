package com.salesmanager.shop.shipping.model;


import com.salesmanager.shop.commons.entity.reference.country.Country;

import java.util.List;

/**
 * Describes how shipping is configured for a given store
 * @author carlsamson
 *
 */
public class ShippingMetaData {
	
	private List<String> modules;
	private List<String> preProcessors;
	private List<String> postProcessors;
	private List<Country> shipToCountry;
	private boolean useDistanceModule;
	private boolean useAddressAutoComplete;
	
	
	
	public List<String> getModules() {
		return modules;
	}
	public void setModules(List<String> modules) {
		this.modules = modules;
	}
	public List<String> getPreProcessors() {
		return preProcessors;
	}
	public void setPreProcessors(List<String> preProcessors) {
		this.preProcessors = preProcessors;
	}
	public List<String> getPostProcessors() {
		return postProcessors;
	}
	public void setPostProcessors(List<String> postProcessors) {
		this.postProcessors = postProcessors;
	}
	public List<Country> getShipToCountry() {
		return shipToCountry;
	}
	public void setShipToCountry(List<Country> shipToCountry) {
		this.shipToCountry = shipToCountry;
	}
	public boolean isUseDistanceModule() {
		return useDistanceModule;
	}
	public void setUseDistanceModule(boolean useDistanceModule) {
		this.useDistanceModule = useDistanceModule;
	}
  public boolean isUseAddressAutoComplete() {
    return useAddressAutoComplete;
  }
  public void setUseAddressAutoComplete(boolean useAddressAutoComplete) {
    this.useAddressAutoComplete = useAddressAutoComplete;
  }

}
