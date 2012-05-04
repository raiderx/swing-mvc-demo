package org.karpukhin.swingmvcdemo.core.dao;

import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public interface SearchDao {

    /**
     * Performs the full-text search by first name or last name
     * @param text the search text
     * @return list of users
     */
    List<User> searchUsers(String text);

    void rebuildIndex();
}
