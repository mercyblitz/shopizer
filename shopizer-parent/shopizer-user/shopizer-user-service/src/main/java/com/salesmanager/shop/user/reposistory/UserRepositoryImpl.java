package com.salesmanager.shop.user.reposistory;

import com.salesmanager.shop.commons.entity.user.User;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.model.entity.Criteria;
import com.salesmanager.shop.commons.model.entity.GenericEntityList;
import com.salesmanager.shop.commons.util.RepositoryHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {


    @PersistenceContext
    private EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public GenericEntityList<User> listByCriteria(Criteria criteria) throws ServiceException {

        /**
         * Name like
         * email like
         */


        try {
            StringBuilder req = new StringBuilder();
            req.append(
                    "select distinct u from User as u left join fetch u.groups ug left join fetch u.defaultLanguage ud join fetch u.merchantStore um");
            StringBuilder countBuilder = new StringBuilder();
            countBuilder.append("select count(distinct u) from User as u join u.merchantStore um");
            if (!StringUtils.isBlank(criteria.getStoreCode())) {
                req.append("  where um.code=:storeCode");
                countBuilder.append(" where um.code=:storeCode");
            }

            if (!StringUtils.isBlank(criteria.getCriteriaOrderByField())) {
                req.append(" order by u." + criteria.getCriteriaOrderByField() + " "
                        + criteria.getOrderBy().name().toLowerCase());
            }

            Query countQ = this.em.createQuery(countBuilder.toString());

            String hql = req.toString();
            Query q = this.em.createQuery(hql);

            if (!StringUtils.isBlank(criteria.getSearch())) {
                //TODO
            } else {
                if (criteria.getStoreCode() != null) {
                    countQ.setParameter("storeCode", criteria.getStoreCode());
                    q.setParameter("storeCode", criteria.getStoreCode());
                }
            }

            Number count = (Number) countQ.getSingleResult();

            @SuppressWarnings("rawtypes")
            GenericEntityList entityList = new GenericEntityList();
            entityList.setTotalCount(count.intValue());

            /**
             * Configure pagination using setMaxResults and setFirstResult method
             */

            q = RepositoryHelper.paginateQuery(q, count, entityList, criteria);
      
/*      if(criteria.isLegacyPagination()) {
          if (criteria.getMaxCount() > 0) {
            q.setFirstResult(criteria.getStartIndex());
            if (criteria.getMaxCount() < count.intValue()) {
              q.setMaxResults(criteria.getMaxCount());
            } else {
              q.setMaxResults(count.intValue());
            }
          }
      } else {

      }*/

            List<User> users = q.getResultList();
            entityList.setList(users);

            return entityList;


        } catch (javax.persistence.NoResultException ers) {
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return null;
    }

}
