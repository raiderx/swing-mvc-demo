package org.karpukhin.swingmvcdemo.ui.main;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;
import org.karpukhin.swingmvcdemo.core.service.GroupService;
import org.karpukhin.swingmvcdemo.core.service.UserService;
import org.karpukhin.swingmvcdemo.ui.group.GroupControllerImpl;
import org.karpukhin.swingmvcdemo.ui.user.UserControllerImpl;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class MainControllerImpl implements MainController {
    
    private MainModel model;
    private MainView view;

    private GroupService groupService;
    private UserService userService;

    public MainControllerImpl(MainModel model, GroupService groupService, UserService userService) {
        this.model = model;
        this.view = new MainView(this, model);
        this.groupService = groupService;
        this.userService = userService;
        createDemoData();
        this.model.setGroups(groupService.getGroups());
    }

    private void createDemoData() {
        Group GROUP1 = groupService.createGroup("Композиторы", null);
        Group GROUP2 = groupService.createGroup("Писатели", null);
        Group GROUP3 = groupService.createGroup("Художники", null);

        userService.createUser("Михаил", "Глинка", GROUP1);
        userService.createUser("Модест", "Мусоргский", GROUP1);
        userService.createUser("Александр", "Бородин", GROUP1);
        userService.createUser("Николай", "Римский-Корсаков", GROUP1);
        userService.createUser("Петр", "Чайковский", GROUP1);
        userService.createUser("Сергей", "Рахманинов", GROUP1);
        userService.createUser("Александр", "Скрябин", GROUP1);
        userService.createUser("Игорь", "Стравинский", GROUP1);
        userService.createUser("Сергей", "Прокофьев", GROUP1);
        userService.createUser("Дмитрий", "Шостакович", GROUP1);
        userService.createUser("Николай", "Мясковский", GROUP1);
        userService.createUser("Георгий", "Свиридов", GROUP1);
        userService.createUser("Арам", "Хачатурян", GROUP1);
        userService.createUser("Альфред", "Шнитке", GROUP1);

        userService.createUser("Александр", "Пушкин", GROUP2);
        userService.createUser("Василий", "Жуковский", GROUP2);
        userService.createUser("Александр", "Грибоедов", GROUP2);
        userService.createUser("Фёдор", "Тютчев", GROUP2);
        userService.createUser("Николай", "Гоголь", GROUP2);
        userService.createUser("Михаил", "Лермонтов", GROUP2);
        userService.createUser("Петр", "Ершов", GROUP2);
        userService.createUser("Алексей", "Толстой", GROUP2);
        userService.createUser("Иван", "Тургенев", GROUP2);
        userService.createUser("Афанасий", "Фет", GROUP2);
        userService.createUser("Николай", "Некрасов", GROUP2);
        userService.createUser("Федор", "Достоевский", GROUP2);
        userService.createUser("Александр", "Островский", GROUP2);
        userService.createUser("Лев", "Толстой", GROUP2);
        userService.createUser("Николай", "Лесков", GROUP2);
        userService.createUser("Дмитрий", "Мамин-Сибиряк", GROUP2);
        userService.createUser("Антон", "Чехов", GROUP2);
    }

    @Override
    public void selectGroup(int groupId) {
        model.setSelectedGroup(groupId);
        List<User> users = userService.getUsersByGroup(groupId);
        model.setUsers(users);
    }

    @Override
    public void addGroup() {
        GroupControllerImpl groupController = new GroupControllerImpl(view, groupService);
        groupController.add();
        model.setGroups(groupService.getGroups());
    }

    @Override
    public void editGroup(int groupId) {
        GroupControllerImpl groupController = new GroupControllerImpl(view, groupService);
        groupController.edit(groupId);
        model.setGroups(groupService.getGroups());
    }

    @Override
    public void removeGroup(int groupId) {
        groupService.deleteGroup(groupId);
        model.setGroups(groupService.getGroups());
    }

    @Override
    public void addUserToGroup(int groupId) {
        UserControllerImpl userController = new UserControllerImpl(view, groupService, userService);
        userController.add(groupId);
        List<User> users = userService.getUsersByGroup(groupId);
        model.setUsers(users);
    }

    @Override
    public void editUser(int userId) {
        UserControllerImpl userController = new UserControllerImpl(view, groupService, userService);
        userController.edit(userId);
        List<User> users = userService.getUsersByGroup(model.getSelectedGroup());
        model.setUsers(users);
    }

    @Override
    public void removeUser(int userId) {
        userService.deleteUser(userId);
        List<User> users = userService.getUsersByGroup(model.getSelectedGroup());
        model.setUsers(users);
    }
}
