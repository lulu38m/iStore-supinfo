import com.istore.database.DbTools;
import com.istore.user.Role;
import com.istore.user.User;
import com.istore.user.UserController;
import com.istore.user.UserModel;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbUserTests {
    @Test
    public void testAddUser() {
        DbTools dbTools = new DbTools();
        dbTools.initDatabase();
        UserModel userModel = new UserModel(dbTools);

        userModel.addUser(new User(UUID.randomUUID(), "test@test.fr", "test", BCrypt.hashpw("test", BCrypt.gensalt()), Role.USER));

        assertEquals(1, userModel.getUsersList().size());

        User createdUser = userModel.getUsersList().get(0);

        assertEquals("test@test.fr", createdUser.getEmail());
        assertEquals("test", createdUser.getPseudo());
        assertEquals(Role.USER, createdUser.getRole());

        userModel.removeUser(createdUser);

    }

    @Test
    public void testUpdateUser() {
        DbTools dbTools = new DbTools();
        dbTools.initDatabase();
        UserModel userModel = new UserModel(dbTools);

        User user = new User(UUID.randomUUID(), "test@test1.fr", "test1", BCrypt.hashpw("test", BCrypt.gensalt()), Role.USER);

        userModel.addUser(user);

        user.setPseudo("test1");
        user.setPasswordHash(BCrypt.hashpw("test1", BCrypt.gensalt()));
        user.setRole(Role.ADMIN);

        userModel.updateUser(user);

        Optional<User> updatedUser = userModel.getUserByEmail("test@test1.fr");

        if (updatedUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        assertEquals("test1", updatedUser.get().getPseudo());
        assertEquals(Role.ADMIN, updatedUser.get().getRole());

        userModel.removeUser(user);

    }

    @Test
    public void testRemoveUser() {
        DbTools dbTools = new DbTools();
        dbTools.initDatabase();
        UserModel userModel = new UserModel(dbTools);

        User user = new User(UUID.randomUUID(), "test@test2.fr", "test2", BCrypt.hashpw("test", BCrypt.gensalt()), Role.USER);

        userModel.addUser(user);

        userModel.removeUser(user);

        assertEquals(0, userModel.getUsersList().size());

    }

    @Test
    public void testGetUserByEmail() {
        DbTools dbTools = new DbTools();
        dbTools.initDatabase();
        UserModel userModel = new UserModel(dbTools);

        User user = new User(UUID.randomUUID(), "test@test3.fr", "test3", BCrypt.hashpw("test", BCrypt.gensalt()), Role.USER);

        userModel.addUser(user);

        Optional<User> retrievedUser = userModel.getUserByEmail("test@test3.fr");

        if (retrievedUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        assertEquals("test3", retrievedUser.get().getPseudo());

        userModel.removeUser(user);

    }

}
