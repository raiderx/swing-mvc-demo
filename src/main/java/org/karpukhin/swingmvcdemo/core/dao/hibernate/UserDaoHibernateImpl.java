package org.karpukhin.swingmvcdemo.core.dao.hibernate;

import org.karpukhin.swingmvcdemo.core.dao.UserDao;
import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class UserDaoHibernateImpl extends AbstractDaoHibernateImpl<User>
        implements UserDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUsersByGroup(int groupId) {
        return getSession()
                .getNamedQuery("getUsersByGroup")
                .setInteger("groupId", groupId)
                .list();
    }
}
