package org.karpukhin.swingmvcdemo.ui.group;

import org.karpukhin.swingmvcdemo.core.model.Group;

/**
 * @author Pavel Karpukhin
 */
public interface GroupModel {

    /**
     * Returns view title
     * @return view title
     */
    String getTitle();

    /**
     * Sets view title
     * @param title view title
     */
    void setTitle(String title);

    /**
     * Returns group
     * @return group
     */
    Group getGroup();

    /**
     * Sets group
     * @param group group
     */
    void setGroup(Group group);

    boolean getVisible();

    void setVisible(boolean visible);

    /**
     * Registers model observer
     * @param observer model observer
     */
    void registerObserver(GroupModelObserver observer);
}
