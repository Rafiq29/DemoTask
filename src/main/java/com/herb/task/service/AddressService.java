package com.herb.task.service;

import com.herb.task.dto.AddressDTO;
import com.herb.task.entity.Address;
import com.herb.task.entity.User;
import com.herb.task.mapper.AddressMapper;
import com.herb.task.repo.AddressRepo;
import com.herb.task.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepo repo;
    private final AddressMapper mapper;
    private final UserRepo userRepo;

    public void add(AddressDTO addressDTO) {
        User user = userRepo.findById(addressDTO.getUser_id()).orElseThrow();
        Address address = mapper.toAddress(addressDTO);
        address.setUser(user);
        repo.save(address);
    }

    public AddressDTO getByID(Long id) {
        return mapper
                .toDto(repo.findById(id)
                        .filter(address -> address.getStatus())
                        .orElseThrow());
    }

    public List<AddressDTO> getAll() {
        return repo.findAll()
                .stream()
                .filter(address -> address.getStatus())
                .map(address -> mapper.toDto(address))
                .collect(Collectors.toList());
    }

    public void deleteAddressByID(Long id) {
        Address address = repo.findById(id).orElseThrow();
        address.setStatus(false);
        repo.save(address);
    }
}
