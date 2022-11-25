package com.herb.task.service;

import com.herb.task.dto.CreateUserDTO;
import com.herb.task.dto.UserDTO;
import com.herb.task.entity.Address;
import com.herb.task.entity.User;
import com.herb.task.error.CustomException;
import com.herb.task.mapper.UserMapper;
import com.herb.task.repo.AddressRepo;
import com.herb.task.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class UserService {

    private final UserRepo userRepo;
    private final AddressRepo addressRepo;
    private final UserMapper mapper;

    public void addUser(CreateUserDTO userDTO) {
        User user = mapper.toUser(userDTO);
        userRepo.save(user);
    }

    public UserDTO getUserByID(Long id) {
        User user = null;
        Optional<User> optional = userRepo.findById(id)
                .filter(User::getStatus);
        if (check(optional)) {
            user = optional.get();
            insertAddresses(user.getId());

        }
            return mapper.toDto(user);
    }

    //@Scheduled(cron = "0/5 * * * * *")
    public List<UserDTO> getUserAll() {
        System.out.println("Got user!");
        List<User> users = userRepo.findAll()
                .stream()
                .filter(user -> user.getStatus())
                .collect(Collectors.toList());
        if (Objects.isNull(users)) {
            throw new CustomException("Custom error");
        }
        users.forEach(user -> insertAddresses(user.getId()));
        return users
                .stream()
                .map(user -> mapper.toDto(user))
                .collect(Collectors.toList());
    }

    public void deleteByID(Long id) {
        Optional<User> optional = userRepo.findById(id);
        if (check(optional)) {
            User user = optional.get();
            user.setStatus(false);
            userRepo.save(user);
        }
    }

    private void insertAddresses(Long id) {
        Optional<User> optional = userRepo.findById(id);
        if (check(optional)) {
            User user = optional.get();
            List<Address> addresses = addressRepo.findAll()
                    .stream()
                    .filter(address -> address.getUser().getId() == id)
                    .collect(Collectors.toList());
            user.setAddresses(addresses);
        }
    }

    private boolean check(Optional<User> optional) {
        if (optional.isEmpty()) throw new CustomException("Custom error");
        else {
            return true;
        }
    }
}
