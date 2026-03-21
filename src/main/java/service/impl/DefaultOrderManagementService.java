package service.impl;

import model.Order;
import model.impl.DefaultOrder;
import service.OrderManagementService;

import java.util.Arrays;

public class DefaultOrderManagementService implements OrderManagementService {

    private static final int DEFAULT_ORDER_CAPACITY = 10;
    private static DefaultOrderManagementService instance;
    private Order[] orders;
    private int nextId = 0;

    public static OrderManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderManagementService();
        }
        return instance;
    }

    {
        orders = new DefaultOrder[DEFAULT_ORDER_CAPACITY];
    }

    @Override
    public void addOrder(Order order) {
        if(nextId >= orders.length) orders = Arrays.copyOf(orders, orders.length << 1);

        orders[nextId++] = order;
    }

    @Override
    public Order[] getOrdersByUserId(int userId) {
        if(orders[0] == null) return null;

        Order[] userOrders = new Order[orders.length];
        int i = 0;

        for (Order order : orders) {
            if (order == null) {
                break;
            } else if (order.getCustomerId() == userId) {
                userOrders[i++] = order;
            }
        }

        return Arrays.copyOf(userOrders, i);
    }

    @Override
    public Order[] getOrders() {
        return orders;
    }

    void clearServiceState() {
        nextId = 0;
        orders = new DefaultOrder[DEFAULT_ORDER_CAPACITY];
    }

}
