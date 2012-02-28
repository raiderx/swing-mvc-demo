package org.karpukhin.swingmvcdemo.ui.user;

import org.karpukhin.swingmvcdemo.core.model.Group;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public interface UserModel {

    String getTitle();

    void setTitle(String title);

    List<Group> getGroups();
    
    void setGroups(List<Group> groups);
    
    int getSelectedGroup();

    void setSelectedGroup(int groupId);

    void registerObserver(UserModelObserver observer);
}
