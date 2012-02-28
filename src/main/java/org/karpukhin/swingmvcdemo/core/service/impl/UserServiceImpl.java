package org.karpukhin.swingmvcdemo.core.service.impl;

import org.karpukhin.swingmvcdemo.core.dao.UserDao;
import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;
import org.karpukhin.swingmvcdemo.core.service.UserService;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    /**
     * Creates new instance with given {@link UserDao} implementation
     * @param userDao {@link UserDao} implementation
     */
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(String firstName, String lastName, Group group) {
        User user = new User(firstName, lastName, group);
        userDao.saveOrUpdate(user);
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(int id, String firstName, String lastName, Group group) {
        User user = userDao.getById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGroup(group);
        userDao.saveOrUpdate(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser(int id) {
        userDao.delete(userDao.getById(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUsersByGroup(int id) {
        return userDao.getUsersByGroup(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserById(int id) {
        return userDao.getById(id);
    }
}
