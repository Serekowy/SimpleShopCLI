package ui.impl;

import config.ApplicationContext;
import model.impl.DefaultUser;
import service.UserManagementService;
import service.impl.DefaultUserManagementService;
import ui.Menu;

import java.util.Scanner;

public class SignUpMenu implements Menu {
    private UserManagementService userManagementService;
    private ApplicationContext context;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);

        printMenuHeader();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your second name: ");
        String secondName = scanner.nextLine();
        System.out.print("Enter your e-mail: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        DefaultUser user = new DefaultUser(name, secondName, password, email);
        String errorMessage = userManagementService.registerUser(user);

        if (errorMessage.isEmpty()) {
            context.setLoggedInUser(user);
            System.out.println("You have successfully registered!");
        } else {
            System.out.println(errorMessage);
        }

    }

    @Override
    public void printMenuHeader() {
        System.out.println("---SIGN-UP-MENU---");
    }

}
