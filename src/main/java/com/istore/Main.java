package com.istore;

import com.istore.database.DbTools;
import com.istore.store.Store;
import com.istore.store.StoreController;
import com.istore.store.StoreModel;
import com.istore.user.UserController;
import com.istore.user.UserModel;
import com.istore.user.WhitelistUserController;
import com.istore.user.WhitelistUserModel;
import lombok.Getter;

public class Main {

    @Getter
    private static MainWindow window;
    private static UserController userController;
    private static StoreController storeController;

    public static void main(String[] args) {
        DbTools dbTools = new DbTools();
        dbTools.initDatabase();

        WhitelistUserModel whitelistUserModel = new WhitelistUserModel(dbTools);
        WhitelistUserController whitelistUserController = new WhitelistUserController(whitelistUserModel);

        UserModel userModel = new UserModel(dbTools);
        userController = new UserController(userModel, whitelistUserController);

        StoreModel storeModel = new StoreModel();
        storeModel.addStore(new Store("Magasin 1", "1"));
        storeModel.addStore(new Store("Magasin 2", "2"));
        storeController = new StoreController(storeModel);
        window = new MainWindow(userController, storeController);
        window.setVisible(true);
    }

}