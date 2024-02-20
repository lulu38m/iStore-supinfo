package com.istore.inventory;

public class ItemController {
    private final ItemModel itemModel;

    public ItemController(ItemModel itemModel) {
        this.itemModel = itemModel;
    }

    public void removeItem(Item item) {
        this.itemModel.removeItem(item);
    }
}
