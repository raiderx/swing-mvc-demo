package org.karpukhin.swingmvcdemo.core.dao;

import org.karpukhin.swingmvcdemo.core.model.Group;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public interface GroupDao extends EntityDao<Group> {

    /**
     * Returns list of groups
     * @return list of groups
     */
    List<Group> getGroups();
}
