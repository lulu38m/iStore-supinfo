package com.istore;

import com.istore.database.DbTools;
import com.istore.inventory.InventoryController;
import com.istore.inventory.InventoryModel;
import com.istore.inventory.ItemModel;
import com.istore.store.StoreController;
import com.istore.store.StoreModel;
import com.istore.user.UserController;
import com.istore.user.UserModel;
import com.istore.user.WhitelistUserController;
import com.istore.user.WhitelistUserModel;

public class Main {

    public static void main(String[] args) {
        DbTools dbTools = new DbTools();
        dbTools.initDatabase();

        UserModel userModel = new UserModel(dbTools);

        WhitelistUserModel whitelistUserModel = new WhitelistUserModel(dbTools);
        WhitelistUserController whitelistUserController = new WhitelistUserController(whitelistUserModel, userModel);

        UserController userController = new UserController(userModel, whitelistUserController);

//        ItemModel itemModel = new ItemModel();
//        itemModel.addItem(new Item("item1", "1", 10, 11));
//        itemModel.addItem(new Item("item2", "2", 20, 21));
//        itemModel.addItem(new Item("item3", "3", 30, 31));
//        itemModel.addItem(new Item("item4", "4", 40, 41));
//
//        Inventory inventory = new Inventory(itemModel.getItemsList().subList(0, 2));
//        Inventory inventory2 = new Inventory(itemModel.getItemsList().subList(2, 4));
//
        ItemModel itemModel = new ItemModel(dbTools);
        InventoryModel inventoryModel = new InventoryModel(dbTools, itemModel);
        InventoryController inventoryController = new InventoryController(inventoryModel);
        StoreModel storeModel = new StoreModel(dbTools, inventoryModel);
//        storeModel.addStore(new Store("Magasin 1", "1", inventory));
//        storeModel.addStore(new Store("Magasin 2", "2", inventory2));

        StoreController storeController = new StoreController(storeModel);
        MainWindow window = new MainWindow(userController, whitelistUserController, storeController, inventoryController);
        window.setVisible(true);
    }

}