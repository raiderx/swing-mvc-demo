package org.karpukhin.swingmvcdemo.ui.group;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.service.GroupService;

/**
 * @author Pavel Karpukhin
 */
public class GroupControllerImpl implements GroupController {

    private GroupModel model;
    private GroupService groupService;

    public GroupControllerImpl(GroupModel model, GroupService groupService) {
        this.model = model;
        this.groupService = groupService;
    }

    @Override
    public void addGroup() {
        model.setTitle("Добавить группу");
        model.setGroup(new Group());
        model.setVisible(true);
    }

    @Override
    public void editGroup(int id) {
        model.setTitle("Редактировать группу");
        model.setGroup(groupService.getGroupById(id));
        model.setVisible(true);
    }

    @Override
    public void create(String name, String description) {
        groupService.createGroup(name, description);
        model.setVisible(false);
    }

    @Override
    public void save(int id, String name, String description) {
        groupService.updateGroup(id, name, description);
        model.setVisible(false);
    }

    @Override
    public void cancel() {
        model.setVisible(false);
    }
}
