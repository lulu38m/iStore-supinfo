package com.istore.inventory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Item {
    public final String id;
    public final String name;
    public final int price;
    public final int quantity;
}
