package org.karpukhin.swingmvcdemo.ui.user;

/**
 * @author Pavel Karpukhin
 */
public interface UserController {

    /**
     * Saves user with given first name, last name and group id
     * @param firstName first name
     * @param lastName  last name
     * @param groupId   group id
     */
    void save(String firstName, String lastName, int groupId);

    /**
     * Cancels all modifications
     */
    void cancel();
}
