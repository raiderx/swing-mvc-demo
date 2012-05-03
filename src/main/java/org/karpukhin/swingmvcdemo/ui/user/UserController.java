package org.karpukhin.swingmvcdemo.ui.user;

/**
 * @author Pavel Karpukhin
 */
public interface UserController {

    void addUserToGroup(int groupId);

    void editUser(int userId);

    void create(String firstName, String lastName, int groupId);

    /**
     * Saves user with given first name, last name, group id and closes the dialog
     * @param firstName first name
     * @param lastName  last name
     * @param groupId   group id
     */
    void save(int id, String firstName, String lastName, int groupId);

    /**
     * Cancels changes made and closes the dialog
     */
    void cancel();
}
