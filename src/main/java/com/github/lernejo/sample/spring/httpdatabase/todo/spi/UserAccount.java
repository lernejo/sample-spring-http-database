package com.github.lernejo.sample.spring.httpdatabase.todo.spi;

import java.time.Instant;

public class UserAccount {
    public final String name;
    public final String email;
    public final Instant creationDate;

    public UserAccount(String name, String email, Instant creationDate) {
        this.name = name;
        this.email = email;
        this.creationDate = creationDate;
    }
}
