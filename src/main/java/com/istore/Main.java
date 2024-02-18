package com.istore;

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
        UserModel userModel = new UserModel();
        userModel.addUser(new User("aaa@aaa.fr", "aaa", BCrypt.hashpw("aaa", BCrypt.gensalt()), Role.USER));
        userController = new UserController(userModel);

        StoreModel storeModel = new StoreModel();
        storeModel.addStore(new Store("Magasin 1", "1"));
        storeModel.addStore(new Store("Magasin 2", "2"));
        storeController = new StoreController(storeModel);
        window = new MainWindow(userController, storeController);
        window.setVisible(true);
    }
}