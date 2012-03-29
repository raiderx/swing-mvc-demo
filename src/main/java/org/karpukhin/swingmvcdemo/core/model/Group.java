package org.karpukhin.swingmvcdemo.core.model;

/**
 * The class is used to represent a group of users
 * @author Pavel Karpukhin
 */
public class Group extends Entity {
    
    private String name;
    private String description;

    /**
     * Default constructor
     */
    public Group() {
    }

    /**
     * Creates new instance with given group name and description
     * @param name        group name
     * @param description group description
     */
    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns group name
     * @return group name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets group name
     * @param name group name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns group description
     * @return group description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets group description
     * @param description group description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
