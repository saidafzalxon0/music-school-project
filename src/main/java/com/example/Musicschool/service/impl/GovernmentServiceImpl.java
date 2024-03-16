package com.example.Musicschool.service.impl;

import com.example.Musicschool.config.S3File;
import com.example.Musicschool.dto.GovernmentDto;
import com.example.Musicschool.dto.PartnerDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.Government;
import com.example.Musicschool.entity.Partner;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.GovernmentRepository;
import com.example.Musicschool.repository.PartnerRepository;
import com.example.Musicschool.service.GovernmentService;
import com.example.Musicschool.service.mapper.GovernmentMapper;
import com.example.Musicschool.service.mapper.PartnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class GovernmentServiceImpl implements GovernmentService {
    @Autowired
    private GovernmentRepository repository;
    @Autowired
    private GovernmentMapper mapper;
    @Autowired
    private S3File s3File;

    @Override
    public ResponseDto<GovernmentDto> post(String name, MultipartFile file, String linkWebsite) {
        if(repository.findFirstByName(name).isPresent() || repository.findFirstByLinkWebsite(linkWebsite).isPresent()){
            throw new DatabaseException("Government is already exists");
        }
        return ResponseDto.<GovernmentDto>builder().status("success")
                .data(mapper.toDto(repository
                        .findById(repository
                                .save(Government.builder().name(name).photoLink(s3File.postFile(file)).linkWebsite(linkWebsite).build())
                                .getId())
                        .get())).build();
    }

    @Override
    public ResponseDto<GovernmentDto> patch(Long id, String name, MultipartFile file, String linkWebsite) {
        if(id == null) throw new DatabaseException("Government id is null");
        Optional<Government> firstByName = repository.findFirstByName(name);
        if(firstByName.isPresent() && firstByName.get().getId() != id) throw new DatabaseException("Government is already exists");
        Optional<Government> firstByLinkWebsite = repository.findFirstByLinkWebsite(linkWebsite);
        if(firstByLinkWebsite.isPresent() && firstByLinkWebsite.get().getId() != id) throw new DatabaseException("Government is already exists");
        Optional<Government> partner = repository.findById(id);
        if(partner.isPresent()) {

            if (file != null) {
                s3File.deleteFile(partner.get().getPhotoLink());
                return ResponseDto.<GovernmentDto>builder().status("success")
                        .data(mapper.toDto(repository
                                .findById(repository
                                        .save(Government.builder().id(id).name(name).photoLink(s3File.postFile(file)).linkWebsite(linkWebsite).build())
                                        .getId())
                                .get())).build();
            } else {
                return ResponseDto.<GovernmentDto>builder().status("success")
                        .data(mapper.toDto(repository
                                .findById(repository
                                        .save(Government.builder().id(id).name(name).photoLink(partner.get().getPhotoLink()).linkWebsite(linkWebsite).build())
                                        .getId())
                                .get())).build();
            }
        }
        throw new DatabaseException("Government is not found");
    }

    @Override
    public ResponseDto<GovernmentDto> get(Long id) {
        Optional<Government> optional = repository.findById(id);
        if(optional.isPresent()){
            return ResponseDto.<GovernmentDto>builder().status("success").data(mapper.toDto(optional.get())).build();
        }
        throw new DatabaseException("Government is not found");
    }

    @Override
    public ResponseDto<List<GovernmentDto>> getAll() {
        return ResponseDto.<List<GovernmentDto>>builder().status("success").data(repository.findAll().stream().map(mapper::toDto).toList()).build();
    }

    @Override
    public ResponseDto<GovernmentDto> delete(Long id) {
        Optional<Government> government = repository.findById(id);
        if(government.isPresent()){
            s3File.deleteFile(government.get().getPhotoLink());
            repository.delete(government.get());
            return ResponseDto.<GovernmentDto>builder().status("success").data(mapper.toDto(government.get())).build();
        }
        throw new DatabaseException("Government is not found");
    }
}
