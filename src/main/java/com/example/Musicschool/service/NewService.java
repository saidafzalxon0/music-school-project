package com.example.Musicschool.service;

import com.example.Musicschool.dto.FileDto;
import com.example.Musicschool.dto.NewDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface NewService {
    ResponseDto<NewDto> post(String title,String about,String shortly,String who_from,Date date,MultipartFile file);
    ResponseDto<NewDto> patch(Long id,String title,String about,String shortly,String who_from,Date date,MultipartFile file);
    ResponseDto<NewDto> getById(Long id);
    ResponseDto<Page<New>> getByDate(Pageable pageable, Date date);

    ResponseDto<Page<New>> getAll(Pageable pageable);
    ResponseDto<NewDto> delete(Long id);
}
