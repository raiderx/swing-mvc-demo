package org.karpukhin.swingmvcdemo.ui.user;

import org.karpukhin.swingmvcdemo.core.model.Group;

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
    private int groupId = -1;

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
    public int getSelectedGroup() {
        return groupId;
    }

    @Override
    public void setSelectedGroup(int groupId) {
        this.groupId = groupId;
        for (UserModelObserver observer : observers) {
            observer.updateSelectedGroup();
        }
    }

    @Override
    public void registerObserver(UserModelObserver observer) {
        observers.add(observer);
    }
}
