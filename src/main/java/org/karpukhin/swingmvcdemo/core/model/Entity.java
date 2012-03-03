package org.karpukhin.swingmvcdemo.core.model;

/**
 * The class represents abstract entity of domain model
 * @author Pavel Karpukhin
 */
public abstract class Entity {
    
    private Integer id;

    /**
     * Returns unique id of persistent object
     * @return unique id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets unique if of persistent object
     * @param id unique id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns a hash code for this entity. The hash code for an object equals
     * to its unique id.
     * @return a hash code value for this entity
     */
    @Override
    public int hashCode() {
        return id;
    }

    /**
     * Compares this entity to the given object. The result is {@code true} if
     * and only if argument is not {@code null}, the class of this entity equals
     * to the class of argument and unique id of this entity equals to unique id
     * of argument.
     * @param other the object to compare this {@code Entity} against
     * @return a result of comparison of this enity to the given object
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Entity that = (Entity) other;
        return id == that.id;
    }
}
