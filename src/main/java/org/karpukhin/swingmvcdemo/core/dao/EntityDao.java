package org.karpukhin.swingmvcdemo.core.dao;

import org.karpukhin.swingmvcdemo.core.model.Entity;

/**
 * This interface describes contract that DAO of entities responsible for
 * managing their lifecycle themselves, must follow.
 * @author Pavel Karpukhin
 */
public interface EntityDao<T extends Entity> {
    
    /**
     * Saves or updates entity
     * @param entity entity to save
     */
    void saveOrUpdate(T entity);

    /**
     * Returns entity found by given unique id or {@code null} if entity
     * wasn't found
     * @param id entity's unique id
     * @return entity just found
     */
    T getById(int id);

    /**
     * Returns {@code true} if entity with given unique id exists
     * @param id unique id of entity
     * @return {@code true} if entity exists
     */
    boolean isExist(int id);
}
