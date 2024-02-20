package com.istore.user;

import com.istore.database.DbTools;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
public class WhitelistUserModel {

    private final DbTools dbTools;

    public WhitelistUserModel(DbTools dbTools) {
        this.dbTools = dbTools;
    }

    public void addWhitelistedEmail(String email) {
        String sql = "INSERT INTO \"WHITELISTED_EMAIL\" (email) VALUES (?)";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    public void AddWhitelist(Whitelist whitelist) {
        this.whitelistedEmails.add(whitelist);
    }

    public boolean hasWhitelistedEmail(String email) {
        return this.whitelistedEmails.stream().anyMatch(whitelist -> whitelist.getEmail().equals(email));
    }

    public List<Whitelist> listWhitelists() {
        return this.whitelistedEmails;
    }

    public void removeWhitelistedEmail(String email) {
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

    public void clearWhitelistedEmails() {
        String sql = "DELETE FROM \"WHITELISTED_EMAIL\"";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Collection<Object> getWhitelistedEmails() {
        List<Object> whitelistedEmails = new ArrayList<>();
        try (Connection connection = dbTools.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT email FROM \"WHITELISTED_EMAIL\"");
            while (rs.next()) {
                whitelistedEmails.add(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return whitelistedEmails;
    }
}
