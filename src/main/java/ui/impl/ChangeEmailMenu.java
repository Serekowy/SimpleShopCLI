package ui.impl;

import config.ApplicationContext;
import model.User;
import ui.Menu;

import java.util.Scanner;

public class ChangeEmailMenu implements Menu {

    Scanner scanner = new Scanner(System.in);
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();

        User user = context.getLoggedInUser();
        String newEmail = scanner.nextLine();

        user.setEmail(newEmail);
        System.out.println("Email successfully changed!");
    }

    @Override
    public void printMenuHeader() {
        System.out.println("---CHANGE-EMAIL---");
        System.out.print("Enter new email: ");
    }


}
