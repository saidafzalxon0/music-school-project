package com.example.Musicschool.service.impl;

import com.example.Musicschool.dto.DirectionDto;
import com.example.Musicschool.dto.PositionDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SubjectDto;
import com.example.Musicschool.entity.Direction;
import com.example.Musicschool.entity.Position;
import com.example.Musicschool.entity.Subject;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.PositionRepository;
import com.example.Musicschool.service.PositionService;
import com.example.Musicschool.service.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepository repository;
    @Autowired
    private PositionMapper mapper;
    @Override
    public ResponseDto<PositionDto> post(PositionDto dto) {
        if(repository.findFirstByName(dto.getName()).isPresent()){
            throw new DatabaseException("Position is already exists");
        }
        return ResponseDto.<PositionDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
    }

    @Override
    public ResponseDto<PositionDto> patch(PositionDto dto) {
        if(dto.getId() == null) throw new DatabaseException("Position id is null");
        Optional<Position> byName = repository.findFirstByName(dto.getName());
        if(byName.isEmpty()){
            if(repository.existsById(dto.getId())){
                return ResponseDto.<PositionDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
            }
            throw new DatabaseException("Position is not found");
        }else{
            if(byName.get().getId().equals(dto.getId())){
                if(repository.existsById(dto.getId())){
                    return ResponseDto.<PositionDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
                }
                throw new DatabaseException("Position is not found");
            }else{
                throw new DatabaseException("Position is already exists");
            }
        }
    }

    @Override
    public ResponseDto<PositionDto> get(Long id) {
        Optional<Position> position = repository.findById(id);
        if(position.isPresent()){
            return ResponseDto.<PositionDto>builder().status("success").data(mapper.toDto(position.get())).build();
        }
        throw new DatabaseException("Position is not found");
    }

    @Override
    public ResponseDto<List<PositionDto>> getAll() {
        return ResponseDto.<List<PositionDto>>builder().status("success").data(repository.findAll().stream().map(mapper::toDto).toList()).build();
    }

    @Override
    public ResponseDto<PositionDto> delete(Long id) {
        Optional<Position> position = repository.findById(id);
        if(position.isPresent()){
            repository.deleteById(id);
            return ResponseDto.<PositionDto>builder().status("success").data(mapper.toDto(position.get())).build();
        }
        throw new DatabaseException("Position is not deleted");
    }
}
