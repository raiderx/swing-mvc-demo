package org.karpukhin.swingmvcdemo.core.model;

/**
 * @author Pavel Karpukhin
 */
public class Group extends Entity {
    
    private String name;
    private String description;

    /**
     * Default constructor
     */
    protected Group() {
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
