package com.istore.user;

import com.istore.database.DbTools;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class WhitelistUserModel {

    private final DbTools dbTools;

    public WhitelistUserModel(DbTools dbTools) {
        this.dbTools = dbTools;
    }

    public void addWhitelistedEmail(Whitelist whitelist) throws RuntimeException {
        String sql = "INSERT INTO \"WHITELISTED_EMAIL\" (id, email) VALUES (?, ?)";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, whitelist.getId().toString());
            statement.setString(2, whitelist.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean hasWhitelistedEmail(String email) throws RuntimeException {
        String sql = "SELECT email FROM \"WHITELISTED_EMAIL\" WHERE email = ?";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeWhitelistedEmail(String email) throws RuntimeException {
        String sql = "DELETE FROM \"WHITELISTED_EMAIL\" WHERE email = ?";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void clearWhitelistedEmails() throws RuntimeException {
        String sql = "DELETE FROM \"WHITELISTED_EMAIL\"";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Whitelist> getWhitelistedEmails() throws RuntimeException {
        List<Whitelist> whitelistedEmails = new ArrayList<>();
        try (Connection connection = dbTools.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, email FROM \"WHITELISTED_EMAIL\"");
            while (rs.next()) {
                whitelistedEmails.add(new Whitelist(UUID.fromString(rs.getString("id")), rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return whitelistedEmails;
    }
}
