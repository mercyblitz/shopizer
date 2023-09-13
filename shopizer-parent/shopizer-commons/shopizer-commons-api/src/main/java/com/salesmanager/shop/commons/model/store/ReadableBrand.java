package com.salesmanager.shop.commons.model.store;


import com.salesmanager.shop.commons.model.content.ReadableImage;

public class ReadableBrand extends MerchantStoreBrand {
  
  private ReadableImage logo;

  public ReadableImage getLogo() {
    return logo;
  }

  public void setLogo(ReadableImage logo) {
    this.logo = logo;
  }

}
