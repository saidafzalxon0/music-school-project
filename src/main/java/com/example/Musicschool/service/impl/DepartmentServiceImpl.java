package com.example.Musicschool.service.impl;

import com.example.Musicschool.dto.DepartmentDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.Department;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.projection.DepartmentProjection;
import com.example.Musicschool.repository.DepartmentRepository;
import com.example.Musicschool.service.DepartmentService;
import com.example.Musicschool.service.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;
    @Autowired
    private DepartmentMapper mapper;
    @Override
    public ResponseDto<DepartmentDto> post(DepartmentDto dto) {
        if(repository.findFirstByName(dto.getName()).isPresent()){
            throw new DatabaseException("Department is already exists");
        }
        return ResponseDto.<DepartmentDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
    }

    @Override
    public ResponseDto<List<DepartmentProjection>> getAllDirection(Long id) {
            return ResponseDto.<List<DepartmentProjection>>builder().status("success").data(repository.listAllDirectionByDepartmentId(id)).build();
    }


    @Override
    public ResponseDto<List<DepartmentProjection>> getAllDepartment() {
            return ResponseDto.<List<DepartmentProjection>>builder().status("success").data(repository.listAllDepartmentDirection()).build();
    }

    @Override
    public ResponseDto<DepartmentDto> patch(DepartmentDto dto) {
        if(dto.getId() == null) throw new DatabaseException("Department id is null");
        if(repository.existsById(dto.getId())){
            return ResponseDto.<DepartmentDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
        }
        throw new DatabaseException("Department is not found");
    }


    @Override
    public ResponseDto<List<DepartmentDto>> getAll() {
        return ResponseDto.<List<DepartmentDto>>builder().status("success").data(repository.findAllByOrderByIdAsc().stream().map(mapper::toDto).toList()).build();
    }

    @Override
    public ResponseDto<DepartmentDto> delete(Long id) {
        Optional<Department> department = repository.findById(id);
        if(department.isPresent()){
            repository.deleteById(id);
            return ResponseDto.<DepartmentDto>builder().status("success").data(mapper.toDto(department.get())).build();
        }
        throw new DatabaseException("Department is not deleted");
    }
}
