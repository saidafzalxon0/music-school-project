package com.example.Musicschool.service.impl;

import com.example.Musicschool.dto.DirectionDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.Department;
import com.example.Musicschool.entity.Direction;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.DepartmentRepository;
import com.example.Musicschool.repository.DirectionRepository;
import com.example.Musicschool.service.DirectionService;
import com.example.Musicschool.service.mapper.DirectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectionServiceImpl implements DirectionService {
    @Autowired
    private DirectionRepository repository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DirectionMapper mapper;


    @Override
    public ResponseDto<DirectionDto> post(DirectionDto dto) {
        if(repository.findFirstByName(dto.getName()).isPresent()){
            throw new DatabaseException("Direction is already exists");
        }
        Optional<Department> byId = departmentRepository.findById(dto.getDepartment().getId());
        if(byId.isEmpty()) throw new DatabaseException("Department is not found");
        dto.setDepartment(byId.get());
        return ResponseDto.<DirectionDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
    }

    @Override
    public ResponseDto<DirectionDto> patch(DirectionDto dto) {
        if(dto.getId() == null) throw new DatabaseException("Direction id is null");
        if(repository.existsById(dto.getId())){
            Optional<Department> byId = departmentRepository.findById(dto.getDepartment().getId());
            if(byId.isEmpty()) throw new DatabaseException("Department is not found");
            dto.setDepartment(byId.get());
            return ResponseDto.<DirectionDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
        }
        throw new DatabaseException("Direction is not found");
    }

    @Override
    public ResponseDto<DirectionDto> get(Long id) {
        Optional<Direction> direction = repository.findById(id);
        if(direction.isPresent()){
            return ResponseDto.<DirectionDto>builder().status("success").data(mapper.toDto(direction.get())).build();
        }
        throw new DatabaseException("Direction is not found");
    }

    @Override
    public ResponseDto<List<DirectionDto>> getByDepartment(Long id) {
        return ResponseDto.<List<DirectionDto>>builder().status("success").data(repository.findAllByDepartment_IdOrderByLevelAsc(id).stream().map(mapper::toDto).toList()).build();
    }

    @Override
    public ResponseDto<List<DirectionDto>> getAll() {
        return ResponseDto.<List<DirectionDto>>builder().status("success").data(repository.findAllByOrderByLevelAsc().stream().map(mapper::toDto).toList()).build();
    }

    @Override
    public ResponseDto<DirectionDto> delete(Long id) {
        Optional<Direction> direction = repository.findById(id);
        if(direction.isPresent()){
            repository.deleteById(id);
            return ResponseDto.<DirectionDto>builder().status("success").data(mapper.toDto(direction.get())).build();
        }
        throw new DatabaseException("Direction is not deleted");
    }
}
