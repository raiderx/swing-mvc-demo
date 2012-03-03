package org.karpukhin.swingmvcdemo.ui;

import org.karpukhin.swingmvcdemo.core.service.GroupService;
import org.karpukhin.swingmvcdemo.core.service.UserService;
import org.karpukhin.swingmvcdemo.ui.group.*;
import org.karpukhin.swingmvcdemo.ui.main.*;
import org.karpukhin.swingmvcdemo.ui.user.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;

/**
 * @author Pavel Karpukhin
 */
public class MvcDemo {
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationContext context = new ClassPathXmlApplicationContext(
                        "classpath:/org/karpukhin/swingmvcdemo/core/dao/applicationContext-dao.xml",
                        "classpath:/org/karpukhin/swingmvcdemo/core/service/applicationContext-service.xml");
                GroupService groupService = context.getBean(GroupService.class);
                UserService userService = context.getBean(UserService.class);
                
                MainModel mainModel = new MainModelImpl();
                GroupModel groupModel = new GroupModelImpl();
                UserModel userModel = new UserModelImpl();
                
                MainController mainController = new MainControllerImpl(mainModel, groupModel, userModel, groupService, userService);
                GroupController groupController = new GroupControllerImpl(groupModel, groupService);
                UserController userController = new UserControllerImpl(userModel, groupService, userService);
                
                MainView mainView = new MainView(mainController, mainModel);
                GroupView groupView = new GroupView(groupController, groupModel, mainView);
                UserView userView = new UserView(userController, userModel, mainView);
            }
        });
    }
}
