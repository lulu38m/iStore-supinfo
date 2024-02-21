package com.istore.store;


import com.istore.inventory.Inventory;
import com.istore.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Store {

    private final String name;
    private final String id;
    private final Inventory inventory;

    @Setter
    private List<User> users;
}
