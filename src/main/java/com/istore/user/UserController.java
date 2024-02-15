package com.istore.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class UserController {

    private final UserModel userModel;

    public boolean validateEmail(String email) {
        return email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    public boolean checkUserLogins(User user, String email, String password) {
        return user.getEmail().equals(email) && BCrypt.checkpw(password, user.getPasswordHash());
    }

    public boolean isUsernameAvailable(String username) {
        return userModel.getUsersList().stream().noneMatch(user -> Objects.equals(user.getUsername(), username));
    }

    public void createUser(String email, String username, String password) {
        this.userModel.addUser(new User(email, username, BCrypt.hashpw(password, BCrypt.gensalt()), Role.USER));
    }
}
