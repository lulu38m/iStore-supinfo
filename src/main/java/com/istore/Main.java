package com.istore;

import com.istore.inventory.Inventory;
import com.istore.inventory.Item;
import com.istore.inventory.ItemModel;
import com.istore.user.*;
import com.istore.store.Store;
import com.istore.store.StoreController;
import com.istore.store.StoreModel;
import com.istore.user.Role;
import com.istore.user.User;
import com.istore.user.UserController;
import com.istore.user.UserModel;
import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

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

        // Temporary data
//        whitelistUserController.addWhitelistedEmail("bbb@bbb.fr");
        userModel.addUser(new User("aaa@aaa.fr", "aaa", BCrypt.hashpw("aaa", BCrypt.gensalt()), Role.USER));
        userModel.addUser(new User("ccc@ccc.fr", "ccc", BCrypt.hashpw("ccc", BCrypt.gensalt()), Role.ADMIN));


        ItemModel itemModel = new ItemModel();
        itemModel.addItem(new Item("item1", "1", 10, 11));
        itemModel.addItem(new Item("item2", "2", 20, 21));
        itemModel.addItem(new Item("item3", "3", 30, 31));
        itemModel.addItem(new Item("item4", "4", 40, 41));

        Inventory inventory = new Inventory(itemModel.getItemsList().subList(0, 2));
        Inventory inventory2 = new Inventory(itemModel.getItemsList().subList(2, 4));


        StoreModel storeModel = new StoreModel();
        storeModel.addStore(new Store("Magasin 1", "1", inventory));
        storeModel.addStore(new Store("Magasin 2", "2", inventory2));

        storeController = new StoreController(storeModel);
        window = new MainWindow(userController, storeController);
        window.setVisible(true);
    }

}