package org.karpukhin.swingmvcdemo.core.dao;

import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public interface UserDao extends EntityDao<User> {

    /**
     * Returns list of users
     * @return list of users
     */
    /*List<User> getUsers();*/

    /**
     * Returns list of users of group with given id
     * @param groupId group id
     * @return list of users
     */
    List<User> getUsersByGroup(int groupId);
}
