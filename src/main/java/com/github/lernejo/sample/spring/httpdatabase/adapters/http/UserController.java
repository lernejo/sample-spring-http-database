package com.github.lernejo.sample.spring.httpdatabase.adapters.http;

import com.github.lernejo.sample.spring.httpdatabase.todo.api.BlankNameException;
import com.github.lernejo.sample.spring.httpdatabase.todo.api.IncorrectEmailAddressException;
import com.github.lernejo.sample.spring.httpdatabase.todo.api.UnknownUserException;
import com.github.lernejo.sample.spring.httpdatabase.todo.api.UserAlreadyExistsException;
import com.github.lernejo.sample.spring.httpdatabase.todo.api.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/v1")
class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserManager userManager;

    UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto create(@RequestParam("name") String name,
                   @RequestParam("email") String email) throws UserAlreadyExistsException, IncorrectEmailAddressException, BlankNameException {
        return UserDto.from(userManager.createAccount(name, email));
    }

    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto get(@PathVariable("email") String email) throws UnknownUserException {
        return UserDto.from(userManager.getByEmail(email));
    }

    @ExceptionHandler({
        UserAlreadyExistsException.class,
        IncorrectEmailAddressException.class,
        BlankNameException.class,
        UnknownUserException.class
    })
    ResponseEntity<ErrorInfo> handleBadRequests(Exception e) {
        logger.warn(e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorInfo(e));
    }
}
