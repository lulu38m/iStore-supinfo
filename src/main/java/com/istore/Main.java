package com.istore;

import com.istore.database.DbTools;
import com.istore.user.*;
import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

public class Main {

    @Getter
    private static MainWindow window;
    private static UserController userController;

    public static void main(String[] args) {
        DbTools dbTools = new DbTools();
        dbTools.initDatabase();

        WhitelistUserModel whitelistUserModel = new WhitelistUserModel();
        WhitelistUserController whitelistUserController = new WhitelistUserController(whitelistUserModel);

        UserModel userModel = new UserModel();
        userController = new UserController(userModel, whitelistUserController);

        // Temporary data
//        whitelistUserController.addWhitelistedEmail("bbb@bbb.fr");
        userModel.addUser(new User("aaa@aaa.fr", "aaa", BCrypt.hashpw("aaa", BCrypt.gensalt()), Role.USER));
        userModel.addUser(new User("ccc@ccc.fr", "ccc", BCrypt.hashpw("ccc", BCrypt.gensalt()), Role.ADMIN));

        window = new MainWindow(userController);
        window.setVisible(true);
    }

}