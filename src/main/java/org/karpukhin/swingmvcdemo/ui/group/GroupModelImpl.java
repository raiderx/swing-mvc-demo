package org.karpukhin.swingmvcdemo.ui.group;

import org.karpukhin.swingmvcdemo.core.model.Group;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class GroupModelImpl implements GroupModel {

    private List<GroupModelObserver> observers = new LinkedList<GroupModelObserver>();
    private String title;
    private Group group;

    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Sets view title and notifies observers
     * @param title view title
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
        for (GroupModelObserver observer : observers) {
            observer.updateTitle();
        }
    }

    @Override
    public Group getGroup() {
        return group;
    }

    /**
     * Sets group and notifies observers
     * @param group group
     */
    @Override
    public void setGroup(Group group) {
        this.group = group;
        for (GroupModelObserver observer : observers) {
            observer.updateGroup();
        }
    }

    @Override
    public void registerObserver(GroupModelObserver observer) {
        observers.add(observer);
    }
}
