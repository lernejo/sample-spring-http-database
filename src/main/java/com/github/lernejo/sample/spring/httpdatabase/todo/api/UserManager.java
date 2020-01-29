package com.github.lernejo.sample.spring.httpdatabase.todo.api;

public interface UserManager {
    
    User createAccount(String name, String email) throws UserAlreadyExistsException, IncorrectEmailAddressException, BlankNameException;

    User getByEmail(String email) throws UnknownUserException;
}
