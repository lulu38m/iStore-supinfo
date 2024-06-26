package com.istore.store;

import com.istore.database.DbTools;
import com.istore.inventory.Inventory;
import com.istore.inventory.InventoryModel;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class StoreModel {

    private final DbTools dbTools;
    private final InventoryModel inventoryModel;

    public void addStore(Store store) {
        // create join with inventory and store
        String sql = "INSERT INTO \"STORE\" (id, name, inventory_id) VALUES (?, ?, ?)";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, store.getId().toString());
            statement.setString(2, store.getName());
            statement.setString(3, store.getInventory().getId().toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public Store deleteStore(Store store) {
        String sql = "DELETE FROM \"STORE\" WHERE id = ?";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, store.getId().toString());
            statement.execute();
            return store;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Store> getStoresList() {
        String sql = "SELECT * FROM \"STORE\"";
        try (Connection connection = dbTools.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<Store> stores = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                UUID inventoryId = UUID.fromString(rs.getString("inventory_id"));
                Inventory inventory = inventoryModel.getInventoryById(inventoryId);
                stores.add(new Store(UUID.fromString(id), name, inventory, new ArrayList<>()));
            }
            return stores;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}