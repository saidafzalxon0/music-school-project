package com.example.Musicschool.service.mapper;

import com.example.Musicschool.dto.EventDto;
import com.example.Musicschool.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper extends CommonMapper<EventDto, Event> {
}
