package com.example.Musicschool.service.impl;

import com.example.Musicschool.config.S3File;
import com.example.Musicschool.dto.EventDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.Event;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.EventRepository;
import com.example.Musicschool.service.EventService;
import com.example.Musicschool.service.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private EventMapper mapper;
    @Autowired
    private S3File s3File;
    @Override
    public ResponseDto<EventDto> post(String title, String about, Date date, String start_time, String end_time, String location, MultipartFile photo) {
            return ResponseDto.<EventDto>builder().status("success").data(mapper.toDto(repository.save(Event.builder().title(title).about(about).date(date).start_time(start_time == null ? "": start_time).end_time(end_time == null ? "": end_time).location(location == null? "":location).photo_link(s3File.postFile(photo)).build()))).build();
    }

    @Override
    public ResponseDto<EventDto> patch(Long id, String title, String about, Date date, String start_time, String end_time, String location, MultipartFile photo) {
        Optional<Event> byId = repository.findById(id);
        if(byId.isEmpty()) throw new DatabaseException("Event not found");
        if(photo == null){
            return ResponseDto.<EventDto>builder().status("success").data(mapper.toDto(repository.save(Event.builder().id(byId.get().getId()).title(title).about(about).date(date).start_time(start_time == null ? "": start_time).end_time(end_time == null ? "": end_time).location(location == null? "":location).photo_link(byId.get().getPhoto_link()).build()))).build();
        }
        else{
            try{
                if(byId.get().getPhoto_link() != null) s3File.deleteFile(byId.get().getPhoto_link());
                return ResponseDto.<EventDto>builder().status("success").data(mapper.toDto(repository.save(Event.builder().id(byId.get().getId()).title(title).about(about).date(date).start_time(start_time == null ? "": start_time).end_time(end_time == null ? "": end_time).location(location == null? "":location).photo_link(s3File.postFile(photo)).build()))).build();
            }catch (Exception e){
                throw new DatabaseException("New not edited");
            }
        }
    }

    @Override
    public ResponseDto<EventDto> getById(Long id) {
        Optional<Event> byId = repository.findById(id);
        if(byId.isEmpty()) throw new DatabaseException("Event not found");
        return ResponseDto.<EventDto>builder().status("success").data(mapper.toDto(byId.get())).build();
    }

    @Override
    public ResponseDto<Page<Event>> getAll(Pageable pageable) {
        return ResponseDto.<Page<Event>>builder().status("success").data(repository.findAll(pageable)).build();
    }

    @Override
    public ResponseDto<Page<Event>> getByDate(Pageable pageable, Date date) {
        return ResponseDto.<Page<Event>>builder().status("success").data(repository.findAllByDate(pageable,date)).build();

    }

    @Override
    public ResponseDto<EventDto> delete(Long id) {
        Optional<Event> byId = repository.findById(id);
        if(byId.isEmpty()) throw new DatabaseException("Event not found");
        repository.deleteById(id);
        return ResponseDto.<EventDto>builder().status("success").data(mapper.toDto(byId.get())).build();
    }
}
