package com.istore.inventory;

import com.istore.database.DbTools;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@RequiredArgsConstructor
public class ItemModel {
    private final DbTools dbTools;

    public List<Item> getItemsByInventoryId(UUID inventoryId) {
        String sql = "SELECT * FROM \"ITEM\" WHERE inventory_id = ?";
        try (var connection = dbTools.getConnection(); var statement = connection.prepareStatement(sql)) {
            statement.setString(1, inventoryId.toString());
            var rs = statement.executeQuery();
            var items = new ArrayList<Item>();
            while (rs.next()) {
                var id = UUID.fromString(rs.getString("id"));
                var name = rs.getString("name");
                var price = rs.getInt("price");
                var quantity = rs.getInt("quantity");
                var inventory = new Inventory(inventoryId, items);
                items.add(new Item(id, inventory, name, price, quantity));
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addItem(Item item) {
        String sql = "INSERT INTO \"ITEM\" (id, name, price, quantity, inventory_id) VALUES (?, ?, ?, ?, ?)";
        try (var connection = dbTools.getConnection(); var statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getId().toString());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getPrice());
            statement.setInt(4, item.getQuantity());
            statement.setString(5, item.getInventory().getId().toString());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeItem(Item item) {
        String sql = "DELETE FROM \"ITEM\" WHERE id = ?";
        try (var connection = dbTools.getConnection(); var statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getId().toString());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
