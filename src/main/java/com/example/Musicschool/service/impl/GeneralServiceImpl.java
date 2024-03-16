package com.example.Musicschool.service.impl;

import com.example.Musicschool.dto.GeneralDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.General;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.GeneralRepository;
import com.example.Musicschool.service.GeneralService;
import com.example.Musicschool.service.mapper.GeneralMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GeneralServiceImpl implements GeneralService {
    @Autowired
    private GeneralRepository repository;
    @Autowired
    private GeneralMapper mapper;

    @Override
    public ResponseDto<GeneralDto> post(GeneralDto dto) {
        return ResponseDto.<GeneralDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
    }

    @Override
    public ResponseDto<GeneralDto> patch(GeneralDto dto) {
        if(dto.getId() == null) throw new DatabaseException("Id not found");
        Optional<General> byId = repository.findById(dto.getId());
        if(byId.isPresent()){
            return ResponseDto.<GeneralDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
        }
        throw new DatabaseException("Data not updated");
    }

    @Override
    public ResponseDto<GeneralDto> delete(Long id) {
        Optional<General> optional = repository.findById(id);
        if(optional.isPresent()){
            repository.deleteById(id);
            return ResponseDto.<GeneralDto>builder().status("success").data(mapper.toDto(optional.get())).build();
        }
        throw new DatabaseException("Data is not deleted");
    }

    @Override
    public ResponseDto<List<GeneralDto>> get() {
        return ResponseDto.<List<GeneralDto>>builder().status("success").data(repository.findAll().stream().map(mapper::toDto).toList()).build();
    }
}
