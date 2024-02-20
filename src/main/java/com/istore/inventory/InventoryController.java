package com.istore.inventory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InventoryController {
    private final InventoryModel inventoryModel;

    public Inventory addInventory(Inventory inventory) {
        this.inventoryModel.addInventory(inventory);
        return inventory;
    }

    public void removeInventory(Inventory item) {
        this.inventoryModel.removeInventory(item);
    }
}
