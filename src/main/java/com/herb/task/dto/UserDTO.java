package com.herb.task.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String password;
    private List<ResponseAddressDTO> addresses;
}
