package com.salesmanager.shop.commons.service.generic;

import com.salesmanager.shop.commons.entity.generic.SalesManagerEntity;
import com.salesmanager.shop.commons.exception.ServiceException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @param <K> the key type
 * @param <E> the entity type
 */
public abstract class SalesManagerEntityServiceImpl<K extends Serializable & Comparable<K>, E extends SalesManagerEntity<K, ?>>
        implements SalesManagerEntityService<K, E> {

    /**
     * Classe de l'entité, déterminé à partir des paramètres generics.
     */
    private final Class<E> objectClass;


    private final JpaRepository<E, K> repository;

    @SuppressWarnings("unchecked")
    public SalesManagerEntityServiceImpl(JpaRepository<E, K> repository) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.objectClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        this.repository = repository;
    }

    protected final Class<E> getObjectClass() {
        return objectClass;
    }


    public E getById(K id) {
        return repository.getOne(id);
    }


    public void save(E entity) throws ServiceException {
        repository.saveAndFlush(entity);
    }

    public void saveAll(Iterable<E> entities) throws ServiceException {
        repository.saveAll(entities);
    }


    public void create(E entity) throws ServiceException {
        save(entity);
    }


    public void update(E entity) throws ServiceException {
        save(entity);
    }


    public void delete(E entity) throws ServiceException {
        repository.delete(entity);
    }


    public void flush() {
        repository.flush();
    }


    public List<E> list() {
        return repository.findAll();
    }


    public Long count() {
        return repository.count();
    }

    protected E saveAndFlush(E entity) {
        return repository.saveAndFlush(entity);
    }

}