package com.example.userserviceapidesign.mapstruct;

import com.example.userserviceapidesign.models.dto.UserDTO;
import com.example.userserviceapidesign.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    UserDTO toDTO(User user);


    User toEntity(UserDTO userDTO);
}


