package com.herb.task.service;

import com.herb.task.dto.AddressDTO;
import com.herb.task.mapper.AddressMapper;
import com.herb.task.repo.AddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepo repo;
    private final AddressMapper mapper;

    public void add(AddressDTO addressDTO) {
        repo.save(mapper.toAddress(addressDTO));
    }

    public AddressDTO getByID(Long id) {
        return mapper
                .toDto(repo.findById(id)
                        .orElseThrow());
    }

    public List<AddressDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(address -> mapper.toDto(address))
                .collect(Collectors.toList());
    }

    public void deleteAddressByID(Long id) {
        repo.findById(id).orElseThrow().setStatus(false);
    }
}
