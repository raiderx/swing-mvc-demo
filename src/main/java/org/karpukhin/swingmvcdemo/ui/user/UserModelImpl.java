package org.karpukhin.swingmvcdemo.ui.user;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class UserModelImpl implements UserModel {

    private List<UserModelObserver> observers = new LinkedList<UserModelObserver>();
    private String title;
    private List<Group> groups = new ArrayList<Group>();
    private User user;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        for (UserModelObserver observer : observers) {
            observer.updateTitle();
        }
    }

    @Override
    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public void setGroups(List<Group> groups) {
        this.groups = groups;
        for (UserModelObserver observer : observers) {
            observer.updateGroups();
        }
    }

    @Override
    public void setUser(User user) {
        this.user = user;
        for (UserModelObserver observer : observers) {
            observer.updateUser();
        }
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void registerObserver(UserModelObserver observer) {
        observers.add(observer);
    }
}
