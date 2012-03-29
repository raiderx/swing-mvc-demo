package org.karpukhin.swingmvcdemo.core.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.karpukhin.swingmvcdemo.core.dao.EntityDao;
import org.karpukhin.swingmvcdemo.core.model.Entity;

import java.lang.reflect.ParameterizedType;

/**
 * Basic class for access to the {@link Entity} objects.
 * It is used to load objects from database, save, update or delete them.
 * The implementation is based on the Hibernate.
 *
 * These methods were taken from http://www.javatalks.ru project.
 *
 * @author Pavel Vervenko
 * @author Kirill Afonin
 */
public class AbstractEntityDaoHibernateImpl<T extends Entity> implements EntityDao<T> {

    /**
     * Hibernate SessionFactory
     */
    private SessionFactory sessionFactory;

    /**
     * Type of entity
     */
    private final Class<T> type = getType();

    /**
     * Returns parametrized type of entity using reflection
     * @return type of entity
     */
    protected Class<T> getType() {
        return (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * Returns current Hibernate session
     * @return current Hibernate session
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Sets Hibernate session factory
     * @param sessionFactory Hibernate session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getById(int id) {
        return (T)getSession().get(type, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExist(int id) {
        return getById(id) != null;
    }
}
