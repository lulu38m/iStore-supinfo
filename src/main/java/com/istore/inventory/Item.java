package com.istore.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    public final UUID id;
    public String name;
    public int price;
    public int quantity;
    private final Inventory inventory;
}
