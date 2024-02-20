package com.istore.inventory;

import com.istore.store.Store;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ItemModel {

    private final List<Item> itemsList;

    public ItemModel() {
        this.itemsList = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (item.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.itemsList.add(item);
    }

    public void removeItem( Item item) {
        this.itemsList.remove( item);
    }
}
