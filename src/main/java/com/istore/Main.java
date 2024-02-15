package com.istore;

import com.istore.user.UserController;
import com.istore.user.UserModel;

public class Main {

    private static UserController userController;

    public static void main(String[] args) {
        UserModel userModel = new UserModel();
        userController = new UserController(userModel);

        MainWindow window = new MainWindow(userController);
        window.setVisible(true);
    }
}