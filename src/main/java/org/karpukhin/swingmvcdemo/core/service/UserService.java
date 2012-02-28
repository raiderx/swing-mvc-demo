package org.karpukhin.swingmvcdemo.core.service;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.List;

/**
 * This interface describes the contract that service responsible for managing
 * {@link User} entity must follow.
 * @author Pavel Karpukhin
 */
public interface UserService {

    /**
     * Creates new {@link User} instance with given first name, last name
     * and group.
     * @param firstName user first name
     * @param lastName  user last name
     * @param group     user group
     * @return {@link User} instance just created
     */
    User createUser(String firstName, String lastName, Group group);

    /**
     * Sets new first name, last name and group to the existing user.
     * @param id        unique id of user
     * @param firstName new value for user first name
     * @param lastName  new value for user last name
     * @param group     new value for user group
     */
    void updateUser(int id, String firstName, String lastName, Group group);

    /**
     * Deletes specified user
     * @param user user to delete
     */
    void deleteUser(User user);

    /**
     * Deletes user with specified unique id
     * @param id unique id of user
     */
    void deleteUser(int id);

    /**
     * Returns list of users of group with given id
     * @param id unique id of group
     * @return list of users
     */
    List<User> getUsersByGroup(int id);

    /**
     * Returns user found by given unique id
     * @param id unique id of user
     * @return user
     */
    User getUserById(int id);
}
