package com.istore.inventory;

import com.istore.store.StoreListener;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ItemController {

    private final ItemModel itemModel;

    public void removeItem(Item item) {
        this.itemModel.removeItem(item);
    }

    public void addItem(Item item) {
        this.itemModel.addItem(item);
    }


}
