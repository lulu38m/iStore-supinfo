package com.istore.inventory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Inventory {

    private final UUID id;
    private final List<Item> itemsList;

}
