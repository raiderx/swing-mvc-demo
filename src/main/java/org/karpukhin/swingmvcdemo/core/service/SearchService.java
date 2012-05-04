package org.karpukhin.swingmvcdemo.core.service;

import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public interface SearchService {

    List<User> searchUsers(String text);

    void rebuildIndex();
}
