package com.github.lernejo.sample.spring.httpdatabase.todo.api;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String name) {
        super("User " + name + " already exists");
    }
}
