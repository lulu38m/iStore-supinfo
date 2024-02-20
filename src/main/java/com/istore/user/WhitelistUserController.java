package com.istore.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WhitelistUserController {

    private final WhitelistUserModel whitelistUserModel;

    public void addWhitelistedEmail(User operator, String email) throws UserNotAdminException {
        if (!operator.getRole().equals(Role.ADMIN)) {
            throw new UserNotAdminException(operator);
        }
        whitelistUserModel.addWhitelistedEmail(email);
    }

    // Todo remove this
    public void addWhitelistedEmail(String email) {
        whitelistUserModel.addWhitelistedEmail(email);
    }

    public boolean containsWhitelistedEmail(String email) {
        return whitelistUserModel.getWhitelistedEmails().contains(email);
    }

}
