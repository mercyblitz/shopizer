package com.salesmanager.shop.commons.util;

import com.salesmanager.shop.commons.constants.Constants;
import com.salesmanager.shop.commons.entity.catalog.product.Product;
import com.salesmanager.shop.commons.entity.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.shop.commons.entity.content.FileContentType;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Properties;


public abstract class AbstractimageFilePath implements ImageFilePath {


    protected static final String CONTEXT_PATH = "CONTEXT_PATH";
    public @Resource(name = "shopizer-properties") Properties properties = new Properties();//shopizer-properties

    public abstract String getBasePath(MerchantStore store);

    public abstract void setBasePath(String basePath);

    public abstract void setContentUrlPath(String contentUrl);

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Builds a static content image file path that can be used by image servlet
     * utility for getting the physical image
     *
     * @param store
     * @param imageName
     * @return
     */
    public String buildStaticImageUtils(MerchantStore store, String imageName) {
        StringBuilder imgName = new StringBuilder().append(getBasePath(store))
                .append(Constants.FILES_URI)
                .append(Constants.SLASH).append(store.getCode())
                .append(Constants.SLASH)
                .append(FileContentType.IMAGE.name())
                .append(Constants.SLASH);
        if (!StringUtils.isBlank(imageName)) {
            imgName.append(imageName);
        }
        return imgName.toString();

    }

    /**
     * Builds a static content image file path that can be used by image servlet
     * utility for getting the physical image by specifying the image type
     *
     * @param store
     * @param imageName
     * @return
     */
    public String buildStaticImageUtils(MerchantStore store, String type, String imageName) {
        StringBuilder imgName = new StringBuilder().append(getBasePath(store)).append(Constants.FILES_URI).append(Constants.SLASH).append(store.getCode()).append(Constants.SLASH).append(type).append(Constants.SLASH);
        if (!StringUtils.isBlank(imageName)) {
            imgName.append(imageName);
        }
        return imgName.toString();

    }

    /**
     * Builds a manufacturer image file path that can be used by image servlet
     * utility for getting the physical image
     *
     * @param store
     * @param manufacturer
     * @param imageName
     * @return
     */
    public String buildManufacturerImageUtils(MerchantStore store, Manufacturer manufacturer, String imageName) {
        return getBasePath(store) + Constants.SLASH + store.getCode() + Constants.SLASH +
                FileContentType.MANUFACTURER.name() + Constants.SLASH +
                manufacturer.getId() + Constants.SLASH +
                imageName;
    }

    /**
     * Builds a product image file path that can be used by image servlet
     * utility for getting the physical image
     *
     * @param store
     * @param product
     * @param imageName
     * @return
     */
    public String buildProductImageUtils(MerchantStore store, Product product, String imageName) {
        return getBasePath(store) + Constants.PRODUCTS_URI + Constants.SLASH + store.getCode() + Constants.SLASH +
                product.getSku() + Constants.SLASH + Constants.SMALL_IMAGE + Constants.SLASH + imageName;
    }

    /**
     * Builds a default product image file path that can be used by image servlet
     * utility for getting the physical image
     *
     * @param store
     * @param sku
     * @param imageName
     * @return
     */
    public String buildProductImageUtils(MerchantStore store, String sku, String imageName) {
        return getBasePath(store) + Constants.PRODUCTS_URI + Constants.SLASH + store.getCode() + Constants.SLASH +
                sku + Constants.SLASH + Constants.SMALL_IMAGE + Constants.SLASH + imageName;
    }

    /**
     * Builds a large product image file path that can be used by the image servlet
     *
     * @param store
     * @param sku
     * @param imageName
     * @return
     */
    public String buildLargeProductImageUtils(MerchantStore store, String sku, String imageName) {
        return getBasePath(store) + Constants.SLASH + store.getCode() + Constants.SLASH +
                sku + Constants.SLASH + Constants.SMALL_IMAGE + Constants.SLASH + imageName;
    }


    /**
     * Builds a merchant store logo path
     *
     * @param store
     * @return
     */
    public String buildStoreLogoFilePath(MerchantStore store) {
        return getBasePath(store) + Constants.FILES_URI + Constants.SLASH + store.getCode() + Constants.SLASH + FileContentType.LOGO + Constants.SLASH +
                store.getStoreLogo();
    }

    /**
     * Builds product property image url path
     *
     * @param store
     * @param imageName
     * @return
     */
    public String buildProductPropertyImageFilePath(MerchantStore store, String imageName) {
        return getBasePath(store) + Constants.SLASH + store.getCode() + Constants.SLASH + FileContentType.PROPERTY + Constants.SLASH +
                imageName;
    }

    public String buildProductPropertyImageUtils(MerchantStore store, String imageName) {
        return getBasePath(store) + Constants.FILES_URI + Constants.SLASH + store.getCode() + "/" + FileContentType.PROPERTY + "/" +
                imageName;
    }

    public String buildCustomTypeImageUtils(MerchantStore store, String imageName, FileContentType type) {
        return getBasePath(store) + Constants.FILES_URI + Constants.SLASH + store.getCode() + "/" + type + "/" +
                imageName;
    }

    /**
     * Builds static file url path
     *
     * @param store
     * @param fileName
     * @return
     */
    public String buildStaticContentFilePath(MerchantStore store, String fileName) {
        StringBuilder sb = new StringBuilder().append(getBasePath(store)).append(Constants.FILES_URI).append(Constants.SLASH).append(store.getCode()).append(Constants.SLASH);
        if (!StringUtils.isBlank(fileName)) {
            sb.append(fileName);
        }
        return sb.toString();
    }
}
