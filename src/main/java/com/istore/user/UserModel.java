package com.istore.user;

import com.istore.database.DbTools;
import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class UserModel implements UserLoginEventsSubscriber {

    private final DbTools dbTools;
    private final List<UserLoginEventsListener> listeners;

    public UserModel(DbTools dbTools) {
        this.dbTools = dbTools;
        this.listeners = new ArrayList<>();
    }

    public void addUser(User user){
        String sql = "INSERT INTO \"USER\" (id, email, pseudo, password, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getId().toString());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPseudo());
            statement.setString(4, user.getPasswordHash());
            statement.setString(5, user.getRole().name());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE \"USER\" SET pseudo = ?, password = ?, role = ? WHERE email = ?";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getPseudo());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getRole().name());
            statement.setString(4, user.getEmail());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM \"USER\" WHERE email = ?";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                String pseudo = rs.getString("pseudo");
                String password = rs.getString("password");
                String role = rs.getString("role");
                return new User(UUID.fromString(id), email, pseudo, password, Role.valueOf(role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    public void login(User user) {
        listeners.forEach(listener -> listener.onLogin(user));
    }

    public void logout() {
        listeners.forEach(UserLoginEventsListener::onLogout);
    }


    public List<User> getUsersList() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = DbTools.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("SELECT id, email, pseudo, role FROM \"USER\"");
            while (rs.next()) {
                String id = rs.getString("id");
                String email = rs.getString("email");
                String pseudo = rs.getString("pseudo");
                String role = rs.getString("role");

                User user = new User(UUID.fromString(id), email, pseudo, Role.valueOf(role));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return usersList;
    }


    @Override
    public void subscribe(UserLoginEventsListener listener) {
        listeners.add(listener);
    }

}