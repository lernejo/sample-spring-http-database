package com.github.lernejo.sample.spring.httpdatabase.todo.internal;

import com.github.lernejo.sample.spring.httpdatabase.todo.api.UserManager;
import com.github.lernejo.sample.spring.httpdatabase.todo.spi.UserRepository;

public class TodoFactory {
    
    public UserManager createUserManager(UserRepository repository) {
        return new UserManagerImpl(repository);
    }
}
