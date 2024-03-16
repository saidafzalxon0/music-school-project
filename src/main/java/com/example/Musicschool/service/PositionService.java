package com.example.Musicschool.service;

import com.example.Musicschool.dto.PartnerDto;
import com.example.Musicschool.dto.PositionDto;
import com.example.Musicschool.dto.ResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PositionService {
    ResponseDto<PositionDto> post(PositionDto dto);
    ResponseDto<PositionDto> patch(PositionDto dto);
    ResponseDto<PositionDto> get(Long id);
    ResponseDto<List<PositionDto>> getAll();
    ResponseDto<PositionDto> delete(Long id);
}
