package com.istore.store;

import java.util.ArrayList;
import java.util.List;

public class StoreModel {

    private final List<Store> storesList;

    public StoreModel() {
        this.storesList = new ArrayList<>();
    }

    public void addStore(Store store){
        this.storesList.add(store);
    }

    public List<Store> getStoresList() {
        return storesList;
    }
}
