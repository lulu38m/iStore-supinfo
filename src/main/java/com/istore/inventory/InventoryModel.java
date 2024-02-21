package com.istore.inventory;

import com.istore.database.DbTools;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@RequiredArgsConstructor
public class InventoryModel {

    private final DbTools dbTools;
    private final ItemModel itemModel;

    public void addInventory(Inventory item) {
        String sql = "INSERT INTO \"INVENTORY\" (id) VALUES (?)";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getId().toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeInventory(Inventory item) {
        String sql = "DELETE FROM \"INVENTORY\" WHERE id = ?";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getId().toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public Inventory getInventoryById(UUID inventoryId) {
        String sql = "SELECT * FROM \"INVENTORY\" WHERE id = ?";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, inventoryId.toString());
            return new Inventory(inventoryId, itemModel.getItemsByInventoryId(inventoryId));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
