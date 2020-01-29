package com.github.lernejo.sample.spring.httpdatabase.adapters.inmemory;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.github.lernejo.sample.spring.httpdatabase.todo.spi.DuplicateUserException;
import com.github.lernejo.sample.spring.httpdatabase.todo.spi.UserAccount;
import com.github.lernejo.sample.spring.httpdatabase.todo.spi.UserRepository;

public class InMemoryUserRepository implements UserRepository {

    private final Set<UserAccount> accounts = new HashSet<>();

    @Override
    public UserAccount create(String name, String email) throws DuplicateUserException {
        if (accounts.stream().anyMatch(a -> a.name.equals(name))) {
            throw new DuplicateUserException();
        }
        UserAccount userAccount = new UserAccount(name, email, Instant.now());
        accounts.add(userAccount);
        return userAccount;
    }

    @Override
    public UserAccount findByEmail(String email) {
        return accounts.stream().filter(a -> a.email.equals(email)).findFirst().orElse(null);
    }
}
