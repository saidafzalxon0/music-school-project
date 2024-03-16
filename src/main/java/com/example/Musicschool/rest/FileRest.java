package com.example.Musicschool.rest;

import com.example.Musicschool.dto.FileDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.service.FileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/file")
public record FileRest(FileService service) {
    @PostMapping(consumes = "multipart/form-data")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<FileDto> post(@RequestParam MultipartFile file, @RequestParam Integer type){
        return service.post(file, type);
    }

    @PatchMapping(consumes = "multipart/form-data")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<FileDto> patch(@RequestParam @NotNull Long id,@RequestParam(required = false) MultipartFile file, @RequestParam @NotNull Integer type){
        return service.patch(id,file,type);
    }

    @GetMapping("/{id}")
    public ResponseDto<FileDto> get(@NotNull @PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("type/{id}")
    public ResponseDto<List<FileDto>> getByType(@NotNull @PathVariable Integer id){
        return service.getByType(id);
    }
    @GetMapping("/getAll")
    public ResponseDto<List<FileDto>> getAll(){
        return service.getAll();
    }


    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<FileDto> delete(@PathVariable Long id){
        return service.delete(id);
    }

}
