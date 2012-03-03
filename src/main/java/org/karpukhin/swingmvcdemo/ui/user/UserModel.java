package org.karpukhin.swingmvcdemo.ui.user;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public interface UserModel {

    String getTitle();

    void setTitle(String title);

    List<Group> getGroups();
    
    void setGroups(List<Group> groups);

    void setUser(User user);

    User getUser();

    boolean getVisible();

    void setVisible(boolean visible);

    void registerObserver(UserModelObserver observer);
}
