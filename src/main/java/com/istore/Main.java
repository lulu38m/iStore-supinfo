package com.istore;

import com.istore.store.Store;
import com.istore.store.StoreController;
import com.istore.store.StoreModel;
import com.istore.user.*;
import lombok.Getter;

public class Main {

    @Getter
    private static MainWindow window;
    private static UserController userController;
    private static StoreController storeController;

    public static void main(String[] args) {
        WhitelistUserModel whitelistUserModel = new WhitelistUserModel();
        WhitelistUserController whitelistUserController = new WhitelistUserController(whitelistUserModel);

        UserModel userModel = new UserModel();
        userController = new UserController(userModel, whitelistUserController);

        whitelistUserController.addWhitelistedEmail("aaa@aaa.fr");
        whitelistUserController.addWhitelistedEmail("bbb@bbb.fr");

        try {
            userController.createUser("aaa@aaa.fr", "aaa", "aaa");
            userController.createUser("bbb@bbb.fr", "bbb", "bbb");
        } catch (UserEmailNotWhitelistedException e) {
            e.printStackTrace();
        }

        StoreModel storeModel = new StoreModel();
        storeModel.addStore(new Store("Magasin 1", "1"));
        storeModel.addStore(new Store("Magasin 2", "2"));
        storeController = new StoreController(storeModel);
        window = new MainWindow(userController, storeController);
        window.setVisible(true);
    }

}
