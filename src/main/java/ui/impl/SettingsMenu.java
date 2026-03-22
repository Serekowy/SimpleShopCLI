package ui.impl;

import config.ApplicationContext;
import ui.Menu;

import java.util.Scanner;

public class SettingsMenu implements Menu {

    private static final String SETTINGS = "1. Change Password" + System.lineSeparator()
            + "2. Change Email";
    Scanner scanner = new Scanner(System.in);
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        if (context.getLoggedInUser() != null) {
            Menu choosenMenu;

            printMenuHeader();
            String input = scanner.nextLine();

            switch (input) {
                case "1": {
                    choosenMenu = new ChangePasswordMenu();
                    break;
                }
                case "2": {
                    choosenMenu = new ChangeEmailMenu();
                    break;
                }
                default: {
                    System.out.println("Please enter a valid option!");
                    choosenMenu = this;
                    break;
                }
            }

            choosenMenu.start();
        } else {
            System.out.println("You are not logged in!");
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("---SETTINGS---");
        System.out.println(SETTINGS);
        System.out.print("Choose an option: ");
    }


}
