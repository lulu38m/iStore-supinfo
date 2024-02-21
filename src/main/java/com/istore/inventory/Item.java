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
}
