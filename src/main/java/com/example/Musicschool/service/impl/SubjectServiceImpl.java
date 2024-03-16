package com.example.Musicschool.service.impl;

import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SubjectDto;
import com.example.Musicschool.entity.Subject;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.projection.SubjectDirectionGetProjection;
import com.example.Musicschool.projection.SubjectGetProjection;
import com.example.Musicschool.repository.SubjectRepository;
import com.example.Musicschool.service.SubjectService;
import com.example.Musicschool.service.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository repository;
    @Autowired
    private SubjectMapper mapper;

    @Override
    public ResponseDto<SubjectDirectionGetProjection> post(SubjectDto dto) {
        if(repository.findFirstByNameAndDirection_Id(dto.getName(),dto.getDirection().getId()).isPresent()){
            throw new DatabaseException("Subject is already exists");
        }

        return ResponseDto.<SubjectDirectionGetProjection>builder().status("success").data(repository.findBySubjectId(repository.save(mapper.toEntity(dto)).getId()).get()).build();
    }

    @Override
    public ResponseDto<SubjectDirectionGetProjection> patch(SubjectDto dto) {
        if(dto.getId() == null) throw new DatabaseException("Subject id is null");
        Optional<Subject> byName = repository.findFirstByNameAndDirection_Id (dto.getName(),dto.getDirection().getId());
        if(byName.isEmpty()){
            if(repository.existsById(dto.getId())){
                return ResponseDto.<SubjectDirectionGetProjection>builder().status("success").data(repository.findBySubjectId(repository.save(mapper.toEntity(dto)).getId()).get()).build();
            }
            throw new DatabaseException("Subject is not found");
        }else{
            if(byName.get().getId().equals(dto.getId())){
                if(repository.existsById(dto.getId())){
                    return ResponseDto.<SubjectDirectionGetProjection>builder().status("success").data(repository.findBySubjectId(repository.save(mapper.toEntity(dto)).getId()).get()).build();
                }
                throw new DatabaseException("Subject is not found");
            }else{
                throw new DatabaseException("Subject is already exists");
            }
        }
    }

    @Override
    public ResponseDto<SubjectDirectionGetProjection> delete(Long id) {
        Optional<SubjectDirectionGetProjection> by = repository.findBySubjectId(id);
        if(by.isPresent()){
            repository.deleteById(id);
            return ResponseDto.<SubjectDirectionGetProjection>builder().status("success").data(by.get()).build();
        }
        throw new DatabaseException("Subject is not deleted");
    }
    @Override
    public ResponseDto<List<SubjectGetProjection>> getAllByDirectionId(Long id) {
        return ResponseDto.<List<SubjectGetProjection>>builder().status("success").data(repository.findAllByDirectionId(id)).build();
    }
}
