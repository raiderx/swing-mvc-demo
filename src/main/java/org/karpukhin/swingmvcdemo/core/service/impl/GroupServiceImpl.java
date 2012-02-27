package org.karpukhin.swingmvcdemo.core.service.impl;

import org.karpukhin.swingmvcdemo.core.dao.GroupDao;
import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.service.GroupService;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    /**
     * Creates new instance with given {@link GroupDao} implementation
     * @param groupDao {@link GroupDao} implementation
     */
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group createGroup(String name, String description) {
        Group group = new Group(name, description);
        groupDao.saveOrUpdate(group);
        return group;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGroup(int groupId, String name, String description) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Group> getGroups() {
        return groupDao.getGroups();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group getGroupById(int id) {
        return groupDao.getById(id);
    }
}
