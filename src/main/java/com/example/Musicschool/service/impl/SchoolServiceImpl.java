package com.example.Musicschool.service.impl;

import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SchoolDto;
import com.example.Musicschool.entity.School;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.SchoolRepository;
import com.example.Musicschool.service.SchoolService;
import com.example.Musicschool.service.mapper.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository repository;
    @Autowired
    private SchoolMapper mapper;

    @Override
    public ResponseDto<SchoolDto> post(SchoolDto dto) {
        return ResponseDto.<SchoolDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
    }

    @Override
    public ResponseDto<SchoolDto> patch(SchoolDto dto) {
        if(dto.getId() == null) throw new DatabaseException("Id not found");
        Optional<School> byId = repository.findById(dto.getId());
        if(byId.isPresent()){
            return ResponseDto.<SchoolDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
        }
        throw new DatabaseException("Data not updated");
    }

    @Override
    public ResponseDto<SchoolDto> delete(Long id) {
        Optional<School> optional = repository.findById(id);
        if(optional.isPresent()){
            repository.deleteById(id);
            return ResponseDto.<SchoolDto>builder().status("success").data(mapper.toDto(optional.get())).build();
        }
        throw new DatabaseException("Data is not deleted");
    }

    @Override
    public ResponseDto<List<SchoolDto>> get() {
        return ResponseDto.<List<SchoolDto>>builder().status("success").data(repository.findAll().stream().map(mapper::toDto).toList()).build();
    }
}
