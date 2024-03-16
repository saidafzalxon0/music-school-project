package com.example.Musicschool.service.impl;

import com.example.Musicschool.dto.AdminDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.Admin;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.AdminRepository;
import com.example.Musicschool.security.JwtService;
import com.example.Musicschool.service.AdminService;
import com.example.Musicschool.service.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository repository;
    @Autowired
    private AdminMapper mapper;
    @Autowired
    private JwtService jwtService;
    @Override
    public ResponseDto<AdminDto> signUp(AdminDto dto) {
        Optional<Admin> find = repository.findByLogin(dto.getLogin());
        if(find.isPresent()){
            throw new DatabaseException("Login is already exists");
        }
        return ResponseDto.<AdminDto>builder()
                .status("success")
                .data(mapper.toDto(repository.save(mapper.toEntity(dto))))
                .build();
    }

    @Override
    public ResponseDto<String> signIn(AdminDto dto) {
        Optional<Admin> optional = repository.findByLoginAndPassword(dto.getLogin(), dto.getPassword());
        if(optional.isEmpty()){
            throw new DatabaseException("User not found!");
        }
        var jwtToken = jwtService.generateToken(optional.get());
        return ResponseDto.<String>builder()
                .data(jwtToken)
                .status("success")
                .build();
    }

    @Override
    public ResponseDto<String> patch(AdminDto dto) {
        Optional<Admin> find = repository.findById(dto.getId());
        if(find.isEmpty()){
            throw new DatabaseException("Admin not found");
        }
        find = Optional.of(repository.save(mapper.toEntity(dto)));
        var jwtToken = jwtService.generateToken(find.get());
        return ResponseDto.<String>builder()
                .data(jwtToken)
                .status("success")
                .build();
    }

    @Override
    public ResponseDto<AdminDto> delete(Long id) {
        Optional<Admin> find = repository.findById(id);
        if(find.isEmpty()){
            throw new DatabaseException("Admin not found");
        }
        repository.deleteById(id);
        return ResponseDto.<AdminDto>builder()
                .status("success")
                .data(find.map(mapper::toDto).get())
                .build();
    }

    @Override
    public ResponseDto<List<AdminDto>> getAllAdmin() {
        return ResponseDto.<List<AdminDto>>builder()
                .status("success")
                .data(repository.findAll().stream().map(mapper::toDto).toList())
                .build();

    }
}
