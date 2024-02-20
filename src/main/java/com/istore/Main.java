package com.istore;

import com.istore.database.DbTools;
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

        WhitelistUserModel whitelistUserModel = new WhitelistUserModel();
        WhitelistUserController whitelistUserController = new WhitelistUserController(whitelistUserModel, userModel);

        UserController userController = new UserController(userModel, whitelistUserController);
        
        StoreModel storeModel = new StoreModel();
        storeModel.addStore(new Store("Magasin 1", "1"));
        storeModel.addStore(new Store("Magasin 2", "2"));
        StoreController storeController = new StoreController(storeModel);

        Window window = new MainWindow(userController, whitelistUserController, storeController);
        window.setVisible(true);
    }

}