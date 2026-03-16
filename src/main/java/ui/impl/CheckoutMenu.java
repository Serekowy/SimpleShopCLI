package ui.impl;

import config.ApplicationContext;
import service.OrderManagementService;
import service.impl.DefaultOrderManagementService;
import ui.Menu;

public class CheckoutMenu implements Menu {
    private ApplicationContext context;
    private OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        // <write your code here>
    }

    @Override
    public void printMenuHeader() {
        // <write your code here>
    }
}
