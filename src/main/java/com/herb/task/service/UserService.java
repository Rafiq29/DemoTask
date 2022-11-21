package com.herb.task.service;

import com.herb.task.dto.CreateUserDTO;
import com.herb.task.dto.UserDTO;
import com.herb.task.entity.Address;
import com.herb.task.entity.User;
import com.herb.task.mapper.UserMapper;
import com.herb.task.repo.AddressRepo;
import com.herb.task.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final AddressRepo addressRepo;
    private final UserMapper mapper;

    public void addUser(CreateUserDTO userDTO) {
        User user = mapper.toUser(userDTO);
        userRepo.save(user);
    }

    public UserDTO getUserByID(Long id) {
        User user = userRepo.findById(id)
                .filter(User::getStatus)
                .orElseThrow();
        insertAddresses(user.getId());
        return mapper
                .toDto(user);
    }

    public List<UserDTO> getUserAll() {
        List<User> users = userRepo.findAll()
                .stream()
                .filter(user -> user.getStatus())
                .collect(Collectors.toList());
        users.forEach(user -> insertAddresses(user.getId()));
        return users
                .stream()
                .map(user -> mapper.toDto(user))
                .collect(Collectors.toList());
    }

    public void deleteByID(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        user.setStatus(false);
        userRepo.save(user);
    }

    private void insertAddresses(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        List<Address> addresses = addressRepo.findAll()
                .stream()
                .filter(address -> address.getUser().getId() == id)
                .collect(Collectors.toList());
        user.setAddresses(addresses);
    }
}
