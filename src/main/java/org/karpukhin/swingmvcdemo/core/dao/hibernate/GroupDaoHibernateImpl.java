package org.karpukhin.swingmvcdemo.core.dao.hibernate;

import org.karpukhin.swingmvcdemo.core.dao.GroupDao;
import org.karpukhin.swingmvcdemo.core.model.Group;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class GroupDaoHibernateImpl extends AbstractDaoHibernateImpl<Group>
        implements GroupDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Group> getGroups() {
        return getSession().getNamedQuery("getAllGroups").list();
    }
}
