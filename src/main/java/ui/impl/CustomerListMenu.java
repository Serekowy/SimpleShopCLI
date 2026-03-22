package ui.impl;

import config.ApplicationContext;
import model.User;
import service.UserManagementService;
import service.impl.DefaultUserManagementService;
import ui.Menu;

public class CustomerListMenu implements Menu {
    private ApplicationContext context;
    private UserManagementService userManagementService;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {

        User users[] = userManagementService.getUsers();

        if (users[1] == null) {
            System.out.println("No users found");
        }

        for (User user : users) {
            if (user != null) {
                System.out.println(user.toString());
            }
        }

    }

    @Override
    public void printMenuHeader() {
        System.out.println("---CUSTOMERS---");
    }
}
