package org.karpukhin.swingmvcdemo.core.dao;

import org.karpukhin.swingmvcdemo.core.model.Entity;

/**
 * @author Pavel Karpukhin
 */
public interface EntityDao<T extends Entity> {
    
    /**
     * Creates new entity
     * @param entity entity
     */
    void saveOrUpdate(T entity);

    /**
     * Returns entity found by given id
     * @param id entity id
     * @return entity
     */
    T getById(int id);

    /**
     * Returns {@code true} if entity with given id exists
     * @param id entity id
     * @return {@code true} if entity exists
     */
    boolean isExist(int id);
}
