package org.karpukhin.swingmvcdemo.core.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.karpukhin.swingmvcdemo.core.dao.EntityDao;
import org.karpukhin.swingmvcdemo.core.model.Entity;

import java.lang.reflect.ParameterizedType;

/**
 * @author Pavel Karpukhin
 */
public class AbstractDaoHibernateImpl<T extends Entity> implements EntityDao<T> {

    /**
     * Hibernate SessionFactory
     */
    private SessionFactory sessionFactory;

    /**
     * Type of entity
     */
    private final Class<T> type = getType();

    protected Class<T> getType() {
        return (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        session.saveOrUpdate(entity);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
