package com.herb.task.controller;

import com.herb.task.dto.RequestAddressDTO;
import com.herb.task.dto.ResponseAddressDTO;
import com.herb.task.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService service;

    @PostMapping("/add")
    public String add(@RequestBody RequestAddressDTO requestAddressDTO) {
        service.add(requestAddressDTO);
        return "User created!";
    }

    @GetMapping
    public List<ResponseAddressDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseAddressDTO getByID(@PathVariable("id") Long id) {
        return service.getByID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteByID(@PathVariable("id") Long id) {
        service.deleteAddressByID(id);
        return "User deleted!";
    }
}
