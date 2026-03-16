package ui.impl;

import config.ApplicationContext;
import service.UserManagementService;
import service.impl.DefaultUserManagementService;
import ui.Menu;

public class SignUpMenu implements Menu {
    private UserManagementService userManagementService;
    private ApplicationContext context;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        // <write your code here>
    }

    @Override
    public void printMenuHeader() {
        // <write your code here>
    }

}
