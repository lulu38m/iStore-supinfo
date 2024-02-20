package com.istore.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    public final String id;
    public String name;
    public int price;
    public int quantity;

}
