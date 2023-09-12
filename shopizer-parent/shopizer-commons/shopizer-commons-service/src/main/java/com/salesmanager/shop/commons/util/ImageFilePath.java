package com.salesmanager.shop.commons.util;


import com.salesmanager.shop.commons.entity.catalog.product.Product;
import com.salesmanager.shop.commons.entity.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.shop.commons.entity.content.FileContentType;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;

public interface ImageFilePath {

    /**
     * Context path configured in shopizer-properties.xml
     *
     * @return
     */
    String getContextPath();


    String getBasePath(MerchantStore store);

    /**
     * Builds a static content image file path that can be used by image servlet
     * utility for getting the physical image
     *
     * @param store
     * @param imageName
     * @return
     */
    String buildStaticImageUtils(MerchantStore store, String imageName);

    /**
     * Builds a static content image file path that can be used by image servlet
     * utility for getting the physical image by specifying the image type
     *
     * @param store
     * @param imageName
     * @return
     */
    String buildStaticImageUtils(MerchantStore store, String type, String imageName);

    /**
     * Builds a manufacturer image file path that can be used by image servlet
     * utility for getting the physical image
     *
     * @param store
     * @param manufacturer
     * @param imageName
     * @return
     */
    String buildManufacturerImageUtils(MerchantStore store, Manufacturer manufacturer, String imageName);

    /**
     * Builds a product image file path that can be used by image servlet
     * utility for getting the physical image
     *
     * @param store
     * @param product
     * @param imageName
     * @return
     */
    String buildProductImageUtils(MerchantStore store, Product product, String imageName);

    /**
     * Builds a default product image file path that can be used by image servlet
     * utility for getting the physical image
     *
     * @param store
     * @param sku
     * @param imageName
     * @return
     */
    String buildProductImageUtils(MerchantStore store, String sku, String imageName);

    /**
     * Builds a large product image file path that can be used by the image servlet
     *
     * @param store
     * @param sku
     * @param imageName
     * @return
     */
    String buildLargeProductImageUtils(MerchantStore store, String sku, String imageName);


    /**
     * Builds a merchant store logo path
     *
     * @param store
     * @return
     */
    String buildStoreLogoFilePath(MerchantStore store);

    /**
     * Builds product property image url path
     *
     * @param store
     * @param imageName
     * @return
     */
    String buildProductPropertyImageUtils(MerchantStore store, String imageName);

    /**
     * A custom file type image handler
     *
     * @param store
     * @param imageName
     * @param type
     * @return
     */
    String buildCustomTypeImageUtils(MerchantStore store, String imageName, FileContentType type);


    /**
     * Builds static file path
     *
     * @param store
     * @param fileName
     * @return
     */
    String buildStaticContentFilePath(MerchantStore store, String fileName);


}
