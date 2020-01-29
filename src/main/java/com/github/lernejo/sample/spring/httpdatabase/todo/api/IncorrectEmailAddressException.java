package com.github.lernejo.sample.spring.httpdatabase.todo.api;

public class IncorrectEmailAddressException extends RuntimeException {
    public IncorrectEmailAddressException(String email) {
        super("Invalid email: " + email);
    }
}
