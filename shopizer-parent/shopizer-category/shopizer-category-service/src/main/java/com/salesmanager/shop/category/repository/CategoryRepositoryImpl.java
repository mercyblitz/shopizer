package com.salesmanager.shop.category.repository;

import com.salesmanager.shop.category.entity.Category;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;


public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> countProductsByCategories(MerchantStore store, List<Long> categoryIds) {


        String hql = "select category.id, count(product.id) from Product product " +
                "inner join product.categories category " +
                "where category.id in (:cid) " +
                "and product.available=true and product.dateAvailable<=:dt " +
                "group by category.id";
        Query q = this.em.createQuery(hql);

        q.setParameter("cid", categoryIds);
        q.setParameter("dt", new Date());


        @SuppressWarnings("unchecked")
        List<Object[]> counts = q.getResultList();


        return counts;


    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Category> listByStoreAndParent(MerchantStore store, Category category) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select c from Category c join fetch c.merchantStore cm ");

        if (store == null) {
            if (category == null) {
                //query.from(qCategory)
                queryBuilder.append(" where c.parent IsNull ");
                //.where(qCategory.parent.isNull())
                //.orderBy(qCategory.sortOrder.asc(),qCategory.id.desc());
            } else {
                //query.from(qCategory)
                queryBuilder.append(" join fetch c.parent cp where cp.id =:cid ");
                //.where(qCategory.parent.eq(category))
                //.orderBy(qCategory.sortOrder.asc(),qCategory.id.desc());
            }
        } else {
            if (category == null) {
                //query.from(qCategory)
                queryBuilder.append(" where c.parent IsNull and cm.id=:mid ");
                //.where(qCategory.parent.isNull()
                //	.and(qCategory.merchantStore.eq(store)))
                //.orderBy(qCategory.sortOrder.asc(),qCategory.id.desc());
            } else {
                //query.from(qCategory)
                queryBuilder.append(" join fetch c.parent cp where cp.id =:cId and cm.id=:mid ");
                //.where(qCategory.parent.eq(category)
                //	.and(qCategory.merchantStore.eq(store)))
                //.orderBy(qCategory.sortOrder.asc(),qCategory.id.desc());
            }
        }

        queryBuilder.append(" order by c.sortOrder asc");

        String hql = queryBuilder.toString();
        Query q = this.em.createQuery(hql);

        q.setParameter("cid", category.getId());
        if (store != null) {
            q.setParameter("mid", store.getId());
        }


        return q.getResultList();
    }

    @Override
    public List<Category> listByProduct(MerchantStore store, Long product) {


        String hql = "select category from Product product " +
                "inner join product.categories category inner join product.merchantStore pm " +
                "where product.id=:id and pm.id=:mid " +
                "group by category.id";
        Query q = this.em.createQuery(hql);

        q.setParameter("id", product);
        q.setParameter("mid", store.getId());

        @SuppressWarnings("unchecked")
        List<Category> c = q.getResultList();


        return c;

    }

}
