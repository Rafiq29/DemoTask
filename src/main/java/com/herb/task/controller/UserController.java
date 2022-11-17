package com.herb.task.controller;

import com.herb.task.dto.UserDTO;
import com.herb.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/add")
    public String add(@RequestBody UserDTO userDTO) {
        service.addUser(userDTO);
        return "User created!";
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return service.getUserAll();
    }

    @GetMapping("/{id}")
    public UserDTO getByID(@PathVariable("id") Long id) {
        return service.getUserByID(id);
    }

    @DeleteMapping("/{id}")
    public String  deleteByID(@PathVariable("id") Long id) {
        service.deleteByID(id);
        return "User deleted!";
    }
}
