package com.github.lernejo.sample.spring.httpdatabase.adapters;

import com.github.lernejo.sample.spring.httpdatabase.adapters.inmemory.InMemoryUserRepository;
import com.github.lernejo.sample.spring.httpdatabase.todo.api.UserManager;
import com.github.lernejo.sample.spring.httpdatabase.todo.internal.TodoFactory;
import com.github.lernejo.sample.spring.httpdatabase.todo.spi.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WiringConfiguration {
    
    @Bean
    UserRepository userRepository() {
        return new InMemoryUserRepository();
    }
    
    @Bean
    UserManager userManager(UserRepository repository) {
        return new TodoFactory().createUserManager(repository);
    }
}
