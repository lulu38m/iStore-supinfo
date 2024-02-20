package com.istore.user;

import com.istore.user.exceptions.UserAlreadyExistsException;
import com.istore.user.exceptions.UserAlreadyWhitelistedException;
import com.istore.user.exceptions.UserEmailNotValidException;
import com.istore.user.exceptions.UserNotAdminException;
import com.istore.utils.EmailValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class WhitelistUserController {

    private final WhitelistUserModel whitelistUserModel;
    private final UserModel userModel;

    public Whitelist addWhitelistedEmail(User operator, String email) throws UserNotAdminException, UserEmailNotValidException, UserAlreadyExistsException {
        if (!operator.getRole().equals(Role.ADMIN)) {
            throw new UserNotAdminException(operator);
        }

        boolean isEmailValid = EmailValidator.isValid(email);
        if (!isEmailValid) {
            throw new UserEmailNotValidException(email);
        }

        Optional<User> whitelistedUser = userModel.getUserByEmail(email);
        if (whitelistedUser.isPresent()) {
            throw new UserAlreadyExistsException(whitelistedUser.get());
        }

        boolean hasWhitelistedEmail = whitelistUserModel.hasWhitelistedEmail(email);
        if (hasWhitelistedEmail) {
            throw new UserAlreadyWhitelistedException(email);
        }

        Whitelist whitelist = new Whitelist(UUID.randomUUID(), email);
        whitelistUserModel.AddWhitelist(whitelist);
        return whitelist;
    }

    public boolean containsWhitelistedEmail(String email) {
        return whitelistUserModel.listWhitelists().stream().anyMatch(whitelist -> whitelist.getEmail().equals(email));
    }

    public void removeWhitelistedEmail(Whitelist whitelist) {
        whitelistUserModel.removeWhitelist(whitelist);
    }

    public List<Whitelist> listWhitelists() {
        return whitelistUserModel.listWhitelists();
    }
}
