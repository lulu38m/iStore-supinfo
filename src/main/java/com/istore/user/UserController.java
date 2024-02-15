package com.istore.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.*;

import java.util.Objects;

@RequiredArgsConstructor
public class UserController {

    private final UserModel userModel;

    public boolean validateEmail(String email) {
        return email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    public boolean checkUserLogins(User user, String username, String password) {
        return user.getPseudo().equals(username) && BCrypt.checkpw(password, user.getPasswordHash());
    }

    public boolean isPseudoAvailable(String username) {
        return userModel.getUsersList().stream().noneMatch(user -> Objects.equals(user.getPseudo(), username));
    }

    public boolean isEmailAvailable(String email) {
        return userModel.getUsersList().stream().noneMatch(user -> Objects.equals(user.getEmail(), email));
    }

    public void createUser(String email, String username, String password) {
        this.userModel.addUser(new User(email, username, BCrypt.hashpw(password, BCrypt.gensalt()), Role.USER));
    }

    public User getUserByUsername(String username) {
        return userModel.getUsersList().stream().filter(user -> Objects.equals(user.getPseudo(), username)).findFirst().orElse(null);
    }
}
