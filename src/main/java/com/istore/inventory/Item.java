package com.istore.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    public final UUID id;
    private final Inventory inventory;
    public String name;
    public int price;
    public int quantity;

    public Item(String name, int price, int quantity, Inventory inventory) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.inventory = inventory;
    }
}
