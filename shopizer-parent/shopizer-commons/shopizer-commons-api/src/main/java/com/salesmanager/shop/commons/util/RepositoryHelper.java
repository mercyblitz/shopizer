package com.salesmanager.shop.commons.util;


import com.salesmanager.shop.commons.model.entity.Criteria;
import com.salesmanager.shop.commons.model.entity.GenericEntityList;

import javax.persistence.Query;

/**
 * Helper for Spring Data JPA
 * 
 * @author carlsamson
 *
 */
public class RepositoryHelper {

	@SuppressWarnings("rawtypes")
	public static Query paginateQuery(Query q, Number count, GenericEntityList entityList, Criteria criteria) {

		if (entityList == null) {
			entityList = new GenericEntityList();
		}

		if (criteria.isLegacyPagination()) {
			if (criteria.getMaxCount() > 0) {
				q.setFirstResult(criteria.getStartIndex());
				q.setMaxResults(Math.min(criteria.getMaxCount(), count.intValue()));
			}
		} else {
			//int firstResult = ((criteria.getStartPage()==0?criteria.getStartPage()+1:criteria.getStartPage()) - 1) * criteria.getPageSize();
			int firstResult = ((criteria.getStartPage()==0?0:criteria.getStartPage())) * criteria.getPageSize();
			q.setFirstResult(firstResult);
			q.setMaxResults(criteria.getPageSize());
			int lastPageNumber = (count.intValue() / criteria.getPageSize()) + 1;
			entityList.setTotalPages(lastPageNumber);
			entityList.setTotalCount(count.intValue());
		}

		return q;

	}

}
