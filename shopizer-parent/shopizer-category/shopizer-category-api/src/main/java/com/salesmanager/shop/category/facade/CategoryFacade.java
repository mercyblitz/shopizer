package com.salesmanager.shop.category.facade;


import com.salesmanager.shop.category.entity.Category;
import com.salesmanager.shop.category.model.PersistableCategory;
import com.salesmanager.shop.category.model.ReadableCategory;
import com.salesmanager.shop.category.model.ReadableCategoryList;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.model.entity.ListCriteria;

import java.util.List;

public interface CategoryFacade {


    /**
     * Returns a list of ReadableCategory ordered and built according to a given depth
     * @param store
     * @param depth
     * @param language
     * @param filter
     * @param page
     * @param count
     * @return ReadableCategoryList
     */
	ReadableCategoryList getCategoryHierarchy(MerchantStore store, ListCriteria criteria, int depth, Language language, List<String> filter, int page, int count);

	/**
	 *
	 * @param store
	 * @param category
	 * @return PersistableCategory
	 */
	PersistableCategory saveCategory(MerchantStore store, PersistableCategory category);

	/**
	 *
	 * @param store
	 * @param id
	 * @param language
	 * @return ReadableCategory
	 */
	ReadableCategory getById(MerchantStore store, Long id, Language language);

	/**
	 *
	 * @param store
	 * @param code
	 * @param language
	 * @return ReadableCategory
	 * @throws Exception
	 */
	ReadableCategory getByCode(MerchantStore store, String code, Language language) throws Exception;

	/**
	 * Get a Category by the Search Engine friendly URL slug
	 *
	 * @param merchantStore
	 * @param friendlyUrl
	 * @param language
	 * @return
	 */
	ReadableCategory getCategoryByFriendlyUrl(MerchantStore merchantStore, String friendlyUrl, Language language) throws Exception;

	Category getByCode(String code, MerchantStore store);

	void deleteCategory(Long categoryId, MerchantStore store);

	void deleteCategory(Category category);

	/**
	 * Check if category code already exist
	 * @param store
	 * @param code
	 * @return
	 * @throws Exception
	 */
	boolean existByCode(MerchantStore store, String code);

	/**
	 * Move a Category from a node to another node
	 * @param child
	 * @param parent
	 * @param store
	 */
	void move(Long child, Long parent, MerchantStore store);

	/**
	 * Set category visible or not
	 * @param category
	 * @param store
	 */
	void setVisible(PersistableCategory category, MerchantStore store);
	
	
	/**
	 * List category by product
	 * @param store
	 * @param product
	 * @return
	 */
	ReadableCategoryList listByProduct(MerchantStore store, Long product, Language language);
}
