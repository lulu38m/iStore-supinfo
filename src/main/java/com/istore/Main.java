package com.istore;

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

    public static void main(String[] args) {
        UserModel userModel = new UserModel();
        userModel.addUser(new User("aaa@aaa.fr", "aaa", BCrypt.hashpw("aaa", BCrypt.gensalt()), Role.USER));
        userController = new UserController(userModel);

        window = new MainWindow(userController);
        window.setVisible(true);
    }
}