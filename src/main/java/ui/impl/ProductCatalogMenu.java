package ui.impl;

import config.ApplicationContext;
import model.Product;
import service.ProductManagementService;
import service.impl.DefaultProductManagementService;
import ui.Menu;

import java.util.Scanner;

public class ProductCatalogMenu implements Menu {

    private static final String CHECKOUT_COMMAND = "checkout";
    private final ApplicationContext context;
    private final ProductManagementService productManagementService;

    private final Scanner scanner = new Scanner(System.in);

    {
        context = ApplicationContext.getInstance();
        productManagementService = DefaultProductManagementService.getInstance();
    }

    @Override
    public void start() {
        Menu menuToOpen;

        if (context.getLoggedInUser() != null) {
            String userInput = "";

            while (!userInput.equals(CHECKOUT_COMMAND)) {
                printProducts();
                userInput = getUserInput();
                addProductToCart(userInput);
            }

            if(!context.getSessionCart().isEmpty()) menuToOpen = new CheckoutMenu();
            else menuToOpen = new MainMenu();

            menuToOpen.start();

        } else {
            System.out.println("You must be logged in!");
        }
    }

    private void addProductToCart(String userInput) {
        if(userInput.equals(CHECKOUT_COMMAND)) return;

        int productId = parseToInt(userInput);

        if(productId > 0 && productManagementService.getProducts().length >= productId) {

            Product product = productManagementService.getProductById(productId);
            context.getSessionCart().addProduct(product);

            System.out.println("Product added to cart.");
        } else {
            System.out.println("Invalid product ID!");
        }


    }

    private int parseToInt(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Product with this id does not exist!");
            return 0;
        }
    }

    private void printProducts() {
        Product[] products = productManagementService.getProducts();

        for (Product product : products) {
            System.out.println(product);
        }
    }

    private String getUserInput() {
        System.out.println("Enter product ID you want to add to cart");
        System.out.print("Input: ");

        return scanner.nextLine();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("---Product-Catalog---");
        System.out.println("Enter product id to add it to the cart or 'menu' if you want to navigate back to the main menu");
    }

}
