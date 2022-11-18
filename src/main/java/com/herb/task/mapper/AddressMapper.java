package com.herb.task.mapper;

import com.herb.task.dto.AddressDTO;
import com.herb.task.entity.Address;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
    AddressDTO toDto(Address address);

    Address toAddress(AddressDTO addressDTO);
}
