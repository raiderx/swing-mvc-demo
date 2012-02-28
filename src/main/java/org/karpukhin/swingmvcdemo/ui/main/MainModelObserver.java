package org.karpukhin.swingmvcdemo.ui.main;

/**
 * @author Pavel Karpukhin
 */
public interface MainModelObserver {

    void updateGroups();

    void updateUsers();

    void updateSelectedGroup();
}
