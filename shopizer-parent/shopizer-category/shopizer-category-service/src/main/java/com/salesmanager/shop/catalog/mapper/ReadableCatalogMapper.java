package com.salesmanager.shop.catalog.mapper;

import com.salesmanager.shop.catalog.entity.Catalog;
import com.salesmanager.shop.catalog.model.ReadableCatalog;
import com.salesmanager.shop.category.entity.Category;
import com.salesmanager.shop.category.model.ReadableCategory;
import com.salesmanager.shop.commons.entity.mapper.Mapper;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.entity.common.audit.AuditSection;
import com.salesmanager.shop.commons.util.DateUtil;
import com.salesmanager.shop.store.facade.StoreFacade;
import com.salesmanager.shop.commons.model.store.ReadableMerchantStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class ReadableCatalogMapper implements Mapper<Catalog, ReadableCatalog> {

    @Autowired
    private StoreFacade storeFacade;


    @Autowired
    private ReadableCategoryMapper readableCategoryMapper;

    @Override
    public ReadableCatalog convert(Catalog source, MerchantStore store, Language language) {
        ReadableCatalog destination = new ReadableCatalog();
        return merge(source, destination, store, language);
    }

    @Override
    public ReadableCatalog merge(Catalog source, ReadableCatalog destination, MerchantStore store,
                                 Language language) {
        if (destination == null) {
            destination = new ReadableCatalog();
        }

        if (isPositive(source.getId())) {
            destination.setId(source.getId());
        }

        destination.setCode(source.getCode());
        destination.setDefaultCatalog(source.isDefaultCatalog());
        destination.setVisible(source.isVisible());

        Optional<ReadableMerchantStore> readableStore = Optional.ofNullable(source.getMerchantStore())
                .map(MerchantStore::getCode)
                .map(code -> storeFacade.getByCode(code, language));
        readableStore.ifPresent(destination::setStore);

        destination.setDefaultCatalog(source.isDefaultCatalog());

        Optional<String> formattedCreationDate = Optional.ofNullable(source.getAuditSection())
                .map(AuditSection::getDateCreated)
                .map(DateUtil::formatDate);
        formattedCreationDate.ifPresent(destination::setCreationDate);

        if (!CollectionUtils.isEmpty(source.getEntry())) {

            //hierarchy temp object
            Map<Long, ReadableCategory> hierarchy = new HashMap<Long, ReadableCategory>();

            source.getEntry().forEach(entry -> {
                processCategory(entry.getCategory(), store, language, hierarchy, new HashMap<>());
            });

            destination.setCategory(new ArrayList<>(hierarchy.values()));
        }

        return destination;

    }

    private boolean isPositive(Long id) {
        return Objects.nonNull(id) && id > 0;
    }

    /**
     * @param c
     * @param store
     * @param language
     * @param hierarchy
     * @param processed
     */
    private void processCategory(Category c, MerchantStore store, Language language, Map<Long, ReadableCategory> hierarchy, Map<Long, ReadableCategory> processed) {

        //build category hierarchy

        ReadableCategory rc = null;
        ReadableCategory rp = null;

        if (!CollectionUtils.isEmpty(c.getCategories())) {
            c.getCategories().stream().forEach(element -> {
                processCategory(element, store, language, hierarchy, processed);
            });
        }

        Category parent = c.getParent();
        if (Objects.nonNull(parent)) {
            rp = hierarchy.computeIfAbsent(parent.getId(), i -> toReadableCategory(c.getParent(), store, language, processed));
        }

        rc = toReadableCategory(c, store, language, processed);
        if (Objects.nonNull(rp)) {
            rp.getChildren().add(rc);
        } else {
            hierarchy.put(c.getId(), rc);
        }

    }

    private ReadableCategory toReadableCategory(Category c, MerchantStore store, Language lang, Map<Long, ReadableCategory> processed) {
        Long id = c.getId();
        return processed.computeIfAbsent(id, it -> readableCategoryMapper.convert(c, store, lang));
    }

}
