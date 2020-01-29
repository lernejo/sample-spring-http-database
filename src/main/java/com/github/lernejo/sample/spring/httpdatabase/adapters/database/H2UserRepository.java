package com.github.lernejo.sample.spring.httpdatabase.adapters.database;

import java.util.Collections;

import com.github.lernejo.sample.spring.httpdatabase.todo.spi.DuplicateUserException;
import com.github.lernejo.sample.spring.httpdatabase.todo.spi.UserAccount;
import com.github.lernejo.sample.spring.httpdatabase.todo.spi.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class H2UserRepository implements UserRepository {
    
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public H2UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserAccount create(String name, String email) throws DuplicateUserException {
        jdbcTemplate.update("INSERT INTO `USER` (name, email) VALUES ()", Collections.emptyMap());
        return null;
    }

    @Override
    public UserAccount findByEmail(String email) {
        return null;
    }
}
