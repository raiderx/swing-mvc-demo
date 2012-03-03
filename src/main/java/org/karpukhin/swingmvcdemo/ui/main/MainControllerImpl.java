package org.karpukhin.swingmvcdemo.ui.main;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;
import org.karpukhin.swingmvcdemo.core.service.GroupService;
import org.karpukhin.swingmvcdemo.core.service.UserService;
import org.karpukhin.swingmvcdemo.ui.group.GroupControllerImpl;
import org.karpukhin.swingmvcdemo.ui.group.GroupModel;
import org.karpukhin.swingmvcdemo.ui.user.UserControllerImpl;
import org.karpukhin.swingmvcdemo.ui.user.UserModel;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class MainControllerImpl implements MainController {
    
    private MainModel mainModel;
    private GroupModel groupModel;
    private UserModel userModel;

    private GroupService groupService;
    private UserService userService;

    public MainControllerImpl(MainModel mainModel, GroupModel groupModel, UserModel userModel, GroupService groupService, UserService userService) {
        this.mainModel = mainModel;
        this.groupModel = groupModel;
        this.userModel = userModel;
        this.groupService = groupService;
        this.userService = userService;
        createDemoData();
        this.mainModel.setGroups(groupService.getGroups());
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
        mainModel.setSelectedGroup(groupId);
        List<User> users = userService.getUsersByGroup(groupId);
        mainModel.setUsers(users);
    }

    @Override
    public void addGroup() {
        groupModel.setTitle("Добавить группу");
        groupModel.setGroup(new Group(null, null));
        groupModel.setVisible(true);
        mainModel.setGroups(groupService.getGroups());
    }

    @Override
    public void editGroup(int groupId) {
        groupModel.setTitle("Редактировать группу");
        groupModel.setGroup(groupService.getGroupById(groupId));
        groupModel.setVisible(true);
        mainModel.setGroups(groupService.getGroups());
    }

    @Override
    public void removeGroup(int groupId) {
        groupService.deleteGroup(groupId);
        mainModel.setGroups(groupService.getGroups());
    }

    @Override
    public void addUserToGroup(int groupId) {
        userModel.setTitle("Добавить пользователя");
        userModel.setGroups(groupService.getGroups());
        userModel.setUser(new User(null, null, groupService.getGroupById(groupId)));
        userModel.setVisible(true);
        mainModel.setUsers(userService.getUsersByGroup(groupId));
    }

    @Override
    public void editUser(int userId) {
        userModel.setTitle("Редактировать пользователя");
        userModel.setGroups(groupService.getGroups());
        userModel.setUser(userService.getUserById(userId));
        userModel.setVisible(true);
        mainModel.setUsers(userService.getUsersByGroup(mainModel.getSelectedGroup()));
    }

    @Override
    public void removeUser(int userId) {
        userService.deleteUser(userId);
        List<User> users = userService.getUsersByGroup(mainModel.getSelectedGroup());
        mainModel.setUsers(users);
    }
}
