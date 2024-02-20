package com.istore;

import com.istore.database.DbTools;
import com.istore.inventory.*;
import com.istore.store.Store;
import com.istore.store.StoreController;
import com.istore.store.StoreModel;
import com.istore.user.UserController;
import com.istore.user.UserModel;
import com.istore.user.WhitelistUserController;
import com.istore.user.WhitelistUserModel;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        DbTools dbTools = new DbTools();
        dbTools.initDatabase();

        UserModel userModel = new UserModel(dbTools);

        WhitelistUserModel whitelistUserModel = new WhitelistUserModel(dbTools);
        WhitelistUserController whitelistUserController = new WhitelistUserController(whitelistUserModel, userModel);

        UserController userController = new UserController(userModel, whitelistUserController);

        ItemModel itemModel = new ItemModel();
        itemModel.addItem(new Item("item1", "1", 10, 11));
        itemModel.addItem(new Item("item2", "2", 20, 21));
        itemModel.addItem(new Item("item3", "3", 30, 31));
        itemModel.addItem(new Item("item4", "4", 40, 41));


        InventoryModel inventoryModel = new InventoryModel();
        Inventory inventory = new Inventory(itemModel.getItemsList().subList(0, 2));
        Inventory inventory2 = new Inventory(itemModel.getItemsList().subList(2, 4));
        inventoryModel.addInventory(inventory);
        inventoryModel.addInventory(inventory2);

        StoreModel storeModel = new StoreModel();
        storeModel.addStore(new Store("Magasin 1", "1", inventory));
        storeModel.addStore(new Store("Magasin 2", "2", inventory2));
        StoreController storeController = new StoreController(storeModel);
        InventoryController inventoryController = new InventoryController(inventoryModel);

        Window window = new MainWindow(userController, whitelistUserController, storeController, inventoryController);
        window.setVisible(true);
    }

}