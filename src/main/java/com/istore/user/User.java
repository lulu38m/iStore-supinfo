package com.istore.user;

import com.istore.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private final UUID id;
    private String email;
    private String pseudo;
    private String passwordHash;
    private Role role;

    private List<Store> stores;

    public User(UUID id, String email, String pseudo, Role role, List<Store> stores) {
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.role = role;
        this.stores = stores;
    }

    public User clone() {
        return new User(this.id, this.email, this.pseudo, this.role, this.stores);
    }

}
