package com.istore.inventory;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Inventory {

    private final List<Item> itemsList;

}
