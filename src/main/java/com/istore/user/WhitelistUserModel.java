package com.istore.user;

import java.util.ArrayList;
import java.util.List;

public class WhitelistUserModel {

    private final List<String> whitelistedEmails;

    public WhitelistUserModel() {
        this.whitelistedEmails = new ArrayList<>();
    }

    public void addWhitelistedEmail(String email) {
        this.whitelistedEmails.add(email);
    }

    public List<String> getWhitelistedEmails() {
        return this.whitelistedEmails;
    }

    public void removeWhitelistedEmail(String email) {
        this.whitelistedEmails.remove(email);
    }

    public void clearWhitelistedEmails() {
        this.whitelistedEmails.clear();
    }

}
