import com.istore.user.Role;
import com.istore.user.User;
import com.istore.user.UserController;
import com.istore.user.UserModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {
    @Test
    public void testUserCreation() {
        User user = new User("test@example.com", "testUser", "hashedPassword", Role.USER);

        assertEquals("test@example.com", user.getEmail());
        assertEquals("testUser", user.getPseudo());
        assertEquals("hashedPassword", user.getPasswordHash());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    public void testUserController() {
        UserModel userModel = new UserModel();
        UserController userController = new UserController(userModel);

        userController.createUser("test@example.com", "testUser", "password");

        assertEquals(1, userModel.getUsersList().size());

        User createdUser = userModel.getUsersList().get(0);
        assertTrue(userController.checkUserLogins(createdUser, "testUser", "password"));

        assertFalse(userController.isPseudoAvailable("testUser"));
        assertFalse(userController.isEmailAvailable("test@example.com"));

        User retrievedUser = userController.getUserByUsername("testUser");
        assertNotNull(retrievedUser);
        assertEquals(createdUser, retrievedUser);
    }

    @Test
    public void testValidateEmail() {
        UserController userController = new UserController(new UserModel());

        assertTrue(userController.validateEmail("test@example.com"));
        assertFalse(userController.validateEmail("invalid-email"));
    }


    @Test
    public void testAddUser() {
        UserModel userModel = new UserModel();
        User user = new User("test@example.com", "testUser", "hashedPassword", Role.USER);

        userModel.addUser(user);

        assertTrue(userModel.getUsersList().contains(user));
    }


    @Test
    public void testUpdateUser() {
        UserModel userModel = new UserModel();
        User originalUser = new User("test@example.com", "testUser", "hashedPassword", Role.USER);
        userModel.addUser(originalUser);

        User updatedUser = new User("test@example.com", "updatedUser", "newHashedPassword", Role.ADMIN);
        assertTrue(userModel.updateUser(updatedUser));

        assertEquals(updatedUser, userModel.getUsersList().get(0));
    }

}
