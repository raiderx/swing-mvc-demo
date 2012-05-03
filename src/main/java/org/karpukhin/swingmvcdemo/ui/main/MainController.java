package org.karpukhin.swingmvcdemo.ui.main;

import org.karpukhin.swingmvcdemo.core.model.User;

/**
 * @author Pavel Karpukhin
 */
public interface MainController {

    void selectGroup(int groupId);

    void addGroup();

    void editGroup(int groupId);

    void removeGroup(int groupId);

    void addUserToGroup(int groupId);

    void editUser(int userId);

    void removeUser(int userId);

    /**
     * Searches user with given first name or last name
     * @param text first name or last name
     */
    void search(String text);
}
