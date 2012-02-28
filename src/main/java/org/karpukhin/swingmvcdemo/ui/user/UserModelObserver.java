package org.karpukhin.swingmvcdemo.ui.user;

/**
 * @author Pavel Karpukhin
 */
public interface UserModelObserver {

    void updateTitle();

    void updateGroups();

    void updateUser();
}
