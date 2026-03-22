package ui.impl;

import config.ApplicationContext;
import model.User;
import ui.Menu;

import java.util.Scanner;

public class ChangePasswordMenu implements Menu {

    Scanner scanner = new Scanner(System.in);
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();

        User user = context.getLoggedInUser();
        String newPassword = scanner.nextLine();

        user.setPassword(newPassword);
        System.out.println("Password changed successfully!");
    }

    @Override
    public void printMenuHeader() {
        System.out.println("---CHANGE-PASSWORD---");
        System.out.print("Enter new password: ");
    }

}
