package org.karpukhin.swingmvcdemo.ui.main;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class MainModelImpl implements MainModel {
    
    private List<Group> groups = new ArrayList<Group>();
    private List<User> users = new ArrayList<User>();
    private int groupId = -1;

    private List<MainModelObserver> observers = new LinkedList<MainModelObserver>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroups(List<Group> groups) {
        this.groups = groups;
        notifyObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUsers(List<User> users) {
        this.users = users;
        notifyUsersObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUsers() {
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerObserver(MainModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public int getSelectedGroup() {
        return groupId;
    }

    @Override
    public void setSelectedGroup(int groupId) {
        this.groupId = groupId;
        for (MainModelObserver observer : observers) {
            observer.updateSelectedGroup();
        }
    }

    private void notifyObservers() {
        for (MainModelObserver observer : observers) {
            observer.updateGroups();
        }
    }

    private void notifyUsersObservers() {
        for (MainModelObserver observer : observers) {
            observer.updateUsers();
        }
    }
}
