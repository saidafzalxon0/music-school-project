package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.StatisticDto;
import com.example.Musicschool.entity.Statistic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticMapper extends CommonMapper<StatisticDto, Statistic> {
}
