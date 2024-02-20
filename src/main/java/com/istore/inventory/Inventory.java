package com.istore.inventory;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Inventory {

    private final UUID id;
    private final List<Item> itemsList;

}
