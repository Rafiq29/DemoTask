package com.herb.task.service;

import com.herb.task.dto.RequestAddressDTO;
import com.herb.task.dto.ResponseAddressDTO;
import com.herb.task.entity.Address;
import com.herb.task.entity.User;
import com.herb.task.error.CustomException;
import com.herb.task.mapper.AddressMapper;
import com.herb.task.repo.AddressRepo;
import com.herb.task.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepo repo;
    private final AddressMapper mapper;
    private final UserRepo userRepo;

    public void add(RequestAddressDTO requestAddressDTO) {
        Optional<User> optionalUser = userRepo.findById(requestAddressDTO.getUser_id());
        if (optionalUser.isEmpty()) throw new CustomException("Custom error");
        else {
            User user = optionalUser.get();
            Address address = mapper.toAddress(requestAddressDTO);
            address.setUser(user);
            repo.save(address);
        }
    }

    public ResponseAddressDTO getByID(Long id) {
        Address address = null;
        Optional<Address> optional = repo.findById(id)
                .filter(a -> a.getStatus());
        if (check(optional)) {
            address = optional.get();
        }
        return mapper.toDto(address);
    }

    public List<ResponseAddressDTO> getAll() {
        List<Address> addresses = repo.findAll();
        if (addresses.isEmpty()) throw new CustomException("Custom error");
        return addresses.stream()
                .filter(address -> address.getStatus())
                .map(address -> mapper.toDto(address))
                .collect(Collectors.toList());
    }

    public void deleteAddressByID(Long id) {
        Optional<Address> optional = repo.findById(id);
        if (check(optional)) {
            Address address = optional.get();
            address.setStatus(false);
            repo.save(address);
        }
    }

    private boolean check(Optional<Address> optional) {
        if (optional.isEmpty()) throw new CustomException("Custom error");
        else {
            return true;
        }
    }
}
