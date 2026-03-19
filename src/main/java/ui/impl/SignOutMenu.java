package ui.impl;

import config.ApplicationContext;
import ui.Menu;

public class SignOutMenu implements Menu {

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        if (context.getLoggedInUser() == null) System.out.println("You have not logged in!");
        else {
            context.setLoggedInUser(null);
            System.out.println("Successfully logged out!");
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("---SIGN-OUT---");
    }


}
