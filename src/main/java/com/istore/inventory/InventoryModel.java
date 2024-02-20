package com.istore.inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryModel {
    private final List<Inventory> inventoryList;

    public InventoryModel() {
        this.inventoryList = new ArrayList<>();
    }

    public void addInventory(Inventory item) {
        this.inventoryList.add(item);
    }

    public void removeInventory(Inventory item) {
        this.inventoryList.remove(item);
    }
}
