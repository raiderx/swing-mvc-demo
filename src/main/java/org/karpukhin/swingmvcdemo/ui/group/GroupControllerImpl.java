package org.karpukhin.swingmvcdemo.ui.group;

import org.karpukhin.swingmvcdemo.core.service.GroupService;
import org.karpukhin.swingmvcdemo.ui.main.MainView;

/**
 * @author Pavel Karpukhin
 */
public class GroupControllerImpl implements GroupController {

    private GroupModel model;
    private GroupView view;
    private GroupService groupService;

    private int groupId = -1;
    private boolean creating = false;

    public GroupControllerImpl(MainView owner, GroupService groupService) {
        this.model = new GroupModelImpl();
        this.view = new GroupView(this, model, owner);
        this.groupService = groupService;
    }

    public void add() {
        creating = true;
        groupId = -1;
        model.setTitle("Добавить группу");
        view.show();

    }

    public void edit(int groupId) {
        creating = false;
        this.groupId = groupId;
        model.setTitle("Редактировать группу");
        model.setGroup(groupService.getGroupById(groupId));
        view.show();
    }

    @Override
    public void save(String name, String description) {
        if (creating) {
            groupService.createGroup(name, description);
        } else {
            groupService.updateGroup(groupId, name, description);
        }
        view.hide();
    }

    @Override
    public void cancel() {
        view.hide();
    }
}
