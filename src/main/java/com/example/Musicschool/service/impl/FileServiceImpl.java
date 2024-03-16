package com.example.Musicschool.service.impl;

import com.example.Musicschool.config.S3File;
import com.example.Musicschool.dto.FileDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.File;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.repository.FileRepository;
import com.example.Musicschool.service.FileService;
import com.example.Musicschool.service.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository repository;
    @Autowired
    private FileMapper mapper;
    @Autowired
    private S3File s3File;


    @Override
    public ResponseDto<FileDto> post(MultipartFile file, Integer type) {

        if(type>0 && type<11){
            return ResponseDto.<FileDto>builder().status("success").data(mapper.toDto(repository.save(File.builder().link(s3File.postFile(file)).type(type).build()))).build();
        }
        throw new DatabaseException("Type not found");
    }

    @Override
    public ResponseDto<FileDto> patch(Long id,MultipartFile file, Integer type) {
        if(type<1 || type>10) throw new RuntimeException("File type not found");
        Optional<File> optional = repository.findById(id);
        if(optional.isPresent()) {
            if (file != null) {
                s3File.deleteFile(optional.get().getLink());
                return ResponseDto.<FileDto>builder().status("success")
                        .data(mapper.toDto(repository
                                .findById(repository
                                        .save(File.builder().id(id).link(s3File.postFile(file)).type(type).build())
                                        .getId())
                                .get())).build();
            }else{
                return ResponseDto.<FileDto>builder().status("success")
                        .data(mapper.toDto(repository
                                .findById(repository
                                        .save(File.builder().id(id).link(optional.get().getLink()).type(type).build())
                                        .getId())
                                .get())).build();
            }
        }
        throw new DatabaseException("File is not found");
    }

    @Override
    public ResponseDto<FileDto> getById(Long id) {
        Optional<File> optional = repository.findById(id);
        if(optional.isPresent()){
            return ResponseDto.<FileDto>builder().status("success").data(mapper.toDto(optional.get())).build();
        }
        throw new DatabaseException("File is not found");
    }


    @Override
    public ResponseDto<List<FileDto>> getByType(Integer id) {
        if(id<1 ||  id>10) throw new DatabaseException("File type not found");
        return ResponseDto.<List<FileDto>>builder().status("success").data(repository.findAllByType(id).stream().map(mapper::toDto).toList()).build();
    }

    @Override
    public ResponseDto<List<FileDto>> getAll() {
        return ResponseDto.<List<FileDto>>builder().status("success").data(repository.findAll().stream().map(mapper::toDto).toList()).build();
    }

    @Override
    public ResponseDto<FileDto> delete(Long id) {
        Optional<File> file = repository.findById(id);
        if(file.isPresent()){
            s3File.deleteFile(file.get().getLink());
            repository.delete(file.get());
            return ResponseDto.<FileDto>builder().status("success").data(mapper.toDto(file.get())).build();
        }
        throw new DatabaseException("File is not found");
    }
}
