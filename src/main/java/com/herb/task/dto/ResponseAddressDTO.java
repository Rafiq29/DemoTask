package com.herb.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAddressDTO {
    private String city;
    private String district;
    private String street;
    private String apart_no;
}
