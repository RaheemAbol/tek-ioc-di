package org.abol.springstarter.services;


import org.abol.springstarter.models.CartItem;

import java.util.List;

public interface CartService {
    void addItem(int userId, CartItem item);
    void updateItem(int userId, int itemIndex, CartItem item);
    void deleteItem(int userId, int itemIndex);
    List<CartItem> getCart(int userId);
}
