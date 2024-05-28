package org.abol.springstarter.models;

import lombok.Data;

@Data
public class CartItem {
    private int index;
    private String itemName;
    private int quantity;
    private double price;
}
