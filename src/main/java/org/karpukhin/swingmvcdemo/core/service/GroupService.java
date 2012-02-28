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
     * Returns new group created with given name and description
     * @param name        group name
     * @param description group description
     * @return group just created
     */
    Group createGroup(String name, String description);

    /**
     * Sets new name and description to the existing group.
     * @param id          unique id of group
     * @param name        new value for group name
     * @param description new value for group description
     */
    void updateGroup(int id, String name, String description);

    /**
     * Deletes specified group
     * @param group group to delete
     */
    void deleteGroup(Group group);

    /**
     * Deletes group with specified unique id
     * @param id unique id of group
     */
    void deleteGroup(int id);

    /**
     * Returns list of groups
     * @return list of groups
     */
    List<Group> getGroups();

    /**
     * Returns group found by given unique id
     * @param id unique id of group
     * @return group
     */
    Group getGroupById(int id);
}
