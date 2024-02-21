package com.istore.store;

public interface StoreListener {
    void onStoreAdded(Store store);

    void onStoreDeleted(Store store);
}