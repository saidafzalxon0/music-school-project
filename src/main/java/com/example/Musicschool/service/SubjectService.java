package com.example.Musicschool.service;

import com.example.Musicschool.dto.FileDto;
import com.example.Musicschool.dto.PositionDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SubjectDto;
import com.example.Musicschool.projection.SubjectDirectionGetProjection;
import com.example.Musicschool.projection.SubjectGetProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubjectService {
    ResponseDto<SubjectDirectionGetProjection> post(SubjectDto subjectDto);
    ResponseDto<SubjectDirectionGetProjection> patch(SubjectDto subjectDto);
    ResponseDto<SubjectDirectionGetProjection> delete(Long id);
    ResponseDto<List<SubjectGetProjection>> getAllByDirectionId(Long id);

}
