package org.karpukhin.swingmvcdemo.ui.main;

/**
 * @author Pavel Karpukhin
 */
public interface MainController {

    void selectGroup(int groupId);

    void addGroup();

    void editGroup(int groupId);

    void removeGroup(int groupId);

    void addUserToGroup(int groupId);
}
