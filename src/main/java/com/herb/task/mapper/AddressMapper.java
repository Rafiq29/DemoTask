package com.herb.task.mapper;

import com.herb.task.dto.RequestAddressDTO;
import com.herb.task.dto.ResponseAddressDTO;
import com.herb.task.entity.Address;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
    ResponseAddressDTO toDto(Address address);

    Address toAddress(ResponseAddressDTO responseAddressDTO);
    Address toAddress(RequestAddressDTO requestAddressDTO);
}
