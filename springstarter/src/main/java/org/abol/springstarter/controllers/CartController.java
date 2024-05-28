package org.abol.springstarter.controllers;

import org.abol.springstarter.models.BaseUser;
import org.abol.springstarter.models.CartItem;
import org.abol.springstarter.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {

    private final UserService userService;

    @Autowired
    public CartController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public String viewCart(@PathVariable("userId") int userId, Model model) {
        BaseUser user = userService.getUserById(userId);
        model.addAttribute("cart", user.getCart());
        model.addAttribute("userId", userId);
        model.addAttribute("userName", user.getName());
        return "view-cart";
    }

    @GetMapping("/addItemForm/{userId}")
    public String showAddItemForm(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("cartItem", new CartItem());
        model.addAttribute("userId", userId);
        return "add-item-form";
    }

    @PostMapping("/addItem")
    public String addItem(@RequestParam("userId") int userId, @ModelAttribute CartItem cartItem) {
        BaseUser user = userService.getUserById(userId);
        user.getCart().add(cartItem);
        return "redirect:/cart/" + userId;
    }

    @PostMapping("/updateItem")
    public String updateItem(@RequestParam("userId") int userId, @RequestParam("index") int index, @ModelAttribute CartItem cartItem) {
        BaseUser user = userService.getUserById(userId);
        user.getCart().set(index, cartItem);
        return "redirect:/cart/" + userId;
    }

    @DeleteMapping("/deleteItem")
    public String deleteItem(@RequestParam("userId") int userId, @RequestParam("index") int index) {
        BaseUser user = userService.getUserById(userId);
        user.getCart().remove(index);
        return "redirect:/cart/" + userId;
    }

    @GetMapping("/editItem/{userId}/{index}")
    public String showEditItemForm(@PathVariable("userId") int userId, @PathVariable("index") int index, Model model) {
        BaseUser user = userService.getUserById(userId);
        CartItem item = user.getCart().get(index);
        model.addAttribute("cartItem", item);
        model.addAttribute("userId", userId);
        model.addAttribute("index", index);
        return "edit-item-form";
    }

    @GetMapping("/clearCart/{userId}")
    public String clearCart(@PathVariable("userId") int userId) {
        BaseUser user = userService.getUserById(userId);
        user.getCart().clear();
        return "redirect:/cart/" + userId;
    }
}
