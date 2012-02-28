package org.karpukhin.swingmvcdemo.core.dao;

import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public interface UserDao extends EntityDao<User> {

    /**
     * Returns list of users of group with given unique id
     * @param groupId unique id of group
     * @return list of users
     */
    List<User> getUsersByGroup(int groupId);
}
