package org.karpukhin.swingmvcdemo.ui.user;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.service.GroupService;
import org.karpukhin.swingmvcdemo.core.service.UserService;
import org.karpukhin.swingmvcdemo.ui.main.MainView;

/**
 * @author Pavel Karpukhin
 */
public class UserControllerImpl implements UserController {

    private UserModel model;
    private UserView view;
    private GroupService groupService;
    private UserService userService;
    
    private int userId = -1;
    private boolean creating = false;

    public UserControllerImpl(MainView owner, GroupService groupService, UserService userService) {
        this.model = new UserModelImpl();
        this.view = new UserView(this, model, owner);
        this.groupService = groupService;
        this.userService = userService;
    }

    public void add(final int groupId) {
        creating = true;
        userId = -1;
        model.setTitle("Добавить пользователя");
        model.setGroups(groupService.getGroups());
        model.setSelectedGroup(groupId);
        view.show();
    }

    public void edit(final int userId) {
        creating = false;
        this.userId = userId;
        model.setTitle("Редактировать пользователя");
        model.setGroups(groupService.getGroups());
        view.show();
    }

    @Override
    public void save(String firstName, String lastName, int groupId) {
        if (creating) {
            Group group = groupService.getGroupById(groupId);
            userService.createUser(firstName, lastName, group);
        } else {
            Group group = groupService.getGroupById(groupId);
            userService.updateUser(userId, firstName, lastName, group);
        }
        view.hide();
    }

    @Override
    public void cancel() {
        view.hide();
    }

    private boolean validate(String firstName, String lastName, int groupId) {
        return true;
    }
}
