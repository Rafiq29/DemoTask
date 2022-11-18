package com.herb.task.service;

import com.herb.task.dto.UserDTO;
import com.herb.task.entity.User;
import com.herb.task.mapper.UserMapper;
import com.herb.task.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;
    private final UserMapper mapper;

    public void addUser(UserDTO userDTO) {
        User user = mapper.toUser(userDTO);
        repo.save(user);
    }

    public UserDTO getUserByID(Long id) {
        Optional<User> user = repo.findById(id);
        return mapper.toDto(user.orElseThrow());
    }

    public List<UserDTO> getUserAll() {
        return repo.findAll().stream().map(user -> mapper.toDto(user)).collect(Collectors.toList());
    }

    public void deleteByID(Long id) {
        repo.findById(id).orElseThrow().setStatus(false);
    }
}
