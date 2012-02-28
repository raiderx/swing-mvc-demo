package org.karpukhin.swingmvcdemo.ui;

import org.karpukhin.swingmvcdemo.core.service.GroupService;
import org.karpukhin.swingmvcdemo.core.service.UserService;
import org.karpukhin.swingmvcdemo.ui.main.MainControllerImpl;
import org.karpukhin.swingmvcdemo.ui.main.MainModel;
import org.karpukhin.swingmvcdemo.ui.main.MainModelImpl;
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
                ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
                        "classpath:/org/karpukhin/swingmvcdemo/core/dao/applicationContext-dao.xml",
                        "classpath:/org/karpukhin/swingmvcdemo/core/service/applicationContext-service.xml"
                });
                GroupService groupService = context.getBean(GroupService.class);
                UserService userService = context.getBean(UserService.class);
                MainModel model = new MainModelImpl();
                new MainControllerImpl(model, groupService, userService);
            }
        });
    }
}
