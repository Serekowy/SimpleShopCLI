package ui.impl;

import config.ApplicationContext;
import model.Order;
import model.impl.DefaultOrder;
import service.OrderManagementService;
import service.impl.DefaultOrderManagementService;
import ui.Menu;

import java.util.Scanner;

public class CheckoutMenu implements Menu {
    Scanner scanner = new Scanner(System.in);

    private ApplicationContext context;
    private OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {

        while (true) {
            printMenuHeader();

            if (!createOrder(getUserCardNumber())) continue;

            context.getSessionCart().clear();
            break;
        }

        System.out.println("Thanks a lot for your purchase. Details about order delivery are sent to your email.");
    }


    private boolean createOrder(String cardNumber) {
        Order order = new DefaultOrder();

        if(!order.isCreditCardNumberValid(cardNumber)) {
            System.out.println("Invalid card number!");
            return false;
        }

        order.setCreditCardNumber(cardNumber);
        order.setCustomerId(context.getLoggedInUser().getId());
        order.setProducts(context.getSessionCart().getProducts());

        orderManagementService.addOrder(order);

        return true;
    }

    private String getUserCardNumber() {
        return scanner.nextLine();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("---CHECKOUT-MENU---");
        System.out.print("Enter your credit card number without spaces and press enter if you confirm purchase: ");
    }
}
