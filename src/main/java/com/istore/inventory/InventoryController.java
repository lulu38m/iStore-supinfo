package com.istore.inventory;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class InventoryController {
    private final InventoryModel inventoryModel;

    public Inventory addInventory(List<Item> items) {
        Inventory inventory = new Inventory(UUID.randomUUID(), items);
        this.inventoryModel.addInventory(inventory);
        return inventory;
    }

    public void removeInventory(Inventory item) {
        this.inventoryModel.removeInventory(item);
    }

}
