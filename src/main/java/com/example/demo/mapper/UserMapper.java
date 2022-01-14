package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDto entityToDto(User user) ;
	User dtoToEntity(UserDto userDto) ;
}
