package com.istore.store;


import com.istore.inventory.Inventory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Store {

    private final String name;
    private final String id;
    private final Inventory inventory;
}
