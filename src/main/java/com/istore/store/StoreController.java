package com.istore.store;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class StoreController {
    private final StoreModel storeModel;
    private final List<StoreListener> listeners;

    public StoreController(StoreModel storeModel) {
        this.storeModel = storeModel;
        this.listeners = new ArrayList<>();
    }

    public void addStoreListener(StoreListener listener) {
        listeners.add(listener);
    }

    public void addStore(Store store) {
        storeModel.addStore(store);
        notifyListeners(store);
    }

    private void notifyListeners(Store store) {
        for (StoreListener listener : listeners) {
            listener.storeAdded(store);
        }
    }

    public List<Store> getStoresList() {
        return storeModel.getStoresList();
    }
}
