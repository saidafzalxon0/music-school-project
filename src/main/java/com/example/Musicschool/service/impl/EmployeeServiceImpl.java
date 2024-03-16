package com.example.Musicschool.service.impl;

import com.example.Musicschool.config.S3File;
import com.example.Musicschool.dto.EmployeeDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.Direction;
import com.example.Musicschool.entity.Employee;
import com.example.Musicschool.entity.Position;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.projection.EmployeeIdProjection;
import com.example.Musicschool.projection.EmployeeLevelProjection;
import com.example.Musicschool.projection.EmployeeProjection;
import com.example.Musicschool.repository.DirectionRepository;
import com.example.Musicschool.repository.EmployeeRepository;
import com.example.Musicschool.repository.PositionRepository;
import com.example.Musicschool.service.EmployeeService;
import com.example.Musicschool.service.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private EmployeeMapper mapper;
    @Autowired
    private PositionRepository position_repository;
    @Autowired
    private DirectionRepository direction_repository;

    @Override
    public ResponseDto<EmployeeIdProjection> post(String fullName, String about, String editor, Long position_id, Long direction_id, Integer type, Integer level, MultipartFile photo_file, List<MultipartFile> list_files) {
        if(type<1 || type > 10) throw new DatabaseException("Type not found");
        Optional<Position> position_optional = position_repository.findById(position_id);
        if(position_optional.isEmpty()) throw new DatabaseException("Position not found");
        Optional<Direction> direction_optional = direction_repository.findById(direction_id);
        if(direction_optional.isEmpty()) throw new DatabaseException("Direction not found");
        List<String> list = new ArrayList<>();
        if(list_files != null) {
            for (MultipartFile listFile : list_files) {
                list.add(s3File.postFile(listFile));
            }
        }

        return ResponseDto.<EmployeeIdProjection>builder()
                .status("success")
                .data(repository.
                        getfindById(repository
                                .save(Employee.builder().fullName(fullName).about(about).editor(editor == null? "" : editor).position(position_optional.get()).direction(direction_optional.get()).type(type).level(level).photoLink(s3File.postFile(photo_file)).listFile(list).build()).getId()).get()).build();
    }

    @Autowired
    private S3File s3File;


    @Override
    public ResponseDto<EmployeeIdProjection> patch(Long id, String fullName, String about, String editor, Long position_id, Long direction_id, Integer type, Integer level, MultipartFile photo_file, List<MultipartFile> list_files, Integer list_id) {
        if(id == null) throw new DatabaseException("Employee id not found");
        if(type<1 || type > 10) throw new DatabaseException("Type not found");
        Optional<Position> position_optional = position_repository.findById(position_id);
        if(position_optional.isEmpty()) throw new DatabaseException("Position not found");
        Optional<Direction> direction_optional = direction_repository.findById(direction_id);
        if(direction_optional.isEmpty()) throw new DatabaseException("Direction not found");
        Optional<Employee> optional = repository.findById(id);
        if(optional.isPresent()) {
            if (photo_file != null && list_files != null) {
                for (String s : optional.get().getListFile()) {
                    s3File.deleteFile(s);
                }
                s3File.deleteFile(optional.get().getPhotoLink());
                List<String> list = new ArrayList<>();
                for (MultipartFile listFile : list_files) {
                    list.add(s3File.postFile(listFile));
                }
                repository.save(Employee.builder().id(id).fullName(fullName).about(about).editor(editor == null ? optional.get().getEditor(): editor).position(position_optional.get()).direction(direction_optional.get()).type(type).level(level).photoLink(s3File.postFile(photo_file)).listFile(list).build());
                return ResponseDto.<EmployeeIdProjection>builder().status("success").data(repository.getfindById(id).get()).build();
            }else if(photo_file == null && list_files != null){
                for (String s : optional.get().getListFile()) {
                    s3File.deleteFile(s);
                }
                List<String> list = new ArrayList<>();
                for (MultipartFile listFile : list_files) {
                    list.add(s3File.postFile(listFile));
                }
                repository.save(Employee.builder().id(id).fullName(fullName).about(about).editor(editor == null ? optional.get().getEditor(): editor).position(position_optional.get()).direction(direction_optional.get()).type(type).level(level).photoLink(optional.get().getPhotoLink()).listFile(list).build());
                return ResponseDto.<EmployeeIdProjection>builder().status("success").data(repository.getfindById(id).get()).build();

            }
            else if(photo_file != null && list_files == null) {
                if(list_id != 1) {
                    repository.save(Employee.builder().id(id).fullName(fullName).about(about).editor(editor == null ? optional.get().getEditor() : editor).position(position_optional.get()).direction(direction_optional.get()).type(type).level(level).photoLink(s3File.postFile(photo_file)).listFile(optional.get().getListFile()).build());
                    return ResponseDto.<EmployeeIdProjection>builder().status("success").data(repository.getfindById(id).get()).build();
                }else{
                    repository.save(Employee.builder().id(id).fullName(fullName).about(about).editor(editor == null ? optional.get().getEditor() : editor).position(position_optional.get()).direction(direction_optional.get()).type(type).level(level).photoLink(s3File.postFile(photo_file)).listFile(null).build());
                    return ResponseDto.<EmployeeIdProjection>builder().status("success").data(repository.getfindById(id).get()).build();
                }
            }else{
                if(list_id != 1){
                    repository.save(Employee.builder().id(id).fullName(fullName).about(about).editor(editor == null ? optional.get().getEditor(): editor).position(position_optional.get()).direction(direction_optional.get()).type(type).level(level).photoLink(optional.get().getPhotoLink()).listFile(optional.get().getListFile()).build());
                    return ResponseDto.<EmployeeIdProjection>builder().status("success").data(repository.getfindById(id).get()).build();
                }else{
                    repository.save(Employee.builder().id(id).fullName(fullName).about(about).editor(editor == null ? optional.get().getEditor(): editor).position(position_optional.get()).direction(direction_optional.get()).type(type).level(level).photoLink(optional.get().getPhotoLink()).listFile(null).build());
                    return ResponseDto.<EmployeeIdProjection>builder().status("success").data(repository.getfindById(id).get()).build();
                }
            }
        }
        throw new DatabaseException("Employee not found");
    }

    @Override
    public ResponseDto<EmployeeIdProjection> getById(Long id) {
        Optional<EmployeeIdProjection> optional = repository.getfindById(id);
        if(optional.isPresent()){
            return ResponseDto.<EmployeeIdProjection>builder().status("success").data(optional.get()).build();
        }
        throw new DatabaseException("Employee is not found");
    }

    @Override
    public ResponseDto<List<EmployeeIdProjection>> getByType(Integer id) {
        return ResponseDto.<List<EmployeeIdProjection>>builder().status("success").data(repository.findAllByType(id)).build();

    }

    @Override
    public ResponseDto<List<EmployeeLevelProjection>> getByDirection(Long id) {
        return ResponseDto.<List<EmployeeLevelProjection>>builder().status("success").data(repository.findByDirection(id)).build();
    }

    @Override
    public ResponseDto<List<EmployeeProjection>> getAll() {
        return ResponseDto.<List<EmployeeProjection>>builder().status("success").data(repository.getEmployeeProjection()).build();
    }

    @Override
    public ResponseDto<EmployeeIdProjection> delete(Long id) {
        Optional<EmployeeIdProjection> employeeIdProjection = repository.getfindById(id);
        if(employeeIdProjection.isPresent()){
            repository.deleteById(id);
            return ResponseDto.<EmployeeIdProjection>builder().status("success").data(employeeIdProjection.get()).build();
        }
        throw new DatabaseException("Employee is not deleted");
    }
}
