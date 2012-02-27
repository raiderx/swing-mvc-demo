package org.karpukhin.swingmvcdemo.core.model;

/**
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

    public User(String firstName, String lastName, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
