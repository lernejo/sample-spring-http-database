package com.github.lernejo.sample.spring.httpdatabase.todo.spi;

public interface UserRepository {
    UserAccount create(String name, String email) throws DuplicateUserException;

    UserAccount findByEmail(String email);
}
