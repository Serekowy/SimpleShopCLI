package model.impl;

import model.Cart;
import model.Product;

import java.util.Arrays;

public class DefaultCart implements Cart {

    private final int AMOUNT_OF_PRODUCTS = 10;
    private int nextIndex = 0;
    private Product[] products;

    {
        products = new Product[AMOUNT_OF_PRODUCTS];
    }

    @Override
    public boolean isEmpty() {
        return nextIndex == 0;
    }

    @Override
    public void addProduct(Product product) {
        if(product == null) return;

        if(products.length >= nextIndex) {
            products = Arrays.copyOf(products, AMOUNT_OF_PRODUCTS << 1);
        }

        products[nextIndex++] = product;

    }

    @Override
    public Product[] getProducts() {
        return Arrays.copyOf(products, nextIndex);
    }

    @Override
    public void clear() {
        nextIndex = 0;
        products = new Product[AMOUNT_OF_PRODUCTS];
    }
}
