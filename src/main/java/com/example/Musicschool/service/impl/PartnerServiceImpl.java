package com.example.Musicschool.service.impl;

import com.example.Musicschool.config.S3File;
import com.example.Musicschool.dto.DirectionDto;
import com.example.Musicschool.dto.PartnerDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.Partner;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.PartnerRepository;
import com.example.Musicschool.service.PartnerService;
import com.example.Musicschool.service.mapper.PartnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerRepository repository;
    @Autowired
    private PartnerMapper mapper;
    @Autowired
    private S3File s3File;

    @Override
    public ResponseDto<PartnerDto> post(String name, MultipartFile file, String linkWebsite) {
        if(repository.findFirstByName(name).isPresent() || repository.findFirstByLinkWebsite(linkWebsite).isPresent()){
            throw new DatabaseException("Partner is already exists");
        }
        return ResponseDto.<PartnerDto>builder().status("success")
                .data(mapper.toDto(repository
                        .findById(repository
                                .save(Partner.builder().name(name).photoLink(s3File.postFile(file)).linkWebsite(linkWebsite).build())
                                .getId())
                        .get())).build();

    }

    @Override
    public ResponseDto<PartnerDto> patch(Long id, String name, MultipartFile file, String linkWebsite) {
        if(id == null) throw new DatabaseException("Partner id is null");
        Optional<Partner> firstByName = repository.findFirstByName(name);
        if(firstByName.isPresent() && firstByName.get().getId() != id) throw new DatabaseException("Partner is already exists");

        Optional<Partner> firstByLinkWebsite = repository.findFirstByLinkWebsite(linkWebsite);
        if(firstByLinkWebsite.isPresent() && firstByLinkWebsite.get().getId() != id) throw new DatabaseException("Government is already exists");

        Optional<Partner> partner = repository.findById(id);
        if(partner.isPresent()) {
            if (file != null) {
                s3File.deleteFile(partner.get().getPhotoLink());
                return ResponseDto.<PartnerDto>builder().status("success")
                        .data(mapper.toDto(repository
                                .findById(repository
                                        .save(Partner.builder().id(id).name(name).photoLink(s3File.postFile(file)).linkWebsite(linkWebsite).build())
                                        .getId())
                                .get())).build();
            }else{
                return ResponseDto.<PartnerDto>builder().status("success")
                        .data(mapper.toDto(repository
                                .findById(repository
                                        .save(Partner.builder().id(id).name(name).photoLink(partner.get().getPhotoLink()).linkWebsite(linkWebsite).build())
                                        .getId())
                                .get())).build();
            }
        }
        throw new DatabaseException("Partner is not found");
    }

    @Override
    public ResponseDto<PartnerDto> get(Long id) {
        Optional<Partner> optional = repository.findById(id);
        if(optional.isPresent()){
            return ResponseDto.<PartnerDto>builder().status("success").data(mapper.toDto(optional.get())).build();
        }
        throw new DatabaseException("Partner is not found");
    }

    @Override
    public ResponseDto<List<PartnerDto>> getAll() {
        return ResponseDto.<List<PartnerDto>>builder().status("success").data(repository.findAll().stream().map(mapper::toDto).toList()).build();
    }

    @Override
    public ResponseDto<PartnerDto> delete(Long id) {
        Optional<Partner> partner = repository.findById(id);
        if(partner.isPresent()){
            s3File.deleteFile(partner.get().getPhotoLink());
            repository.delete(partner.get());
            return ResponseDto.<PartnerDto>builder().status("success").data(mapper.toDto(partner.get())).build();
        }
        throw new DatabaseException("Partner is not found");
    }
}
