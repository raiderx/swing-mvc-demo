package org.karpukhin.swingmvcdemo.ui.user;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;
import org.karpukhin.swingmvcdemo.core.service.GroupService;
import org.karpukhin.swingmvcdemo.core.service.UserService;

/**
 * @author Pavel Karpukhin
 */
public class UserControllerImpl implements UserController {

    private UserModel model;
    private GroupService groupService;
    private UserService userService;
    
    public UserControllerImpl(UserModel model, GroupService groupService, UserService userService) {
        this.model = model;
        this.groupService = groupService;
        this.userService = userService;
    }

    @Override
    public void addUserToGroup(int groupId) {
        model.setTitle("Добавить пользователя");
        model.setGroups(groupService.getGroups());
        model.setUser(new User(null, null, groupService.getGroupById(groupId)));
        model.setVisible(true);
    }

    @Override
    public void editUser(int userId) {
        model.setTitle("Редактировать пользователя");
        model.setGroups(groupService.getGroups());
        model.setUser(userService.getUserById(userId));
        model.setVisible(true);
    }

    @Override
    public void create(String firstName, String lastName, int groupId) {
        Group group = groupService.getGroupById(groupId);
        userService.createUser(firstName, lastName, group);
        model.setVisible(false);
    }

    @Override
    public void save(int id, String firstName, String lastName, int groupId) {
        Group group = groupService.getGroupById(groupId);
        userService.updateUser(id, firstName, lastName, group);
        model.setVisible(false);
    }

    @Override
    public void cancel() {
        model.setVisible(false);
    }

    private boolean validate(String firstName, String lastName, int groupId) {
        return true;
    }
}
