package org.karpukhin.swingmvcdemo.ui;

import org.karpukhin.swingmvcdemo.core.service.GroupService;
import org.karpukhin.swingmvcdemo.core.service.SearchService;
import org.karpukhin.swingmvcdemo.core.service.UserService;
import org.karpukhin.swingmvcdemo.ui.group.*;
import org.karpukhin.swingmvcdemo.ui.main.*;
import org.karpukhin.swingmvcdemo.ui.user.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
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
                        "classpath:/org/karpukhin/swingmvcdemo/core/service/applicationContext-service.xml",
                        "classpath:/org/karpukhin/swingmvcdemo/ui/applicationContext-ui.xml");
                GroupService groupService = context.getBean(GroupService.class);
                UserService userService = context.getBean(UserService.class);
                SearchService searchService = context.getBean(SearchService.class);

                searchService.rebuildIndex();

                MessageSource messageSource = context.getBean(MessageSource.class);
                
                MainModel mainModel = new MainModelImpl();
                GroupModel groupModel = new GroupModelImpl();
                UserModel userModel = new UserModelImpl();
                
                GroupController groupController = new GroupControllerImpl(groupModel, groupService);
                UserController userController = new UserControllerImpl(userModel, groupService, userService);
                MainController mainController = new MainControllerImpl(mainModel, groupController, userController, groupService, userService, searchService);

                MainView mainView = new MainView(mainController, mainModel, messageSource);
                GroupView groupView = new GroupView(groupController, groupModel, mainView, messageSource);
                UserView userView = new UserView(userController, userModel, mainView, messageSource);

                mainView.init();
                groupView.init();
                userView.init();
            }
        });
    }
}
