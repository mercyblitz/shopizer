package com.salesmanager.shop.catalog.service;

import com.salesmanager.shop.catalog.entity.Catalog;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityService;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CatalogService extends SalesManagerEntityService<Long, Catalog> {


    /**
     * Creates a new Catalog
     *
     * @param store
     * @return Catalog
     * @throws ServiceException
     */
    Catalog saveOrUpdate(Catalog catalog, MerchantStore store);

    Optional<Catalog> getById(Long catalogId, MerchantStore store);

    Optional<Catalog> getByCode(String code, MerchantStore store);

    /**
     * Get a list of Catalog associated with a MarketPlace
     *
     * @param store
     * @param language
     * @param name
     * @param page
     * @param count
     * @return
     */
    Page<Catalog> getCatalogs(MerchantStore store, Language language, String name, int page, int count);

    /**
     * Delete a Catalog and related objects
     */
    void delete(Catalog catalog) throws ServiceException;

    boolean existByCode(String code, MerchantStore store);

}
