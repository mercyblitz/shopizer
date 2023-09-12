package com.salesmanager.shop.commons.util;

import com.salesmanager.shop.commons.constants.Constants;
import com.salesmanager.shop.commons.entity.catalog.product.file.DigitalProduct;
import com.salesmanager.shop.commons.entity.content.FileContentType;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Properties;

import static com.salesmanager.shop.commons.constants.Constants.ADMIN_URI;
import static com.salesmanager.shop.commons.constants.Constants.BLANK;
import static com.salesmanager.shop.commons.constants.Constants.CATEGORY_URI;
import static com.salesmanager.shop.commons.constants.Constants.DEFAULT_DOMAIN_NAME;
import static com.salesmanager.shop.commons.constants.Constants.FILES_URI;
import static com.salesmanager.shop.commons.constants.Constants.HTTP_SCHEME;
import static com.salesmanager.shop.commons.constants.Constants.SHOP_URI;
import static com.salesmanager.shop.commons.constants.Constants.SLASH;
import static com.salesmanager.shop.commons.constants.Constants.STATIC_URI;
import static com.salesmanager.shop.commons.constants.Constants.URL_EXTENSION;

@Component
public class FilePathUtils {

    public static final String X_FORWARDED_HOST = "X-Forwarded-Host";
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    private static final String DOWNLOADS = "/downloads/";
    private static final String DOUBLE_SLASH = "://";
    private static final String CONTEXT_PATH = "CONTEXT_PATH";
    @Resource(name = "shopizer-properties")
    public Properties properties = new Properties();
    @Inject
    private CoreConfiguration coreConfiguration;
    @Inject
    @Qualifier("img")
    private ImageFilePath imageUtils;

    /**
     * Builds a static content content file path that can be used by image
     * servlet utility for getting the physical image Example:
     * /files/<storeCode>/
     */
    public String buildStaticFilePath(String storeCode, String fileName) {
        String path = FILES_URI + SLASH + storeCode + SLASH;
        if (StringUtils.isNotBlank(fileName)) {
            return path + fileName;
        }
        return path;
    }

    public String buildStaticFilePath(MerchantStore store) {
        return STATIC_URI + FILES_URI + SLASH + store.getCode() + SLASH;
    }

    /**
     * Example: /admin/files/downloads/<storeCode>/<product>
     */
    public String buildAdminDownloadProductFilePath(MerchantStore store, DigitalProduct digitalProduct) {
        return ADMIN_URI + FILES_URI + DOWNLOADS + store.getCode() + SLASH + digitalProduct.getProductFileName();
    }


    /**
     * Example: /<baseImagePath>/files/<storeCode>/STATIC_FILE/<fileName> Or
     * example: /<shopScheme>://<domainName>/<contextPath>/files/<storeCode>/
     */
    public String buildStaticFileAbsolutePath(MerchantStore store, String fileName) {
        if (StringUtils.isNotBlank(imageUtils.getBasePath(store))
                && imageUtils.getBasePath(store).startsWith(HTTP_SCHEME)) {
            return imageUtils.getBasePath(store) + FILES_URI + SLASH + store.getCode() + SLASH
                    + FileContentType.STATIC_FILE + SLASH + fileName;
        } else {
            String scheme = this.getScheme(store);
            return scheme + SLASH + coreConfiguration.getProperty("CONTEXT_PATH")
                    + buildStaticFilePath(store.getCode(), fileName);
        }
    }

    /**
     * Example: http[s]://<scheme>/<domainName>/<contextPath>
     */
    public String buildStoreUri(MerchantStore store, HttpServletRequest request) {
        return buildBaseUrl(request, store);
    }

    /**
     * \/<contextPath>
     */
    public String buildStoreUri(MerchantStore store, String contextPath) {
        return normalizePath(contextPath);
    }

    public String buildRelativeStoreUri(HttpServletRequest request, MerchantStore store) {
        return normalizePath(request.getContextPath());
    }

    /**
     * Access to the customer section
     */
    public String buildCustomerUri(MerchantStore store, String contextPath) {
        return buildStoreUri(store, contextPath);
    }

    public String buildAdminUri(MerchantStore store, HttpServletRequest request) {
        String baseUrl = buildBaseUrl(request, store);
        return baseUrl + ADMIN_URI;
    }

    public String buildCategoryUrl(MerchantStore store, String contextPath, String url) {
        return buildStoreUri(store, contextPath) + SHOP_URI + CATEGORY_URI + SLASH + url + URL_EXTENSION;
    }

    public String buildProductUrl(MerchantStore store, String contextPath, String url) {
        return buildStoreUri(store, contextPath) + SHOP_URI + Constants.PRODUCT_URI + SLASH + url + URL_EXTENSION;
    }

    public String getContextPath() {
        return properties.getProperty(CONTEXT_PATH);
    }

    private String normalizePath(String path) {
        if (SLASH.equals(path)) {
            return BLANK;
        } else {
            return path;
        }
    }

    private String getDomainName(String domainName) {
        if (StringUtils.isBlank(domainName)) {
            return DEFAULT_DOMAIN_NAME;
        } else {
            return domainName;
        }
    }

    private String getScheme(MerchantStore store) {
        String baseScheme = store.getDomainName();
        if (baseScheme != null && baseScheme.length() > 0
                && baseScheme.charAt(baseScheme.length() - 1) == Constants.SLASH.charAt(0)) {
            baseScheme = baseScheme.substring(0, baseScheme.length() - 1);
        }
        // end no more
        return validUrl(baseScheme);
    }

    public String validUrl(final String url) {
        if (!StringUtils.isBlank(url) && !url.startsWith(HTTP) && !url.startsWith(HTTP)) {
            return HTTPS + url;
        }
        return url;
    }

    private String buildBaseUrl(HttpServletRequest request, MerchantStore store) {
        String contextPath = normalizePath(request.getContextPath());
        String scheme = getScheme(store);
        return scheme + DOUBLE_SLASH + contextPath;
    }

    public String buildBaseUrl(String contextPath, MerchantStore store) {
        String normalizePath = normalizePath(contextPath);
        String scheme = getScheme(store);
        return scheme + SLASH + normalizePath;
    }

    /**
     * Requires web server headers to build image URL for social media
     * sharing.<br/>
     * <p>
     * Nginx configuration example:
     *
     * <pre>
     *     proxy_set_header X-Forwarded-Proto $scheme;
     *     proxy_set_header X-Forwarded-Host $scheme://$host;
     *     proxy_set_header X-Forwarded-Server $host;
     * </pre>
     *
     * @param merchantStore
     * @param request
     * @return
     */
    public String buildStoreForwardedUri(MerchantStore merchantStore, HttpServletRequest request) {
        String uri;
        if (StringUtils.isNotEmpty(request.getHeader(X_FORWARDED_HOST))) {
            uri = request.getHeader(X_FORWARDED_HOST);
        } else {
            uri = buildStoreUri(merchantStore, request);
        }
        return uri;
    }

    public boolean isValidURL(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
