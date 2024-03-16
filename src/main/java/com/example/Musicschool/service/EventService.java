package com.example.Musicschool.service;

import com.example.Musicschool.dto.EventDto;
import com.example.Musicschool.dto.FileDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.Event;
import com.example.Musicschool.entity.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface EventService {
    ResponseDto<EventDto> post(String title, String about, Date date, String start_time, String end_time, String location, MultipartFile photo);
    ResponseDto<EventDto> patch(Long id,String title, String about, Date date,String start_time,String end_time,String location,MultipartFile photo);
    ResponseDto<EventDto> getById(Long id);
    ResponseDto<Page<Event>> getAll(Pageable pageable);
    ResponseDto<Page<Event>> getByDate(Pageable pageable, Date date);
    ResponseDto<EventDto> delete(Long id);
}
