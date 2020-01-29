package com.github.lernejo.sample.spring.httpdatabase.todo.api;

import java.time.Instant;

public class User {
    public final String name;
    public final String email;
    public final Instant creationDate;

    public User(String name, String email, Instant creationDate) {
        this.name = name;
        this.email = email;
        this.creationDate = creationDate;
    }
}
