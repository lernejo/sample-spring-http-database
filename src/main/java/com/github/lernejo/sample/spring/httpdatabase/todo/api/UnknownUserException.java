package com.github.lernejo.sample.spring.httpdatabase.todo.api;

public class UnknownUserException extends RuntimeException {
    public UnknownUserException(String email) {
        super("No user with email: " + email);
    }
}
