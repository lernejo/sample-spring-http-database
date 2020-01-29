package com.github.lernejo.sample.spring.httpdatabase.todo.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.lernejo.sample.spring.httpdatabase.todo.api.BlankNameException;
import com.github.lernejo.sample.spring.httpdatabase.todo.api.IncorrectEmailAddressException;
import com.github.lernejo.sample.spring.httpdatabase.todo.api.UnknownUserException;
import com.github.lernejo.sample.spring.httpdatabase.todo.api.User;
import com.github.lernejo.sample.spring.httpdatabase.todo.api.UserAlreadyExistsException;
import com.github.lernejo.sample.spring.httpdatabase.todo.api.UserManager;
import com.github.lernejo.sample.spring.httpdatabase.todo.spi.DuplicateUserException;
import com.github.lernejo.sample.spring.httpdatabase.todo.spi.UserAccount;
import com.github.lernejo.sample.spring.httpdatabase.todo.spi.UserRepository;

class UserManagerImpl implements UserManager {

    private final Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    private final UserRepository repository;

    UserManagerImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createAccount(String name, String email) throws UserAlreadyExistsException, IncorrectEmailAddressException, BlankNameException {
        validateParameters(name, email);

        final UserAccount userAccount = createUserOrThrowIfExists(name, email);

        return mapToUser(userAccount);
    }

    private User mapToUser(UserAccount userAccount) {
        return new User(userAccount.name, userAccount.email, userAccount.creationDate);
    }

    private UserAccount createUserOrThrowIfExists(String name, String email) throws UserAlreadyExistsException {
        final UserAccount userAccount;
        try {
            userAccount = repository.create(name, email);
        } catch (DuplicateUserException e) {
            throw new UserAlreadyExistsException(name);
        }
        return userAccount;
    }

    private void validateParameters(String name, String email) {
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            throw new IncorrectEmailAddressException(email);
        }
        if (name.isBlank()) {
            throw new BlankNameException();
        }
    }

    @Override
    public User getByEmail(String email) throws UnknownUserException {
        UserAccount account = repository.findByEmail(email);
        if (account == null) {
            throw new UnknownUserException(email);
        }
        return mapToUser(account);
    }
}
