package ui.impl;

import config.ApplicationContext;
import model.Order;
import service.OrderManagementService;
import service.impl.DefaultOrderManagementService;
import ui.Menu;

public class MyOrdersMenu implements Menu {

    private ApplicationContext context;
    private OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        if (context.getLoggedInUser() == null) {
            System.out.println("You should login first to see your orders!");
            return;
        }

        printMenuHeader();
        Order[] orders = orderManagementService.getOrdersByUserId(context.getLoggedInUser().getId());

        if (orders == null || orders.length == 0) {
            System.out.println("No orders found");
            return;
        }

        for (Order order : orders) {
            if(order != null) {
                System.out.println(order.toString());
            }
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("---ORDERS---");}
}
