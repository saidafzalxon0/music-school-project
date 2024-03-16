package com.example.Musicschool.service.impl;

import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.StatisticDto;
import com.example.Musicschool.entity.Statistic;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.StatisticRepository;
import com.example.Musicschool.service.StatisticService;
import com.example.Musicschool.service.mapper.StatisticMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticRepository repository;
    @Autowired
    private StatisticMapper mapper;

    @Override
    public ResponseDto<StatisticDto> post(StatisticDto dto) {
        return ResponseDto.<StatisticDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
    }

    @Override
    public ResponseDto<StatisticDto> patch(StatisticDto dto) {
        if(dto.getId() == null) throw new DatabaseException("Id not found");
        Optional<Statistic> byId = repository.findById(dto.getId());
        if(byId.isPresent()){
            return ResponseDto.<StatisticDto>builder().status("success").data(mapper.toDto(repository.save(mapper.toEntity(dto)))).build();
        }
        throw new DatabaseException("Data not updated");
    }

    @Override
    public ResponseDto<StatisticDto> delete(Long id) {
        Optional<Statistic> optional = repository.findById(id);
        if(optional.isPresent()){
            repository.deleteById(id);
            return ResponseDto.<StatisticDto>builder().status("success").data(mapper.toDto(optional.get())).build();
        }
        throw new DatabaseException("Data is not deleted");
    }
    @Override
    public ResponseDto<List<StatisticDto>> get() {
        return ResponseDto.<List<StatisticDto>>builder().status("success").data(repository.findAll().stream().map(mapper::toDto).toList()).build();
    }
}
