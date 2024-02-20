import com.istore.database.DbTools;
import com.istore.inventory.Inventory;
import com.istore.inventory.InventoryModel;
import com.istore.inventory.Item;
import com.istore.inventory.ItemModel;
import com.istore.store.Store;
import com.istore.store.StoreModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbStoreItemsIventoryTests {
    @Test
    public void testAddStore() {
        DbTools dbTools = new DbTools();
        dbTools.initDatabase();

        ItemModel itemModel = new ItemModel(dbTools);
        InventoryModel inventoryModel = new InventoryModel(dbTools, itemModel);
        StoreModel storeModel = new StoreModel(dbTools, inventoryModel);

        Inventory inventory = new Inventory(UUID.randomUUID(), new ArrayList<>());
        Inventory inventory2 = new Inventory(UUID.randomUUID(), new ArrayList<>());

        inventoryModel.addInventory(inventory);
        inventoryModel.addInventory(inventory2);

        itemModel.addItem(new Item(UUID.randomUUID(), inventory, "item1", 10, 11));
        itemModel.addItem(new Item(UUID.randomUUID(), inventory, "item2", 20, 21));

        itemModel.addItem(new Item(UUID.randomUUID(), inventory2, "item3", 30, 31));
        itemModel.addItem(new Item(UUID.randomUUID(), inventory2, "item4", 40, 41));

        inventory.getItemsList().add(itemModel.getItemsList().get(0));
        inventory.getItemsList().add(itemModel.getItemsList().get(1));

        inventory2.getItemsList().add(itemModel.getItemsList().get(2));
        inventory2.getItemsList().add(itemModel.getItemsList().get(3));

        Store store = new Store("Magasin 1", "1", inventory);
        Store store2 = new Store("Magasin 2", "2", inventory2);

        storeModel.addStore(store);
        storeModel.addStore(store2);

        List<Store> stores = storeModel.getStoresList();

        assertEquals(2, stores.size());
    }

}
