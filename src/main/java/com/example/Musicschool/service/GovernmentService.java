package com.example.Musicschool.service;

import com.example.Musicschool.dto.GovernmentDto;
import com.example.Musicschool.dto.PartnerDto;
import com.example.Musicschool.dto.ResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GovernmentService {
    ResponseDto<GovernmentDto> post(String name, MultipartFile file, String linkWebsite);
    ResponseDto<GovernmentDto> patch(Long id,String name,MultipartFile file,String linkWebsite);
    ResponseDto<GovernmentDto> get(Long id);
    ResponseDto<List<GovernmentDto>> getAll();
    ResponseDto<GovernmentDto> delete(Long id);
}
