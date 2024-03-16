package com.example.Musicschool.service;

import com.example.Musicschool.dto.DirectionDto;
import com.example.Musicschool.dto.PartnerDto;
import com.example.Musicschool.dto.ResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PartnerService {
    ResponseDto<PartnerDto> post(String name, MultipartFile file,String linkWebsite);
    ResponseDto<PartnerDto> patch(Long id,String name,MultipartFile file,String linkWebsite);
    ResponseDto<PartnerDto> get(Long id);
    ResponseDto<List<PartnerDto>> getAll();
    ResponseDto<PartnerDto> delete(Long id);
}
