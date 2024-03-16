package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.AdminDto;
import com.example.Musicschool.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class AdminMapper implements CommonMapper<AdminDto, Admin>{
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "password",expression = "java(passwordEncoder.encode(adminDto.getPassword()))")
    public abstract Admin toEntity(AdminDto adminDto);
}
