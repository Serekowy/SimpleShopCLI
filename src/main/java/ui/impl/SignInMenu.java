package ui.impl;

import config.ApplicationContext;
import model.User;
import service.UserManagementService;
import service.impl.DefaultUserManagementService;
import ui.Menu;

import java.util.Scanner;

public class SignInMenu implements Menu {

    private ApplicationContext context;
    private UserManagementService userManagementService;

    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);

        printMenuHeader();
        System.out.print("Input your email: ");
        String email = scanner.nextLine();
        System.out.print("Input your password: ");
        String password = scanner.nextLine();


        User user = userManagementService.getUserByEmail(email);

        if (user == null) {
            System.out.println("User with that email not exist!");
        } else if (user.getPassword().equals(password)) {
            context.setLoggedInUser(user);
            System.out.println("You have successfully logged in!");
        } else {
            System.out.println("Invalid password!");
        }

    }

    @Override
    public void printMenuHeader() {
        System.out.println("---LOGIN-MENU---");
    }

}
