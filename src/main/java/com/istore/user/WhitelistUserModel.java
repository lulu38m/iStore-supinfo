package com.istore.user;

import java.util.ArrayList;
import java.util.List;

public class WhitelistUserModel {

    private final List<Whitelist> whitelistedEmails;

    public WhitelistUserModel() {
        this.whitelistedEmails = new ArrayList<>();
    }

    public void AddWhitelist(Whitelist whitelist) {
        this.whitelistedEmails.add(whitelist);
    }

    public boolean hasWhitelistedEmail(String email) {
        return this.whitelistedEmails.stream().anyMatch(whitelist -> whitelist.getEmail().equals(email));
    }

    public List<Whitelist> listWhitelists() {
        return this.whitelistedEmails;
    }

    public void removeWhitelist(Whitelist whitelist) {
        this.whitelistedEmails.remove(whitelist);
    }

}
