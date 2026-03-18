package ui.impl;

import config.ApplicationContext;
import ui.Menu;

import java.util.Scanner;

public class MainMenu implements Menu {

    public static final String MENU_COMMAND = "menu";

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign In"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List" + System.lineSeparator();

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign Out"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List" + System.lineSeparator();

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        if (context == null) context.setMainMenu(this);

        boolean running = true;
        Menu choosenMenu = null;
        Scanner scanner = new Scanner(System.in);

        while (true) {

            printMenuHeader();
            String userInput = scanner.nextLine();
            if (MENU_COMMAND.equalsIgnoreCase(userInput)) break;

            switch (userInput) {
                case "1":
                    choosenMenu = new SignUpMenu();
                    break;
                case "2":
                    if (context.getLoggedInUser() == null) choosenMenu = new SignInMenu();
                    else choosenMenu = new SignOutMenu();
                    break;
                case "3":
                    choosenMenu = new ProductCatalogMenu();
                    break;
                case "4":
                    choosenMenu = new MyOrdersMenu();
                    break;
                case "5":
                    choosenMenu = new SettingsMenu();
                    break;
                case "6":
                    choosenMenu = new CustomerListMenu();
                    break;
                default:
                    System.out.println("Invalid input, choose option 1-5. Please try again.");
                    continue;
            }
            choosenMenu.start();
        }

    }

    @Override
    public void printMenuHeader() {
        System.out.println("---MAIN-MENU---");
        if (context.getLoggedInUser() == null) {
            System.out.print(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
        } else {
            System.out.print(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
        }
        System.out.print("Input: ");
    }
}
