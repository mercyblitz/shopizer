package com.salesmanager.shop.category.populator;

import com.salesmanager.shop.category.entity.Category;
import com.salesmanager.shop.category.entity.CategoryDescription;
import com.salesmanager.shop.category.model.PersistableCategory;
import com.salesmanager.shop.category.service.CategoryService;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ConversionException;
import com.salesmanager.shop.commons.service.language.LanguageService;
import com.salesmanager.shop.commons.util.AbstractDataPopulator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Component
public class PersistableCategoryPopulator extends AbstractDataPopulator<PersistableCategory, Category> {

    @Inject
    private CategoryService categoryService;
    @Inject
    private LanguageService languageService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public LanguageService getLanguageService() {
        return languageService;
    }

    public void setLanguageService(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public Category populate(PersistableCategory source, Category target, MerchantStore store, Language language)
            throws ConversionException {

        try {

            Validate.notNull(target, "Category target cannot be null");


/*		Validate.notNull(categoryService, "Requires to set CategoryService");
        Validate.notNull(languageService, "Requires to set LanguageService");*/

            target.setMerchantStore(store);
            target.setCode(source.getCode());
            target.setSortOrder(source.getSortOrder());
            target.setVisible(source.isVisible());
            target.setFeatured(source.isFeatured());

            //children
            if (!CollectionUtils.isEmpty(source.getChildren())) {
                //no modifications to children category
            } else {
                target.getCategories().clear();
            }

            //get parent

            if (source.getParent() == null || (StringUtils.isBlank(source.getParent().getCode())) || source.getParent().getId() == null) {
                target.setParent(null);
                target.setDepth(0);
                target.setLineage("/" + source.getId() + "/");
            } else {
                Category parent = null;
                if (!StringUtils.isBlank(source.getParent().getCode())) {
                    parent = categoryService.getByCode(store.getCode(), source.getParent().getCode());
                } else if (source.getParent().getId() != null) {
                    parent = categoryService.getById(source.getParent().getId(), store.getId());
                } else {
                    throw new ConversionException("Category parent needs at least an id or a code for reference");
                }
                if (parent != null && parent.getMerchantStore().getId().intValue() != store.getId().intValue()) {
                    throw new ConversionException("Store id does not belong to specified parent id");
                }

                if (parent != null) {
                    target.setParent(parent);

                    String lineage = parent.getLineage();
                    int depth = parent.getDepth();

                    target.setDepth(depth + 1);
                    target.setLineage(lineage + target.getId() + "/");
                }

            }


            if (!CollectionUtils.isEmpty(source.getChildren())) {

                for (PersistableCategory cat : source.getChildren()) {

                    Category persistCategory = this.populate(cat, new Category(), store, language);
                    target.getCategories().add(persistCategory);

                }

            }


            if (!CollectionUtils.isEmpty(source.getDescriptions())) {
                Set<CategoryDescription> descriptions = new HashSet<>();
                if (CollectionUtils.isNotEmpty(target.getDescriptions())) {
                    for (CategoryDescription description : target.getDescriptions()) {
                        for (com.salesmanager.shop.category.model.CategoryDescription d : source.getDescriptions()) {
                            if (StringUtils.isBlank(d.getLanguage())) {
                                throw new ConversionException("Source category description has no language");
                            }
                            if (d.getLanguage().equals(description.getLanguage().getCode())) {
                                description.setCategory(target);
                                description = buildDescription(d, description);
                                descriptions.add(description);
                            }
                        }
                    }

                } else {
                    for (com.salesmanager.shop.category.model.CategoryDescription d : source.getDescriptions()) {
                        CategoryDescription t = new CategoryDescription();
                        this.buildDescription(d, t);
                        t.setCategory(target);
                        descriptions.add(t);
                    }
                }
                target.setDescriptions(descriptions);
            }


            return target;


        } catch (Exception e) {
            throw new ConversionException(e);
        }

    }

    private CategoryDescription buildDescription(com.salesmanager.shop.category.model.CategoryDescription source, CategoryDescription target) throws Exception {
        //com.salesmanager.shop.category.model.CategoryDescription desc = new com.salesmanager.shop.category.model.CategoryDescription();
        target.setCategoryHighlight(source.getHighlights());
        target.setDescription(source.getDescription());
        target.setName(source.getName());
        target.setMetatagDescription(source.getMetaDescription());
        target.setMetatagTitle(source.getTitle());
        target.setSeUrl(source.getFriendlyUrl());
        Language lang = languageService.getByCode(source.getLanguage());
        if (lang == null) {
            throw new ConversionException("Language is null for code " + source.getLanguage() + " use language ISO code [en, fr ...]");
        }
        //description.setId(description.getId());
        target.setLanguage(lang);
        return target;
    }


    @Override
    protected Category createTarget() {
        // TODO Auto-generated method stub
        return null;
    }

}
