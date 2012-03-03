package org.karpukhin.swingmvcdemo.ui.group;

import org.karpukhin.swingmvcdemo.core.service.GroupService;
import org.karpukhin.swingmvcdemo.ui.main.MainView;

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
