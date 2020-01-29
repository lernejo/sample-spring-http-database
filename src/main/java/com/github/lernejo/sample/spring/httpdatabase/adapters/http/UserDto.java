package com.github.lernejo.sample.spring.httpdatabase.adapters.http;

import java.time.Instant;

import com.github.lernejo.sample.spring.httpdatabase.todo.api.User;

public class UserDto {

    public final String name;
    public final String email;
    public final Instant creationDate;

    private UserDto(String name, String email, Instant creationDate) {
        this.name = name;
        this.email = email;
        this.creationDate = creationDate;
    }

    public static UserDto from(User user) {
        return new UserDto(user.name, user.email, user.creationDate);
    }
}
