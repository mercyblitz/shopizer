package com.salesmanager.shop.category.populator;


import com.salesmanager.shop.category.entity.Category;
import com.salesmanager.shop.category.entity.CategoryDescription;
import com.salesmanager.shop.category.model.ReadableCategory;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ConversionException;
import com.salesmanager.shop.commons.util.AbstractDataPopulator;
import org.springframework.util.Assert;

public class ReadableCategoryPopulator extends AbstractDataPopulator<Category, ReadableCategory> {

    @Override
    public ReadableCategory populate(final Category source,
                                     final ReadableCategory target,
                                     final MerchantStore store,
                                     final Language language) throws ConversionException {

        Assert.notNull(source, "Category must not be null");

        target.setLineage(source.getLineage());
        if (source.getDescriptions() != null && source.getDescriptions().size() > 0) {

            CategoryDescription description = source.getDescription();
            if (source.getDescriptions().size() > 1) {
                for (final CategoryDescription desc : source.getDescriptions()) {
                    if (desc.getLanguage().getCode().equals(language.getCode())) {
                        description = desc;
                        break;
                    }
                }
            }

            if (description != null) {
                final com.salesmanager.shop.category.model.CategoryDescription  desc = new com.salesmanager.shop.category.model.CategoryDescription ();
                desc.setFriendlyUrl(description.getSeUrl());
                desc.setName(description.getName());
                desc.setId(source.getId());
                desc.setDescription(description.getDescription());
                desc.setKeyWords(description.getMetatagKeywords());
                desc.setHighlights(description.getCategoryHighlight());
                desc.setTitle(description.getMetatagTitle());
                desc.setMetaDescription(description.getMetatagDescription());

                target.setDescription(desc);
            }

        }

        if (source.getParent() != null) {
            final com.salesmanager.shop.category.model.Category  parent = new com.salesmanager.shop.category.model.Category ();
            parent.setCode(source.getParent().getCode());
            parent.setId(source.getParent().getId());
            target.setParent(parent);
        }

        target.setCode(source.getCode());
        target.setId(source.getId());
        if (source.getDepth() != null) {
            target.setDepth(source.getDepth());
        }
        target.setSortOrder(source.getSortOrder());
        target.setVisible(source.isVisible());
        target.setFeatured(source.isFeatured());

        return target;

    }

    @Override
    protected ReadableCategory createTarget() {
        return null;
    }

}
