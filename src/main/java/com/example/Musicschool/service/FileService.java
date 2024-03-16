package com.example.Musicschool.service;

import com.example.Musicschool.dto.FileDto;
import com.example.Musicschool.dto.ResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    ResponseDto<FileDto> post(MultipartFile file, Integer type);
    ResponseDto<FileDto> patch(Long id,MultipartFile file,Integer type);
    ResponseDto<FileDto> getById(Long id);
    ResponseDto<List<FileDto>> getByType(Integer id);

    ResponseDto<List<FileDto>> getAll();
    ResponseDto<FileDto> delete(Long id);
}
