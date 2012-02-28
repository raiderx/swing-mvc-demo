package org.karpukhin.swingmvcdemo.core.service;

import org.karpukhin.swingmvcdemo.core.model.Group;

import java.util.List;

/**
 * This interface describes the contract that service responsible for managing
 * {@link Group} entity must follow.
 * @author Pavel Karpukhin
 */
public interface GroupService {

    /**
     * Returns new group created with given name
     * @param name group name
     * @return group just created
     */
    Group createGroup(String name, String description);

    /**
     * Sets new name and description to the existing group.
     * @param groupId     unique id of group
     * @param name        new value for group name
     * @param description new value for group description
     */
    void updateGroup(int groupId, String name, String description);

    /**
     * Returns list of groups
     * @return list of groups
     */
    List<Group> getGroups();

    /**
     * Returns group found by given id
     * @param id group id
     * @return group
     */
    Group getGroupById(int id);
}
