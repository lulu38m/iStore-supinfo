package com.istore;

import com.istore.database.DbTools;
import com.istore.inventory.InventoryController;
import com.istore.inventory.InventoryModel;
import com.istore.inventory.ItemController;
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

        ItemModel itemModel = new ItemModel(dbTools);
        ItemController itemController = new ItemController(itemModel);
        InventoryModel inventoryModel = new InventoryModel(dbTools, itemModel);
        InventoryController inventoryController = new InventoryController(inventoryModel);
        StoreModel storeModel = new StoreModel(dbTools, inventoryModel);
        StoreController storeController = new StoreController(storeModel);

        UserModel userModel = new UserModel(dbTools, storeController);

        WhitelistUserModel whitelistUserModel = new WhitelistUserModel(dbTools);
        WhitelistUserController whitelistUserController = new WhitelistUserController(whitelistUserModel, userModel);

        UserController userController = new UserController(userModel, whitelistUserController);

        MainWindow window = new MainWindow(userController, whitelistUserController, storeController, inventoryController, itemController);
        window.setVisible(true);
    }

}