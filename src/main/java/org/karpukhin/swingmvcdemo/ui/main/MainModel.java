package org.karpukhin.swingmvcdemo.ui.main;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public interface MainModel {

    /**
     * Sets list of groups
     * @param groups list of groups
     */
    void setGroups(List<Group> groups);
    
    /**
     * Sets list of users
     * @param users list of users
     */
    void setUsers(List<User> users);

    /**
     * Returns list of groups
     * @return list of groups
     */
    List<Group> getGroups();

    /**
     * Returns list of users
     * @return list of users
     */
    List<User> getUsers();

    void registerObserver(MainObserver observer);
}
