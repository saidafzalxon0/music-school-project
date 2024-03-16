package com.example.Musicschool.service.impl;

import com.example.Musicschool.dto.AcceptanceDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SchoolDto;
import com.example.Musicschool.entity.Acceptance;
import com.example.Musicschool.entity.School;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.AcceptanceRepository;
import com.example.Musicschool.repository.SchoolRepository;
import com.example.Musicschool.service.AcceptanceService;
import com.example.Musicschool.service.mapper.AcceptanceMapper;
import com.example.Musicschool.service.mapper.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcceptanceServiceImpl implements AcceptanceService {
    @Autowired
    private AcceptanceRepository repository;
    @Autowired
    private AcceptanceMapper mapper;

    @Override
    public ResponseDto<AcceptanceDto> post(AcceptanceDto dto) {

        return ResponseDto.<AcceptanceDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
    }

    @Override
    public ResponseDto<AcceptanceDto> patch(AcceptanceDto dto) {
        if(dto.getId() == null) throw new DatabaseException("Id not found");
        Optional<Acceptance> byId = repository.findById(dto.getId());
        if(byId.isPresent()){
            return ResponseDto.<AcceptanceDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
        }
        throw new DatabaseException("Data not updated");
    }

    @Override
    public ResponseDto<AcceptanceDto> delete(Long id) {
        Optional<Acceptance> optional = repository.findById(id);
        if(optional.isPresent()){
            repository.deleteById(id);
            return ResponseDto.<AcceptanceDto>builder().status("success").data(mapper.toDto(optional.get())).build();
        }
        throw new DatabaseException("Data is not deleted");
    }

    @Override
    public ResponseDto<List<AcceptanceDto>> get() {
        return ResponseDto.<List<AcceptanceDto>>builder().status("success").data(repository.findAll().stream().map(mapper::toDto).toList()).build();
    }
}
