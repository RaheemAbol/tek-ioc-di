package org.abol.springstarter.services;


import org.abol.springstarter.models.BaseUser;
import org.abol.springstarter.models.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final UserService userService;

    public CartServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addItem(int userId, CartItem item) {
        BaseUser user = userService.getUserById(userId);
        if (user != null) {
            user.getCart().add(item);
        }
    }

    @Override
    public void updateItem(int userId, int itemIndex, CartItem item) {
        BaseUser user = userService.getUserById(userId);
        if (user != null && itemIndex >= 0 && itemIndex < user.getCart().size()) {
            user.getCart().set(itemIndex, item);
        }
    }

    @Override
    public void deleteItem(int userId, int itemIndex) {
        BaseUser user = userService.getUserById(userId);
        if (user != null && itemIndex >= 0 && itemIndex < user.getCart().size()) {
            user.getCart().remove(itemIndex);
        }
    }

    @Override
    public List<CartItem> getCart(int userId) {
        BaseUser user = userService.getUserById(userId);
        return user != null ? user.getCart() : null;
    }
}
