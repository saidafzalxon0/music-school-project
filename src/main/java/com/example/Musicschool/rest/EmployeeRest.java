package com.example.Musicschool.rest;

import com.example.Musicschool.dto.EmployeeDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.projection.EmployeeIdProjection;
import com.example.Musicschool.projection.EmployeeLevelProjection;
import com.example.Musicschool.projection.EmployeeProjection;
import com.example.Musicschool.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/employee")
public record EmployeeRest(EmployeeService service) {


    @PostMapping(consumes = "multipart/form-data")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<EmployeeIdProjection> post(@RequestPart String fullName, @RequestPart String about, @RequestPart(required = false) String editor, @RequestPart String position_id, @RequestPart String direction_id, @RequestPart String type, @RequestPart(required = false) String level,
                                         @RequestPart("photo_file") MultipartFile photo_file,
                                        @RequestPart(value = "list_files",required = false) List<MultipartFile> list_files) {
        return service.post(fullName, about, editor, Long.parseLong(position_id), Long.parseLong(direction_id), Integer.parseInt(type),Integer.parseInt(level==null?"0":level), photo_file, list_files);
    }



    @PatchMapping(consumes = "multipart/form-data")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<EmployeeIdProjection> patch(@RequestPart String id,@RequestPart String fullName, @RequestPart String about, @RequestPart(required = false) String editor, @RequestPart String position_id, @RequestPart String direction_id, @RequestPart String type, @RequestPart(required = false) String level,
                                          @RequestPart(value = "photo_file",required = false) MultipartFile photo_file,
                                          @RequestPart(value = "list_files",required = false) List<MultipartFile> list_files,@RequestPart(value = "list_id") String list_id){
        return service.patch(Long.parseLong(id),fullName,about,editor,Long.parseLong(position_id),Long.parseLong(direction_id),Integer.parseInt(type),Integer.parseInt(level == null?"0":level),photo_file,list_files,Integer.parseInt(list_id));
    }
    @GetMapping("/{id}")
    public ResponseDto<EmployeeIdProjection> getById(@NotNull @PathVariable Long id){
        return service.getById(id);
    }
    @GetMapping("/type/{id}")
    public ResponseDto<List<EmployeeIdProjection>> getByType(@NotNull @PathVariable Integer id){
        return service.getByType(id);
    }
    @GetMapping("/direction/{id}")
    public ResponseDto<List<EmployeeLevelProjection>> getByDirection(@NotNull @PathVariable Long id){
        return service.getByDirection(id);
    }
    @GetMapping("/getAll")
    public ResponseDto<List<EmployeeProjection>> getAll(){
       return service.getAll();
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<EmployeeIdProjection> delete(@NotNull @PathVariable Long id){
        return service.delete(id);
    }
}
