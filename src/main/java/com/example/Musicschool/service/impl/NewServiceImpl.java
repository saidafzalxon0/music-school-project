package com.example.Musicschool.service.impl;

import com.example.Musicschool.config.S3File;
import com.example.Musicschool.dto.FileDto;
import com.example.Musicschool.dto.NewDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.New;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.NewRepository;
import com.example.Musicschool.service.NewService;
import com.example.Musicschool.service.mapper.NewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Optional;

@Service
public class NewServiceImpl implements NewService {
    @Autowired
    private NewRepository repository;
    @Autowired
    private NewMapper mapper;
    @Autowired
    private S3File s3File;
    @Override
    public ResponseDto<NewDto> post(String title, String about, String shortly, String who_from, Date date, MultipartFile file) {
        if(file == null){
            return ResponseDto.<NewDto>builder().status("success").data(mapper.toDto(repository.save(New.builder().about(about).title(title).shortly(shortly).who_from(who_from == null? "" : who_from).date(date).build()))).build();
        }
        else{
            try{
                return ResponseDto.<NewDto>builder().status("success").data(mapper.toDto(repository.save(New.builder().about(about).title(title).shortly(shortly).who_from(who_from == null? "" : who_from).date(date).file(s3File.postFile(file)).build()))).build();
            }catch (Exception e){
                throw new DatabaseException("News not saved");
            }
        }

    }

    @Override
    public ResponseDto<NewDto> patch(Long id,String title, String about, String shortly, String who_from, Date date, MultipartFile file) {
        Optional<New> byId = repository.findById(id);
        if(byId.isEmpty()) throw new DatabaseException("News not found");
        if(file == null){
            return ResponseDto.<NewDto>builder().status("success").data(mapper.toDto(repository.save(New.builder().id(id).about(about).title(title).shortly(shortly).who_from(who_from == null? "" : who_from).date(date).build()))).build();
        }
        else{
            try{
                if(byId.get().getFile() != null) s3File.deleteFile(byId.get().getFile());
                return ResponseDto.<NewDto>builder().status("success").data(mapper.toDto(repository.save(New.builder().id(id).about(about).title(title).shortly(shortly).who_from(who_from == null? "" : who_from).date(date).file(s3File.postFile(file)).build()))).build();
            }catch (Exception e){
                throw new DatabaseException("New not edited");
            }
            }

    }

    @Override
    public ResponseDto<NewDto> getById(Long id) {
        Optional<New> byId = repository.findById(id);
        if(byId.isEmpty()) throw new DatabaseException("News not found");
        return ResponseDto.<NewDto>builder().status("success").data(mapper.toDto(byId.get())).build();
    }

    @Override
    public ResponseDto<Page<New>> getByDate(Pageable pageable, Date date) {
        return ResponseDto.<Page<New>>builder().status("success").data(repository.findAllByDate(pageable,date)).build();
    }

    @Override
    public ResponseDto<Page<New>> getAll(Pageable pageable) {
        return ResponseDto.<Page<New>>builder().status("success").data(repository.findAll(pageable)).build();
    }

    @Override
    public ResponseDto<NewDto> delete(Long id) {
        Optional<New> byId = repository.findById(id);
        if(byId.isEmpty()) throw new DatabaseException("News not found");
        repository.deleteById(id);
        return ResponseDto.<NewDto>builder().status("success").data(mapper.toDto(byId.get())).build();
    }
}
