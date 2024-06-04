package com.assignment.demo.controller;

import com.assignment.demo.dto.UserDto;
import com.assignment.demo.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUsers(@Valid UserDto userDto) {
        log.debug("REST request to save Users : {}", userDto);

        UserDto result = userService.save(userDto);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(
    ) {
        log.debug("REST request to get all Users");

        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        log.debug("REST request to get User : {}", id);
        UserDto userDto = userService.findOne(id);
        return ResponseEntity.ok(userDto);
    }

}
