package org.karpukhin.swingmvcdemo.core.model;

/**
 * The class is used to represent a user
 * @author Pavel Karpukhin
 */
public class User extends Entity {

    private String firstName;
    private String lastName;
    private Group group;

    /**
     * Default constructor
     */
    protected User() {
    }

    /**
     * Creates new instance of user with given first name, last name and group
     * @param firstName first name
     * @param lastName  last name
     * @param group     group
     */
    public User(String firstName, String lastName, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }

    /**
     * Returns user first name
     * @return user first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets user first name
     * @param firstName user first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns user last name
     * @return user last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets user last name
     * @param lastName user last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns user group
     * @return user group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets user group
     * @param group user group
     */
    public void setGroup(Group group) {
        this.group = group;
    }
}
