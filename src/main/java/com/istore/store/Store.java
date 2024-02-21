package com.istore.store;


import com.istore.inventory.Inventory;
import com.istore.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Store {

    private final UUID id;
    private final String name;
    private final Inventory inventory;
    private List<User> users;

    public Store(String name, Inventory inventory) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.inventory = inventory;
    }
}
