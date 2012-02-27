package org.karpukhin.swingmvcdemo.core.service;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public interface UserService {

    /**
     *
     * @param firstName
     * @param lastName
     * @param group
     * @return
     */
    User createUser(String firstName, String lastName, Group group);

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

    void updateUser(int id, String firstName, String lastName, Group group);
}
